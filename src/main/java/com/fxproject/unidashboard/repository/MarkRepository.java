package com.fxproject.unidashboard.repository;

import com.fxproject.unidashboard.model.Marks;
import com.fxproject.unidashboard.model.Students;
import com.fxproject.unidashboard.model.Subjects;
import com.fxproject.unidashboard.utils.HibernateConnect;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class MarkRepository {

    public List<Marks> findStudentMarks(Students student) {
        Transaction tx = null;
        try (Session session = HibernateConnect.openSession()) {
            tx = session.beginTransaction();
            Query<Marks> query = session.createQuery("SELECT m FROM Marks m WHERE m.student = :student", Marks.class);
            query.setParameter("student", student);
            tx.commit();
            return query.getResultList();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            return List.of();
        }
    }

    public List<Marks> findStudentMarksOnSubject(Students student, Subjects subject) {
        Transaction tx = null;
        try (Session session = HibernateConnect.openSession()) {
            tx = session.beginTransaction();
            Query<Marks> query = session.createQuery("SELECT m FROM Marks m WHERE m.student = :student AND m.subject = :subject", Marks.class);
            query.setParameter("student", student);
            query.setParameter("subject", subject);
            tx.commit();
            return query.getResultList();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            return List.of();
        }
    }

    public void save(Marks mark) {
        Transaction tx = null;
        try (Session session = HibernateConnect.openSession()) {
            tx = session.beginTransaction();
            session.persist(mark);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        }
    }

    public List<Marks> findWithName(String name, Long albumId) {
        Transaction tx = null;
        try (Session session = HibernateConnect.openSession()) {
            tx = session.beginTransaction();
            Query<Marks> query = session.createQuery("SELECT m FROM Marks m WHERE lower(m.subject.name) LIKE :name AND m.student.albumId = :albumId", Marks.class);
            query.setParameter("name", "%" + name + "%");
            query.setParameter("albumId", albumId);
            tx.commit();
            return query.getResultList();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            return List.of();
        }
    }

    public void updateMark(Marks newMark, Marks currentMark) {
        Transaction tx = null;
        try (Session session = HibernateConnect.openSession()) {
            tx = session.beginTransaction();
            currentMark.setMark(newMark.getMark());
            currentMark.setMarkDate(newMark.getMarkDate());
            currentMark.setDescription(newMark.getDescription());
            session.merge(currentMark);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        }
    }

    public void remove(Marks mark) {
        Transaction tx = null;
        try (Session session = HibernateConnect.openSession()) {
            tx = session.beginTransaction();
            session.remove(mark);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        }
    }
}
