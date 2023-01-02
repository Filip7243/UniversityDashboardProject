package com.fxproject.unidashboard.utils;

import com.fxproject.unidashboard.model.Person;
import org.h2.engine.Session;

public final class UserSession {

    private static UserSession session;
    private Person person;

    private UserSession(Person person) {
        this.person = person;
    }

    public static UserSession getSession(Person person) {
        if(session == null) {
            session = new UserSession(person);
        }
        return session;
    }
    public static UserSession getSession() {
        if(session != null) {
            return session;
        }
        return null;
    }

    public Person getPerson() {
        return person;
    }

    public void cancelSession() {
        person = null;
        session = null;
    }
}
