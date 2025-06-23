/*
 * Introduction to Hibernate - JEE ORM
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.svc;

import com.example.jeed.dao.Car;
import com.example.jeed.dao.CarDao;
import com.example.jeed.dao.Employee4Car;
import com.example.jeed.dao.Employee4CarDao;

import jakarta.persistence.EntityManagerFactory;

public class EmployeeCarService {

    /**
     * Constructor - inject the required dependencies
     */
    public EmployeeCarService(Employee4CarDao employeeDao, CarDao carDao, EntityManagerFactory emf) {
    }

    public void create(Car car, Employee4Car employee) {
    }
}
