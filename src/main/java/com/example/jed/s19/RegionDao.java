package com.example.jed.s19;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.jed.dao.Dao;
import com.example.jed.dao.JpaUtil;

public class RegionDao extends Dao<Region, Integer> {
    private static final Logger log = LoggerFactory.getLogger(RegionDao.class);

    public RegionDao() {
        super(Region.class);
    }

    public List<Region> readAllLazy() {
        return readAll();
    }

    public List<Region> readAllEager() {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            String jpql = "SELECT e FROM RegionS19 e JOIN FETCH e.countries";
            return em.createQuery(jpql, Region.class).getResultList();
        } catch (Exception ex) {
            log.error("Can't create query: " + ex.getMessage());
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
