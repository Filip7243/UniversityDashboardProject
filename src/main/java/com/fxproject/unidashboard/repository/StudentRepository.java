package com.fxproject.unidashboard.repository;

import com.fxproject.unidashboard.model.Groups;
import com.fxproject.unidashboard.model.Person;
import com.fxproject.unidashboard.model.Students;
import com.fxproject.unidashboard.utils.HibernateConnect;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class StudentRepository {

    public List<Students> findAllStudents() {
        try (Session session = HibernateConnect.openSession()) {
            Query<Students> students = session.createQuery("SELECT s FROM Students s", Students.class);
            return students.getResultList();
        } catch (Exception e) {
            return List.of();
        }
    }

    public Optional<Students> findStudentByAlbumId(Long albumId) {
        Transaction tx = null;
        try (Session session = HibernateConnect.openSession()) {
            tx = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Students> criteria = cb.createQuery(Students.class);
            Root<Students> root = criteria.from(Students.class);
            criteria.where(cb.equal(root.get("albumId"), albumId));
            tx.commit();
            return Optional.ofNullable(session.createQuery(criteria).getSingleResult());
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            return Optional.empty();
        }
    }

    public void updateStudent(Students student) {
        Transaction tx = null;
        try (Session session = HibernateConnect.openSession()) {
            tx = session.beginTransaction();
            session.merge(student);
            tx.commit();
        } catch (Exception e) {
            if(tx != null && tx.isActive()) {
                tx.rollback();
            }
        }
    }

    public List<Students> getStudentsFromGroup(Groups group) {
        Transaction tx = null;
        try (Session session = HibernateConnect.openSession()) {
            tx = session.beginTransaction();
            Query<Students> query = session.createQuery("SELECT s FROM Students s JOIN s.groups g WHERE g = :group", Students.class);
            query.setParameter("group", group);
            tx.commit();
            return query.getResultList();
        } catch (Exception e) {
            if(tx != null && tx.isActive()) {
                tx.rollback();
            }
            return List.of();
        }
    }

    public void save(Students s) {
        Transaction tx = null;
        try (Session session = HibernateConnect.openSession()) {
            tx = session.beginTransaction();
            session.persist(s);
            tx.commit();
        } catch (Exception e) {
            if(tx != null && tx.isActive()) {
                tx.rollback();
            }
        }
    }

}
