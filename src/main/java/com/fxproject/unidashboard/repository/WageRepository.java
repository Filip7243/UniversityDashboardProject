package com.fxproject.unidashboard.repository;

import com.fxproject.unidashboard.model.Professors;
import com.fxproject.unidashboard.model.Wages;
import com.fxproject.unidashboard.utils.HibernateConnect;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.dialect.H2Dialect;
import org.hibernate.query.Query;

import java.util.List;

public class WageRepository {

    public List<Wages> findProfessorWages(Professors professor) {
        Transaction tx = null;
        try (Session session = HibernateConnect.openSession()) {
            tx = session.beginTransaction();
            Query<Wages> query =
                    session.createQuery("SELECT w FROM Wages w WHERE w.professor = :professor", Wages.class);
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

    public void addWage(Wages wage) {
        Transaction tx = null;
        try (Session session = HibernateConnect.openSession()) {
            tx = session.beginTransaction();
            if(wage.getId() == null) {
                session.persist(wage);
            } else {
                session.merge(wage);
            }
            tx.commit();
        } catch (Exception e) {
            if(tx != null && tx.isActive()) {
                tx.rollback();
            }
        }
    }
}
