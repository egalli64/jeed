/*
 * Introduction to Hibernate - JEE ORM
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.dao.legacy;

/**
 * DAO for Country
 */
public class CountryDao extends Dao<Country, String> {
    /**
     * Ctor
     * 
     * @param service access provider to the entity manager
     */
    public CountryDao(EntityManagerService service) {
        super(Country.class, service);
    }
}
