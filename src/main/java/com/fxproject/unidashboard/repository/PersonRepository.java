package com.fxproject.unidashboard.repository;

import com.fxproject.unidashboard.model.Person;
import com.fxproject.unidashboard.model.UniversityAccounts;
import com.fxproject.unidashboard.utils.HibernateConnect;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class PersonRepository {


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
        Optional<Person> personWithId = findPersonWithId(id);
        if(personWithId.isPresent()) {
            p = personWithId.get();
        }

        if(p != null) {
            try (Session session = HibernateConnect.openSession()) {
                tx = session.beginTransaction();
                System.out.println("DUPA");
                session.remove(p.getAcc());
                session.remove(p);
                tx.commit();
            } catch (Exception e) {
                if(tx != null && tx.isActive()) {
                    tx.rollback();
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public void updatePerson() {
        //todo: update person
    }
}
