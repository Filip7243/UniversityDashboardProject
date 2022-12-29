package com.fxproject.unidashboard.repository;

import com.fxproject.unidashboard.model.Lectures;
import com.fxproject.unidashboard.model.Person;
import com.fxproject.unidashboard.utils.HibernateConnect;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class LectureRepository {

    public List<Lectures> findAllLectures() {
        try (Session session = HibernateConnect.openSession()) {
            Query<Lectures> lectures = session.createQuery("SELECT l FROM Lectures l", Lectures.class);
            return lectures.getResultList();
        } catch (Exception e) {
            return List.of();
        }
    }

}
