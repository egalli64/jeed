package com.example.jeed.s08;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class JpaUtil {
    private JpaUtil() {
    }

    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("hron");

    public static EntityManager createEntityManager() {
        return factory.createEntityManager();
    }
}
