package com.fxproject.unidashboard.repository;

import com.fxproject.unidashboard.model.Addresses;
import com.fxproject.unidashboard.utils.HibernateConnect;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AddressRepository {

    public void save(Addresses address) {
        Transaction tx = null;
        try (Session session = HibernateConnect.openSession()){
            tx = session.beginTransaction();;
            session.persist(address);
            tx.commit();
        } catch (Exception e) {
            if(tx != null && tx.isActive()) {
                tx.rollback();
            }
        }
    }

}
