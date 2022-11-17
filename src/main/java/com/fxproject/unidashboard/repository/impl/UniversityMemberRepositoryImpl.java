package com.fxproject.unidashboard.repository.impl;

import com.fxproject.unidashboard.model.UniversityAccount;
import com.fxproject.unidashboard.model.UniversityMember;
import com.fxproject.unidashboard.repository.UniversityMemberRepository;
import com.fxproject.unidashboard.utils.HibernateUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class UniversityMemberRepositoryImpl implements UniversityMemberRepository {

    private final EntityManager em = HibernateUtils.getEntityManager();
    private static final String DEFAULT_QUERY = "SELECT m FROM UniversityMember m";

    @Override
    public void removeWithId(Long id) {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            em.remove(findWithId(id));
            transaction.commit();
        } catch (Exception e) {
            System.out.println("MSG = " + e.getMessage());
            transaction.rollback();
        }
    }

    @Override
    public Optional<UniversityMember> findWithId(Long id) {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<UniversityMember> query = em.createQuery(DEFAULT_QUERY + " WHERE m.id = :id", UniversityMember.class);
            query.setParameter("id", id);
            transaction.commit();
            return Optional.ofNullable(query.getSingleResult());
        } catch (Exception e) {
            System.out.println("MSG = " + e.getMessage());
            transaction.rollback();
            return Optional.empty();
        }
    }

    @Override
    public List<UniversityMember> findAll() {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<UniversityMember> query = em.createQuery(DEFAULT_QUERY, UniversityMember.class);
            transaction.commit();
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("MSG = " + e.getMessage());
            transaction.rollback();
            return List.of();
        }
    }

    @Override
    public Optional<UniversityMember> findByPesel(String pesel) {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<UniversityMember> query = em.createQuery(DEFAULT_QUERY + " WHERE m.pesel = :pesel", UniversityMember.class);
            query.setParameter("pesel", pesel);
            transaction.commit();
            return Optional.ofNullable(query.getSingleResult());
        } catch (Exception e) {
            System.out.println("MSG = " + e.getMessage());
            transaction.rollback();
            return Optional.empty();
        }
    }

    @Override
    public Optional<UniversityMember> findByEmail(String email) {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<UniversityMember> query = em.createQuery(DEFAULT_QUERY + " WHERE m.email = :email", UniversityMember.class);
            query.setParameter("email", email);
            transaction.commit();
            return Optional.ofNullable(query.getSingleResult());
        } catch (Exception e) {
            System.out.println("MSG = " + e.getMessage());
            transaction.rollback();
            return Optional.empty();
        }

    }

    @Override
    public Optional<UniversityMember> findByUniversityEmail(String universityEmail) {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<UniversityMember> query =
                    em.createQuery(DEFAULT_QUERY + " WHERE m.universityAccount.universityEmail = :universityEmail", UniversityMember.class);
            query.setParameter("universityEmail", universityEmail);
            transaction.commit();
            return Optional.ofNullable(query.getSingleResult());
        } catch (Exception e) {
            System.out.println("MSG = " + e.getMessage());
            transaction.rollback();
            return Optional.empty();
        }
    }

    @Override
    public List<UniversityMember> findByFirstNameAndLastName(String firstName, String lastName) {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<UniversityMember> query =
                    em.createQuery(DEFAULT_QUERY + " WHERE m.firstName = :firstName AND m.lastName = :lastName", UniversityMember.class);
            query.setParameter("firstName", firstName);
            query.setParameter("lastName", lastName);
            transaction.commit();
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("MSG = " + e.getMessage());
            transaction.rollback();
            return List.of();
        }
    }

    @Override
    public Optional<UniversityAccount> findUniversityMemberByUniversityAccount(UniversityAccount account) {
        return Optional.empty(); //todo: implementation
    }
}
