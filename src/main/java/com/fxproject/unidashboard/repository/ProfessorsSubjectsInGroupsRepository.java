package com.fxproject.unidashboard.repository;

import com.fxproject.unidashboard.model.Groups;
import com.fxproject.unidashboard.model.Professors;
import com.fxproject.unidashboard.model.ProfessorsSubjectsInGroups;
import com.fxproject.unidashboard.model.Subjects;
import com.fxproject.unidashboard.utils.HibernateConnect;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class ProfessorsSubjectsInGroupsRepository {

    public void save(ProfessorsSubjectsInGroups psig) {
        Transaction tx = null;
        try (Session session = HibernateConnect.openSession()) {
            tx = session.beginTransaction();
            session.merge(psig);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                System.out.println(e.getMessage());
                tx.rollback();
            }
        }
    }

    public void remove(ProfessorsSubjectsInGroups psig) {
        Transaction tx = null;
        Professors professor = psig.getProfessor();
        try (Session session = HibernateConnect.openSession()) {
            tx = session.beginTransaction();
            professor.getPsig().remove(psig);
            session.remove(psig);
//            session.merge(professor);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                System.out.println(e.getMessage());
                tx.rollback();
            }
        }
    }

    public List<Subjects> findProfessorSubjectInGroup(Groups group, Professors professor) {
        Transaction tx = null;
        try (Session session = HibernateConnect.openSession()) {
            tx = session.beginTransaction();
            Query<Subjects> query = session.
                    createQuery("SELECT psig.subject from ProfessorsSubjectsInGroups psig WHERE psig.group = :group AND psig.professor = :professor", Subjects.class);
            query.setParameter("professor", professor);
            query.setParameter("group", group);
            tx.commit();
            return query.getResultList();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                System.out.println(e.getMessage());
                tx.rollback();
            }
            return List.of();
        }
    }
}
