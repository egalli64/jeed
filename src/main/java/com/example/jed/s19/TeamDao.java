package com.example.jed.s19;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import com.example.jed.dao.JpaUtil;

public class TeamDao {
    public List<TeamMToM> readAllLazy() {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            String jpql = "SELECT t FROM TeamMToM t";
            return em.createQuery(jpql, TeamMToM.class).getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Optional<TeamMToM> readEager(int id) {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            String jpql = "SELECT e FROM TeamMToM e JOIN FETCH e.coders WHERE e.id = " + id;
            List<TeamMToM> teams = em.createQuery(jpql, TeamMToM.class).getResultList();
            return teams.isEmpty() ? Optional.empty() : Optional.of(teams.get(0));
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TeamMToM> readAllEager() {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            String jpql = "SELECT DISTINCT e FROM TeamMToM e JOIN FETCH e.coders";
            return em.createQuery(jpql, TeamMToM.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Optional<TeamMToM> read(int id) {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            return Optional.ofNullable(em.find(TeamMToM.class, id));
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
