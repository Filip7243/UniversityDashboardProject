package com.fxproject.unidashboard.repository.impl;

import com.fxproject.unidashboard.model.UniversityAccount;
import com.fxproject.unidashboard.model.UniversityMember;
import com.fxproject.unidashboard.repository.UniversityAccountRepository;
import com.fxproject.unidashboard.utils.HibernateUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class UniversityAccountRepositoryImpl implements UniversityAccountRepository {

    private EntityManager em = HibernateUtils.getEntityManager();
    private static final String DEFAULT_QUERY = "SELECT a FROM UniversityAccount a";
 // todo: make suer that one member can have one account

    @Override
    public void removeWithId(Long id) {
        UniversityAccount universityAccount = findWithId(id).orElseThrow();// todo: custom exception

        var transaction = em.getTransaction();
        try {
            transaction.begin();
            em.remove(universityAccount);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    @Override
    public Optional<UniversityAccount> findWithId(Long id) {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<UniversityAccount> query =
                    em.createQuery(DEFAULT_QUERY + " WHERE a.id = :id", UniversityAccount.class);
            query.setParameter("id", id);
            transaction.commit();
            return Optional.ofNullable(query.getSingleResult());
        } catch (Exception e) {
            transaction.rollback();
            return Optional.empty();
        }
    }

    @Override
    public List<UniversityAccount> findAll() {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<UniversityAccount> query = em.createQuery(DEFAULT_QUERY, UniversityAccount.class);
            transaction.commit();
            return query.getResultList();
        } catch (Exception e) {
            transaction.rollback();
            return List.of();
        }
    }

    @Override
    public Optional<UniversityAccount> findAccountByUniversityEmail(String universityEmail) {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<UniversityAccount> query =
                    em.createQuery(DEFAULT_QUERY + " WHERE a.universityEmail = :universityEmail", UniversityAccount.class);
            query.setParameter("universityEmail", universityEmail);
            transaction.commit();
            return Optional.ofNullable(query.getSingleResult());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
            return Optional.empty();
        }
    }

    @Override
    public List<UniversityAccount> findAllDisabledAccounts() {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<UniversityAccount> query =
                    em.createQuery(DEFAULT_QUERY + " WHERE a.isEnabled = false", UniversityAccount.class);
            transaction.commit();
            return query.getResultList();
        } catch (Exception e) {
            transaction.rollback();
            return List.of();
        }
    }

    @Override
    public List<UniversityAccount> findAllEnableAccounts() {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<UniversityAccount> query =
                    em.createQuery(DEFAULT_QUERY + " WHERE a.isEnabled = true", UniversityAccount.class);
            transaction.commit();
            return query.getResultList();
        } catch (Exception e) {
            transaction.rollback();
            return List.of();
        }
    }

    public Optional<UniversityAccount> findUniversityAccountByMember(UniversityMember member) {
        var transaction = em.getTransaction();

        try {
            transaction.begin();
            TypedQuery<UniversityAccount> query =
                    em.createQuery(DEFAULT_QUERY + " WHERE a.member = :member", UniversityAccount.class);
            query.setParameter("member", member);
            transaction.commit();
            return Optional.ofNullable(query.getSingleResult());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
            return Optional.empty();
        }
    }
}
