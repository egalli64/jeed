package com.example.jeed.s18;

import java.util.List;

import jakarta.persistence.EntityManager;

import com.example.jeed.dao.JpaUtil;

public class RegionDaoEager {
    public List<Region1ToMEager> readAll() {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            String jpql = "SELECT r FROM Region1ToMEager r";
            return em.createQuery(jpql, Region1ToMEager.class).getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
