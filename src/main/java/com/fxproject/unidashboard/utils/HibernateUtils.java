package com.fxproject.unidashboard.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateUtils {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("main-persistence-unit");
    private static EntityManager em;

    static {
        em = emf.createEntityManager();
    }

    public static EntityManager getEntityManager() {
        return em;
    }
}
