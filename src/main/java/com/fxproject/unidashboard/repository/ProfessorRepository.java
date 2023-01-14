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

public class ProfessorRepository {

    public List<Professors> findAllProfessors() {
        try (Session session = HibernateConnect.openSession()) {
            Query<Professors> professors = session.createQuery("SELECT p FROM Professors p", Professors.class);
            return professors.getResultList();
        } catch (Exception e) {
            return List.of();
        }
    }

    public Optional<Professors> findProfessorByAlbumId(Long albumId) {
        Transaction tx = null;
        try (Session session = HibernateConnect.openSession()) {
            tx = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Professors> criteria = cb.createQuery(Professors.class);
            Root<Professors> root = criteria.from(Professors.class);
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

    public void save(Professors p) {
        Transaction tx = null;
        try (Session session = HibernateConnect.openSession()) {
            tx = session.beginTransaction();
            session.persist(p);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        }
    }

    public List<Professors> findStudentWithName(String name) {
        Transaction tx = null;
        try (Session session = HibernateConnect.openSession()) {
            tx = session.beginTransaction();
            Query<Professors> query = session.createQuery("SELECT p FROM Professors p WHERE lower(p.firstName) LIKE :name", Professors.class);
            query.setParameter("name", "%" + name + "%");
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
