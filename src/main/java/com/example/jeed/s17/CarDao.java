package com.example.jeed.s17;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.jeed.dao.JpaUtil;

import jakarta.persistence.EntityManager;

public class CarDao {
    static private final Logger log = LogManager.getLogger(CarDao.class);

    public List<Car1To1> readAll() {
        log.traceEntry();
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            String jpql = "SELECT c FROM Car1To1 c";
            return em.createQuery(jpql, Car1To1.class).getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Optional<Car1To1> read(int id) {
        log.traceEntry();
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            return Optional.ofNullable(em.find(Car1To1.class, id));
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
