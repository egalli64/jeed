package com.example.jeed.dao;

import java.util.List;

import jakarta.persistence.EntityManager;

public class CoderDao extends Dao<Coder, Integer> {
    public CoderDao() {
        super(Coder.class);
    }

    public List<Coder> readAll() {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            String jpql = "SELECT c FROM Coder c where job_id = " + Coder.JOB_ID;
            return em.createQuery(jpql, Coder.class).getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
