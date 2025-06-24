/*
 * Introduction to Hibernate - JEE ORM
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.dao;

public class Employee4CarDaoImpl extends AbstractGenericDao<Employee4Car, Integer> implements Employee4CarDao {
    public Employee4CarDaoImpl() {
        super(Employee4Car.class);
    }
}
