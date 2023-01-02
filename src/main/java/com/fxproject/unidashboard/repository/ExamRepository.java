package com.fxproject.unidashboard.repository;

import com.fxproject.unidashboard.model.Exams;
import com.fxproject.unidashboard.model.Groups;
import com.fxproject.unidashboard.model.Students;
import com.fxproject.unidashboard.utils.HibernateConnect;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.security.auth.Subject;
import java.util.List;
import java.util.Optional;

public class ExamRepository {

    public List<Exams> findStudentExamsInGroup(Groups group) {
        Transaction tx = null;
        try (Session session = HibernateConnect.openSession()){
            tx = session.beginTransaction();
            Query<Exams> query = session.createQuery("SELECT e FROM Exams e WHERE e.group = :group", Exams.class);
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

}
