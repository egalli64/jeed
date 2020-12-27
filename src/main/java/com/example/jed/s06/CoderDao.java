package com.example.jed.s06;

import java.util.List;

import org.hibernate.Session;

import com.example.jed.s05.Coder05;

public class CoderDao {
    public List<Coder05> getAll() {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("from Coder05", Coder05.class).list();
        }
    }
}
