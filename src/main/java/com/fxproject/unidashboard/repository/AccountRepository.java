package com.fxproject.unidashboard.repository;

import com.fxproject.unidashboard.model.Person;
import com.fxproject.unidashboard.model.UniversityAccounts;
import com.fxproject.unidashboard.utils.HibernateConnect;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Optional;

public class AccountRepository {

    public void save(UniversityAccounts account) {
        Transaction tx = null;
        try (Session session = HibernateConnect.openSession()){
            tx = session.beginTransaction();
            session.persist(account);
            tx.commit();
        } catch (Exception e) {
            if(tx != null && tx.isActive()) {
                tx.rollback();
            }
        }
    }
    public Optional<UniversityAccounts> findAccountWithPersonId(Long personId) {
        Transaction tx = null;
        try (Session session = HibernateConnect.openSession()) {
            tx = session.beginTransaction();
            Query<UniversityAccounts> query =
                    session.createQuery("SELECT a FROM UniversityAccounts a WHERE a.person.id = :personId", UniversityAccounts.class);
            query.setParameter("personId", personId);
            tx.commit();
            return Optional.ofNullable(query.getSingleResult());
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            return Optional.empty();
        }
    }

    public void updateAccountWithPersonId(UniversityAccounts updatedAcc, Long personId) {
        UniversityAccounts acc = findAccountWithPersonId(personId).orElseThrow();
        Transaction tx = null;
        try (Session session = HibernateConnect.openSession()) {
            tx = session.beginTransaction();
            acc.setUniversityEmail(updatedAcc.getUniversityEmail());
            acc.setPassword(updatedAcc.getPassword());
            acc.setRole(updatedAcc.getRole());
            session.merge(acc);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        }
    }
}
