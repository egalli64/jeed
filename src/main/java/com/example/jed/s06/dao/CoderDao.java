package com.example.jed.s06.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class CoderDao {
    public List<Coder06> getAll() {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            // equivalent to "select c from Coder06 c"
            String jpql = "FROM Coder06";
            TypedQuery<Coder06> tq = em.createQuery(jpql, Coder06.class);
            List<Coder06> result = tq.getResultList();
            return result;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
