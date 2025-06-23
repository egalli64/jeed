/*
 * Introduction to Hibernate - JEE ORM
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.dao.legacy;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import org.hibernate.Session;

import jakarta.persistence.EntityManager;

/**
 * DBMS name checker
 */
public class DBInfoService {
    private final EntityManagerService service;

    public DBInfoService(EntityManagerService service) {
        this.service = service;
    }

    public String getName() {
        try (EntityManager em = service.createEntityManager()) {
            Session session = em.unwrap(Session.class);
            DatabaseMetaData metaData = session.doReturningWork(conn -> conn.getMetaData());
            return metaData.getDatabaseProductName();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
