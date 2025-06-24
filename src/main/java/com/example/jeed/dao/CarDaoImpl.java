/*
 * Introduction to Hibernate - JEE ORM
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.dao;

public class CarDaoImpl extends AbstractGenericDao<Car, Integer> implements CarDao {

    public CarDaoImpl() {
        super(Car.class);
    }
}