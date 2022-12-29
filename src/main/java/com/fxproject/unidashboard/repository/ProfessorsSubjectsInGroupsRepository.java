package com.fxproject.unidashboard.repository;

import com.fxproject.unidashboard.model.Groups;
import com.fxproject.unidashboard.model.Professors;
import com.fxproject.unidashboard.model.ProfessorsSubjectsInGroups;
import com.fxproject.unidashboard.model.Subjects;
import com.fxproject.unidashboard.utils.HibernateConnect;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
            session.remove(psig);
            professor.getPsig().remove(psig);
            session.merge(professor);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                System.out.println(e.getMessage());
                tx.rollback();
            }
        }
    }

//    public Optional<ProfessorsSubjectsInGroups> findPsigWithSubjectAndGroup(Subjects subject, Groups group) {
//        Transaction tx = null;
//        try (Session session = HibernateConnect.openSession()) {
//            tx = session.beginTransaction();
//            session.c
//            tx.commit();
//            return Optional.ofNullable();
//        } catch (Exception e) {
//            if (tx != null && tx.isActive()) {
//                System.out.println(e.getMessage());
//                tx.rollback();
//            }
//            return Optional.empty();
//        }
//    }
}
