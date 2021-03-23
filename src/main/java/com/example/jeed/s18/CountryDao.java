package com.example.jeed.s18;

import java.util.List;

import javax.persistence.EntityManager;

import com.example.jeed.dao.JpaUtil;

public class CountryDao {
    public List<CountryMTo1> readAll() {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            String jpql = "SELECT c FROM CountryMTo1 c";
            return em.createQuery(jpql, CountryMTo1.class).getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
