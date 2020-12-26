package com.example.jed.s20;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.jed.dao.Dao;
import com.example.jed.dao.JpaUtil;

public class TeamDao extends Dao<Team, Integer> {
    private static final Logger log = LoggerFactory.getLogger(TeamDao.class);

    public TeamDao() {
        super(Team.class);
    }

    public List<Team> readAllLazy() {
        return readAll();
    }

    public Optional<Team> readEager(int id) {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            String jpql = "SELECT e FROM TeamS20 e JOIN FETCH e.coders WHERE e.id = " + id;
            List<Team> teams = em.createQuery(jpql, Team.class).getResultList();
            return teams.isEmpty() ? Optional.empty() : Optional.of(teams.get(0));
        } catch (Exception ex) {
            log.error("Can't create query: " + ex.getMessage());
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Team> readAllEager() {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            String jpql = "SELECT DISTINCT e FROM TeamS20 e JOIN FETCH e.coders";
            return em.createQuery(jpql, Team.class).getResultList();
        } finally {
            em.close();
        }
    }
}
