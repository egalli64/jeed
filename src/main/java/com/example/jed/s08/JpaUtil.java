package com.example.jed.s08;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class JpaUtil {
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("me");

    public static EntityManager createEntityManager() {
        return factory.createEntityManager();
    }
}
