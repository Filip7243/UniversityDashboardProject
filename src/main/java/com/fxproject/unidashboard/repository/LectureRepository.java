package com.fxproject.unidashboard.repository;

import com.fxproject.unidashboard.model.Lectures;
import com.fxproject.unidashboard.model.Person;
import com.fxproject.unidashboard.model.Professors;
import com.fxproject.unidashboard.utils.HibernateConnect;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class LectureRepository {

    public List<Lectures> findAllLectures() {
        try (Session session = HibernateConnect.openSession()) {
            Query<Lectures> lectures = session.createQuery("SELECT l FROM Lectures l", Lectures.class);
            return lectures.getResultList();
        } catch (Exception e) {
            return List.of();
        }
    }

    public Optional<Lectures> findLectureWithId(Long id) {
        Transaction tx = null;
        try (Session session = HibernateConnect.openSession()) {
            tx = session.beginTransaction();
            Query<Lectures> query =
                    session.createQuery("SELECT l FROM Lectures l WHERE l.id = : id", Lectures.class);
            query.setParameter("id", id);
            tx.commit();
            return Optional.ofNullable(query.getSingleResult());
        } catch (Exception e) {
            if(tx != null && tx.isActive()) {
                tx.rollback();
            }
            return Optional.empty();
        }
    }

    public void removeLecture(Lectures lectures) {
        Transaction tx = null;
        try (Session session = HibernateConnect.openSession()) {
            tx = session.beginTransaction();
            session.remove(lectures);
            tx.commit();
        } catch (Exception e) {
            if(tx != null && tx.isActive()) {
                tx.rollback();
            }
        }
    }

    public void save(Lectures lecture) {
        Transaction tx = null;
        try (Session session = HibernateConnect.openSession()) {
            tx = session.beginTransaction();
            session.persist(lecture);
            tx.commit();
        } catch (Exception e) {
            if(tx != null && tx.isActive()) {
                tx.rollback();
            }
        }
    }

    public List<Lectures> findProfessorLectures(Professors professor) {
        Transaction tx = null;
        try (Session session = HibernateConnect.openSession()) {
            tx = session.beginTransaction();
            Query<Lectures> query =
                    session.createQuery("SELECT l FROM Lectures l WHERE l.professor = :professor", Lectures.class);
            query.setParameter("professor", professor);
            tx.commit();
            return query.getResultList();
        } catch (Exception e) {
            if(tx != null && tx.isActive()) {
                tx.rollback();
            }
            return List.of();
        }
    }

    public List<Lectures> findLectureWithTopic(String topic) {
        Transaction tx = null;
        try (Session session = HibernateConnect.openSession()) {
            tx = session.beginTransaction();
            Query<Lectures> query = session.createQuery("SELECT l FROM Lectures l WHERE lower(l.lectureTopic) LIKE :topic", Lectures.class);
            query.setParameter("topic", "%" + topic + "%");
            tx.commit();
            return query.getResultList();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            return List.of();
        }
    }

}
