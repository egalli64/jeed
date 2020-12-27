package com.example.jed.s05.dao;

import java.util.List;

import org.hibernate.Session;

public class Coder05Dao {
    public List<Coder05> getAll() {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("from Coder05", Coder05.class).list();
        }
    }
}
