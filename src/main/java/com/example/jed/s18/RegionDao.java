package com.example.jed.s18;

import java.util.List;

import javax.persistence.EntityManager;

import com.example.jed.dao.JpaUtil;

public class RegionDao {
    public List<Region1ToM> readAllLazy() {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            String jpql = "SELECT r FROM Region1ToM r";
            return em.createQuery(jpql, Region1ToM.class).getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Region1ToM> readAllEager() {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            String jpql = "SELECT DISTINCT e FROM Region1ToM e JOIN FETCH e.countries";
            return em.createQuery(jpql, Region1ToM.class).getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
