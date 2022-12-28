package com.example.jeed.dao;

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
