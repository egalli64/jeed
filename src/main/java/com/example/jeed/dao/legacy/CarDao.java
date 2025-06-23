/*
 * Introduction to Hibernate - JEE ORM
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.dao.legacy;

import com.example.jeed.util.EntityManagerService;

public class CarDao extends Dao<Car, Integer> {
    /**
     * Ctor
     * 
     * @param service access provider to the entity manager
     */
    public CarDao(EntityManagerService service) {
        super(Car.class, service);
    }
}
