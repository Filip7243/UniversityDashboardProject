package com.fxproject.unidashboard.utils;

import com.fxproject.unidashboard.HelloApplication;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Objects;

public class HibernateConnect {

    private static final SessionFactory SESSION_FACTORY;

    static {
        Configuration configuration = new Configuration();
        SESSION_FACTORY = configuration.configure("hibernate.cfg.xml").buildSessionFactory();

    }

    private HibernateConnect() {
    }

    public static Session openSession() {
        return SESSION_FACTORY.openSession();
    }
}
