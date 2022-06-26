package com.example.jeed.s19;

import java.util.List;

import jakarta.persistence.EntityManager;

import com.example.jeed.dao.JpaUtil;

public class ServiceDao {
    public List<ServiceMToM> readAllLazy() {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            String jpql = "SELECT s FROM ServiceMToM s";
            return em.createQuery(jpql, ServiceMToM.class).getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ServiceMToM> readAllEager() {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            String jpql = "SELECT DISTINCT s FROM ServiceMToM s JOIN FETCH s.cars";
            return em.createQuery(jpql, ServiceMToM.class).getResultList();
        } finally {
            em.close();
        }
    }
}
