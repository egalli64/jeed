package com.example.jeed.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnitUtil;

public final class JpaUtil {
    private JpaUtil() {
    }

    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("me");

    public static EntityManager createEntityManager() {
        return factory.createEntityManager();
    }

    public static PersistenceUnitUtil getPersistenceUnitUtil() {
        return factory.getPersistenceUnitUtil();
    }
}
