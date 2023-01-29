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
                tx.rollback();
            }
        }
    }

    public void remove(ProfessorsSubjectsInGroups psig) {
        Transaction tx = null;
        Optional<Professors> professorFromPSIG = getProfessorFromPSIG(psig);
        Professors professor = null;
        if(professorFromPSIG.isPresent()) {
            professor = professorFromPSIG.get();
        }
        try (Session session = HibernateConnect.openSession()) {
            tx = session.beginTransaction();
            List<ProfessorsSubjectsInGroups> professorPSIG = findProfessorPSIG(professor);
            professorPSIG.remove(psig);
            session.remove(psig);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
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
                tx.rollback();
            }
            return List.of();
        }
    }

    public List<ProfessorsSubjectsInGroups> findProfessorPSIG(Professors professor) {
        Transaction tx = null;
        try (Session session = HibernateConnect.openSession()) {
            tx = session.beginTransaction();
            Query<ProfessorsSubjectsInGroups> query = session.
                    createQuery("SELECT psig from ProfessorsSubjectsInGroups psig WHERE psig.professor = :professor", ProfessorsSubjectsInGroups.class);
            query.setParameter("professor", professor);
            tx.commit();
            return query.getResultList();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            return List.of();
        }
    }

    public Optional<Professors> getProfessorFromPSIG(ProfessorsSubjectsInGroups psig) {
        Transaction tx = null;
        try (Session session = HibernateConnect.openSession()) {
            tx = session.beginTransaction();
            Query<Professors> query = session.
                    createQuery("SELECT psig.professor from ProfessorsSubjectsInGroups psig WHERE psig = :psig", Professors.class);
            query.setParameter("psig", psig);
            tx.commit();
            return Optional.ofNullable(query.getSingleResult());
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            return Optional.empty();
        }
    }
}
