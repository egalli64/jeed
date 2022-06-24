package com.example.jeed.s11;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnitUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;

import com.example.jeed.dao.JpaUtil;
import com.example.jeed.s05.EmployeePlain;

public class EmployeeDao {
    private static final Logger log = LogManager.getLogger(EmployeeDao.class);

    public Optional<EmployeePlain> read(long id) {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            EmployeePlain employee = em.find(EmployeePlain.class, id);
            return Optional.ofNullable(employee);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public EmployeePlain readRaw(long id) {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            return em.find(EmployeePlain.class, id);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Optional<EmployeePlain> readAndIncrease(long id, int delta) {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = JpaUtil.createEntityManager();
            tx = em.getTransaction();
            EmployeePlain employee = em.find(EmployeePlain.class, id);
            tx.begin();
            employee.setSalary(employee.getSalary() + delta);
            tx.commit();
            return Optional.of(employee);
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Optional<EmployeePlain> readProxy(long id) {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            EmployeePlain employee = em.getReference(EmployeePlain.class, id);

            PersistenceUnitUtil uu = JpaUtil.getPersistenceUnitUtil();
            if (!uu.isLoaded(employee)) {
                log.trace("Converting from proxy to real employee");
                Hibernate.initialize(employee);
            }

            return Optional.ofNullable(employee);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Optional<EmployeePlain> readRefresh(long id) {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            EmployeePlain employee = em.find(EmployeePlain.class, id);

            employee.setLastName("Something else");

            log.debug("Employee is: " + employee);
            em.refresh(employee);
            log.debug("Coder is: " + employee);

            return Optional.ofNullable(employee);
        } catch (Exception ex) {
            log.error("Find + refresh failure", ex);
            return Optional.empty();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
