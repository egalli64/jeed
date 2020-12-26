package com.example.jed.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmployeeDao extends Dao<Employee, Integer> {
    static private final Logger log = LoggerFactory.getLogger(EmployeeDao.class);

    public EmployeeDao() {
        super(Employee.class);
    }

    public List<Employee> readSalaryRange(double low, double high) {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            String jpql = "SELECT e from Employee e where e.salary > ?1 and e.salary < ?2";
            var query = em.createQuery(jpql, Employee.class);
            query.setParameter(1, low);
            query.setParameter(2, high);
            return query.getResultList();
        } catch (Exception ex) {
            log.error("Can't create query: " + ex.getMessage());
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Employee> readSalaryTop(double low) {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            var query = em.createNamedQuery("getTopSalaried", Employee.class);
            query.setParameter("low", low);
            return query.getResultList();
        } catch (Exception ex) {
            log.error("Can't create query: " + ex.getMessage());
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
