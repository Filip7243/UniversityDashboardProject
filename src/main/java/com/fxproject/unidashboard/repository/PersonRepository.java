package com.fxproject.unidashboard.repository;

import com.fxproject.unidashboard.model.*;
import com.fxproject.unidashboard.utils.HibernateConnect;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class PersonRepository {

    private MarkRepository mr = new MarkRepository();
    private WageRepository wr = new WageRepository();
    private AttendanceRepository ar = new AttendanceRepository();
    private LectureRepository lr = new LectureRepository();
    public void save(Person person) {
        Transaction tx = null;
        try (Session session = HibernateConnect.openSession()) {
            tx = session.beginTransaction();
            ;
            session.persist(person);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        }
    }

    public List<Person> findAllPeople() {
        try (Session session = HibernateConnect.openSession()) {
            Query<Person> people = session.createQuery("SELECT p FROM Person p", Person.class);
            return people.getResultList();
        } catch (Exception e) {
            return List.of();
        }
    }

    public Optional<Person> findPersonWithId(Long id) {
        Transaction tx = null;
        try (Session session = HibernateConnect.openSession()) {
            try {
                tx = session.beginTransaction();
                CriteriaBuilder cb = session.getCriteriaBuilder();
                CriteriaQuery<Person> criteria = cb.createQuery(Person.class);
                Root<Person> root = criteria.from(Person.class);
                criteria.where(cb.equal(root.get("id"), id));
                tx.commit();
                return Optional.ofNullable(session.createQuery(criteria).getSingleResult());
            } catch (Exception e) {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
                return Optional.empty();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void removePersonWithId(Long id) {
        Transaction tx = null;
        Person p = null;
        List<Marks> studentsMarks = null;
        List<Wages> professorsWages = null;
        List<Attendances> studentAttendances = null;
        List<Lectures> professorLectures = null;
        Optional<Person> personWithId = findPersonWithId(id);
        if (personWithId.isPresent()) {
            p = personWithId.get();
            if (p instanceof Students s) {
                studentsMarks = mr.findStudentMarks(s.getAlbumId());
                studentAttendances = ar.findStudentAttendances(s);
            }
            if (p instanceof Professors prof) {
                professorsWages = wr.findProfessorWages(prof);
                professorLectures = lr.findProfessorLectures(prof);
            }
        }

        if (p != null) {
            try (Session session = HibernateConnect.openSession()) {
                tx = session.beginTransaction();
                //todo: remove student attendances
                if(studentAttendances != null) {
                    for (Attendances attendance : studentAttendances) {
                        session.remove(attendance);
                    }
                }
                if (studentsMarks != null) {
                    for (Marks studentMark : studentsMarks) {
                        session.remove(studentMark);
                    }
                }
                if(professorLectures != null) {
                    for (Lectures lecture : professorLectures) {
                        session.remove(lecture);
                    }
                }
                if (professorsWages != null) {
                    for (Wages wage : professorsWages) {
                        session.remove(wage);
                    }
                }
                session.remove(p.getAcc());
                session.remove(p);
                tx.commit();
            } catch (Exception e) {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public void updatePersonWithId(Person updatedPerson, Long id) {
        Person person = findPersonWithId(id).orElseThrow();

        Transaction tx = null;
        try (Session session = HibernateConnect.openSession()) {
            tx = session.beginTransaction();
            person.setFirstName(updatedPerson.getFirstName());
            person.setLastName(updatedPerson.getLastName());
            person.setEmail(updatedPerson.getEmail());
            person.setPhoneNumber(updatedPerson.getPhoneNumber());
            person.setBirthday(updatedPerson.getBirthday());
            person.setPlaceOfBirth(updatedPerson.getPlaceOfBirth());
            person.setGender(updatedPerson.getGender());
            person.setAge(updatedPerson.getAge());
            person.setPesel(updatedPerson.getPesel());
            Addresses address = person.getAddress();
            address.setCountry(updatedPerson.getAddress().getCountry());
            address.setCity(updatedPerson.getAddress().getCity());
            address.setStreet(updatedPerson.getAddress().getStreet());
            address.setFlatNumber(updatedPerson.getAddress().getFlatNumber());
            address.setPostalCode(updatedPerson.getAddress().getPostalCode());
            session.merge(person);
            tx.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        }
    }

    public Optional<Person> findPersonWithFirstName(String firstName) {
        Transaction tx = null;
        try (Session session = HibernateConnect.openSession()) {
            tx = session.beginTransaction();
            Query<Person> query = session.createQuery("SELECT p FROM Person p WHERE p.firstName = :firstName", Person.class);
            query.setParameter("firstName", firstName);
            tx.commit();
            return Optional.ofNullable(query.getSingleResult());
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            return Optional.empty();
        }
    }
}
