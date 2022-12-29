package com.fxproject.unidashboard.repository;

import com.fxproject.unidashboard.model.FieldsOfStudy;
import com.fxproject.unidashboard.utils.HibernateConnect;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class FieldOfStudyRepository {

    public List<FieldsOfStudy> findAllFieldsOfStudy() {
        Transaction tx = null;
        try (Session session = HibernateConnect.openSession()) {
            tx = session.beginTransaction();
            Query<FieldsOfStudy> query = session.createQuery("SELECT f FROM FieldsOfStudy f", FieldsOfStudy.class);
            tx.commit();
            return query.getResultList();
        } catch (Exception e) {
            if(tx != null && tx.isActive()) {
                tx.rollback();
            }
            return List.of();
        }
    }
}
