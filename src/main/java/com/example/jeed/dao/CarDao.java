package com.example.jeed.dao;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.persistence.EntityManager;

public class CarDao extends Dao<Car, Integer> {
    private static final Logger log = LogManager.getLogger(CarDao.class);

    /**
     * Ctor
     * 
     * @param service access provider to the entity manager
     */
    public CarDao(EntityManagerService service) {
        super(Car.class, service);
    }

    public List<Car> readAll() {
        log.traceEntry();
        EntityManager em = service.createEntityManager();

        try {
            return em.createQuery("SELECT c FROM Car c", Car.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Optional<Car> read(Integer id) {
        log.traceEntry("{}", id);
        EntityManager em = service.createEntityManager();

        try {
            return Optional.ofNullable(em.find(Car.class, id));
        } finally {
            em.close();
        }
    }
}
