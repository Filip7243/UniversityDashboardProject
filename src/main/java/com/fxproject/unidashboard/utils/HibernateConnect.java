package com.fxproject.unidashboard.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConnect {

    private static final SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();

    private HibernateConnect(){}

    public static Session openSession() {
        return SESSION_FACTORY.openSession();
    }
}
