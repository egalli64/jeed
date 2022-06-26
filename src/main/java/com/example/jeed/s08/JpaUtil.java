package com.example.jeed.s08;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public final class JpaUtil {
    private JpaUtil() {
    }

    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("hron");

    public static EntityManager createEntityManager() {
        return factory.createEntityManager();
    }
}
