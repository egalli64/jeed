package com.example.jeed.s08;

import java.util.List;
import com.example.jeed.s05.EmployeePlain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class EmployeeDao {
    public List<EmployeePlain> getAll() {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            // equivalent to "select e from EmployeePlain e"
            String jpql = "FROM EmployeePlain";
            TypedQuery<EmployeePlain> tq = em.createQuery(jpql, EmployeePlain.class);
            List<EmployeePlain> result = tq.getResultList();
            return result;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
