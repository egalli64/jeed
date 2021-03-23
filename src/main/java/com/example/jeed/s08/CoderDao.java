package com.example.jeed.s08;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.example.jeed.s05.CoderPlain;

public class CoderDao {
    public List<CoderPlain> getAll() {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            // equivalent to "select c from CoderPlain c"
            String jpql = "FROM CoderPlain";
            TypedQuery<CoderPlain> tq = em.createQuery(jpql, CoderPlain.class);
            List<CoderPlain> result = tq.getResultList();
            return result;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
