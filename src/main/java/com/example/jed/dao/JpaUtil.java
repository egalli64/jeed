package com.example.jed.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class JpaUtil {
    private static EntityManagerFactory factory;

    static {
        try {
            factory = Persistence.createEntityManagerFactory("me");
        } catch (Throwable th) {
            throw new IllegalStateException("Can't create EntityManagerFactory", th);
        }
    }

    public static EntityManager createEntityManager() {
        return factory.createEntityManager();
    }
}
