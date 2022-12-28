/*
 * Introduction to Jakarta Enterprise Edition - JPA on Hibernate
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.dao;

/**
 * DAO for Employee4Car
 */
public class Employee4CarDao extends Dao<Employee4Car, Integer> {
    /**
     * Ctor
     * 
     * @param service access provider to the entity manager
     */
    public Employee4CarDao(EntityManagerService service) {
        super(Employee4Car.class, service);
    }
}
