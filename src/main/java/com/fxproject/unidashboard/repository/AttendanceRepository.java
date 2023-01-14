package com.fxproject.unidashboard.repository;

import com.fxproject.unidashboard.model.Attendances;
import com.fxproject.unidashboard.model.Lectures;
import com.fxproject.unidashboard.model.Students;
import com.fxproject.unidashboard.utils.HibernateConnect;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class AttendanceRepository {

    public List<Attendances> findStudentAttendances(Students student){
        Transaction tx = null;
        try (Session session = HibernateConnect.openSession()){
            tx = session.beginTransaction();
            Query<Attendances> query =
                    session.createQuery("SELECT a FROM Attendances a WHERE a.student = :student", Attendances.class);
            query.setParameter("student", student);
            tx.commit();
            return query.getResultList();
        } catch (Exception e) {
            if(tx != null && tx.isActive()) {
                tx.rollback();
            }
            return List.of();
        }
    }

    public List<Attendances> findLectureAttendances(Lectures lecture){
        Transaction tx = null;
        try (Session session = HibernateConnect.openSession()){
            tx = session.beginTransaction();
            Query<Attendances> query =
                    session.createQuery("SELECT a FROM Attendances a WHERE a.lecture = :lecture", Attendances.class);
            query.setParameter("lecture", lecture);
            tx.commit();
            return query.getResultList();
        } catch (Exception e) {
            if(tx != null && tx.isActive()) {
                tx.rollback();
            }
            return List.of();
        }
    }

    public void save(List<Attendances> attendances) {
        Transaction tx = null;
        try (Session session = HibernateConnect.openSession()){
            tx = session.beginTransaction();
            for (Attendances attendance : attendances) {
                session.persist(attendance);
            }
            tx.commit();
        } catch (Exception e) {
            if(tx != null && tx.isActive()) {
                tx.rollback();
            }
        }
    }
}
