/*
 * Introduction to Hibernate - JEE ORM
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.svc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.jeed.dao.Car;
import com.example.jeed.dao.CarDao;
import com.example.jeed.dao.Employee4Car;
import com.example.jeed.dao.Employee4CarDao;
import com.example.jeed.util.EntityManagerService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class EmployeeCarService {
    private static final Logger log = LogManager.getLogger(EmployeeCarService.class);

    private CarDao carDao;
    private Employee4CarDao employeeDao;
    private EntityManagerService entityManagerService;

    public EmployeeCarService(CarDao carDao, Employee4CarDao employeeDao, EntityManagerService entityManagerService) {
        this.carDao = carDao;
        this.employeeDao = employeeDao;
        this.entityManagerService = entityManagerService;
    }

    public void create(Employee4Car employee, Car car) {
        log.traceEntry();

        EntityTransaction transaction = null;

        try (EntityManager em = entityManagerService.createEntityManager()) {
            carDao.setEm(em);
            employeeDao.setEm(em);

            transaction = em.getTransaction();
            transaction.begin();

            car.setEmployee(employee);
            employee.setCar(car);

            employeeDao.create(employee);
            carDao.create(car);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
                log.debug("Rollback: {}", e.getMessage());
            }
            throw new RuntimeException("Failure creating employee + car", e);
        } finally {
            carDao.removeEm();
            employeeDao.removeEm();
        }
    }
}
