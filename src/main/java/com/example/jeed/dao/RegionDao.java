/*
 * Introduction to Jakarta Enterprise Edition - JPA on Hibernate
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.dao;

/**
 * DAO for Region
 */
public class RegionDao extends Dao<Region, Integer> {
    /**
     * Due to type erasure, the actual JavaBean type has to be passed to the
     * superclass
     * 
     * @param ems required to access the entity manager factory
     */
    public RegionDao(EntityManagerService ems) {
        super(Region.class, ems);
    }
}
