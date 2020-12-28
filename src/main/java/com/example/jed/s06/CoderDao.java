package com.example.jed.s06;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.example.jed.s05.Coder05;

public class CoderDao {
    public List<Coder05> getAll() {
        try (Session session = HibernateUtil.getSession()) {
            Query<Coder05> query = session.createQuery("from Coder05", Coder05.class);
            return query.list();
        }
    }
}
