package com.example.jed.s19;

import java.util.List;

import javax.persistence.EntityManager;

import com.example.jed.dao.JpaUtil;

public class CoderDao {
    public List<CoderMToM> readAllEager() {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            String jpql = "SELECT DISTINCT e FROM CoderMToM e JOIN FETCH e.teams";
            return em.createQuery(jpql, CoderMToM.class).getResultList();
        } finally {
            em.close();
        }
    }
}
