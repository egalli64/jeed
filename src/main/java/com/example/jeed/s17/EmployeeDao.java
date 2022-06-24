package com.example.jeed.s17;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.jeed.dao.JpaUtil;

public class EmployeeDao {
    static private final Logger log = LogManager.getLogger(EmployeeDao.class);

    public List<Employee1To1> readAll() {
        log.traceEntry();
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            String jpql = "SELECT t FROM Employee1To1 t";
            return em.createQuery(jpql, Employee1To1.class).getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Optional<Employee1To1> read(int id) {
        log.traceEntry();
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            return Optional.ofNullable(em.find(Employee1To1.class, id));
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
