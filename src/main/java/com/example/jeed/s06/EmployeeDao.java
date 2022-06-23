package com.example.jeed.s06;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.example.jeed.s05.EmployeePlain;

public class EmployeeDao {
    public List<EmployeePlain> getAll() {
        try (Session session = HibernateUtil.getSession()) {
            // explicit HQL query is "select e from EmployeePlain e"
            Query<EmployeePlain> query = session.createQuery("from EmployeePlain", EmployeePlain.class);
            return query.list();
        }
    }
}
