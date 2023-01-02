package com.fxproject.unidashboard.repository;

import com.fxproject.unidashboard.model.FieldsOfStudy;
import com.fxproject.unidashboard.model.Groups;
import com.fxproject.unidashboard.model.Person;
import com.fxproject.unidashboard.model.Students;
import com.fxproject.unidashboard.utils.HibernateConnect;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class GroupRepository {

    public List<Groups> findGroupsByFieldOfStudy(FieldsOfStudy fieldsOfStudy) {
        Transaction tx = null;
        try (Session session = HibernateConnect.openSession()) {
            tx = session.beginTransaction();
            Query<Groups> query =
                    session.createQuery("SELECT g FROM Groups g WHERE g.fieldOfStudy = :fieldsOfStudy", Groups.class);
            query.setParameter("fieldsOfStudy", fieldsOfStudy);
            tx.commit();
            return query.getResultList();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            return List.of();
        }
    }

    public List<Groups> findAllGroups() {
        try (Session session = HibernateConnect.openSession()) {
            Query<Groups> groups = session.createQuery("SELECT g FROM Groups g", Groups.class);
            return groups.getResultList();
        } catch (Exception e) {
            return List.of();
        }
    }

    public List<Groups> findStudentGroups(Long albumId) {
        Transaction tx = null;
        try (Session session = HibernateConnect.openSession()) {
            tx = session.beginTransaction();
            Query<Groups> query =
                    session.createQuery("SELECT s.groups FROM Students s WHERE s.albumId = :albumId", Groups.class);
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
}
