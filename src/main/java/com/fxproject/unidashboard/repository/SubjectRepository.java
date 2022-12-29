package com.fxproject.unidashboard.repository;

import com.fxproject.unidashboard.model.Professors;
import com.fxproject.unidashboard.model.Subjects;
import com.fxproject.unidashboard.utils.HibernateConnect;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class SubjectRepository {

    public List<Subjects> findAllSubjects() {
        try (Session session = HibernateConnect.openSession()) {
            Query<Subjects> subjects = session.createQuery("SELECT s FROM Subjects s", Subjects.class);
            return subjects.getResultList();
        } catch (Exception e) {
            return List.of();
        }
    }
}
