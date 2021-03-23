package com.example.jeed.dao;

import java.util.List;

import javax.persistence.EntityManager;

public class EmployeeDao extends Dao<Employee, Integer> {
    public EmployeeDao() {
        super(Employee.class);
    }

    public List<Employee> readAllOrderBySalary(boolean asc) {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            String jpql = "SELECT e FROM Employee e ORDER BY e.salary " + (asc ? "ASC" : "DESC");
            return em.createQuery(jpql, Employee.class).getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Employee> readSalaryRange(double low, double high) {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            String jpql = "SELECT e FROM Employee e WHERE e.salary BETWEEN ?1 AND ?2 ORDER BY e.salary DESC";
            var query = em.createQuery(jpql, Employee.class);
            query.setParameter(1, low);
            query.setParameter(2, high);
            return query.getResultList();
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
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
