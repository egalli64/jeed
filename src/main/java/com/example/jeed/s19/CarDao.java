package com.example.jeed.s19;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import com.example.jeed.dao.JpaUtil;

public class CarDao {
    public List<CarMToM> readAllLazy() {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            String jpql = "SELECT c FROM CarMToM c";
            return em.createQuery(jpql, CarMToM.class).getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Optional<CarMToM> readEager(int id) {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            String jpql = "SELECT c FROM CarMToM c JOIN FETCH c.services WHERE c.id = " + id;
            List<CarMToM> cars = em.createQuery(jpql, CarMToM.class).getResultList();
            return cars.isEmpty() ? Optional.empty() : Optional.of(cars.get(0));
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CarMToM> readAllEager() {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            String jpql = "SELECT DISTINCT c FROM CarMToM c JOIN FETCH c.services";
            return em.createQuery(jpql, CarMToM.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Optional<CarMToM> read(int id) {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            return Optional.ofNullable(em.find(CarMToM.class, id));
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
