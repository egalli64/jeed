package com.example.jed.s08;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.example.jed.s05.Coder05;

public class CoderDao {
    public List<Coder05> getAll() {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            // equivalent to "select c from Coder05 c"
            String jpql = "FROM Coder05";
            TypedQuery<Coder05> tq = em.createQuery(jpql, Coder05.class);
            List<Coder05> result = tq.getResultList();
            return result;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
