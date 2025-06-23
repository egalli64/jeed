/*
 * Introduction to Hibernate - JEE ORM
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.dao.legacy;

import com.example.jeed.util.EntityManagerService;

/**
 * DAO for Region entity in one to many relation with Country (EAGER)
 */
public class RegionEager4CountryDao extends Dao<RegionEager4Country, Integer> {
    /**
     * Due to type erasure, the actual JavaBean type has to be passed to the
     * superclass
     * 
     * @param ems required to access the entity manager factory
     */
    public RegionEager4CountryDao(EntityManagerService ems) {
        super(RegionEager4Country.class, ems);
    }
}
