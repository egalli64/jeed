package dd.mhja.s20;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import dd.mhja.dao.Dao;
import dd.mhja.dao.HibUtil;

public class TeamDao extends Dao<Team, Integer> {
    public TeamDao() {
        super(Team.class);
    }

    public List<Team> readAllLazy() {
        return readAll();
    }

    public Optional<Team> readEager(int id) {
        EntityManager em = null;

        try {
            em = HibUtil.getEntityManager();
            String jpql = "SELECT e FROM TeamS20 e JOIN FETCH e.coders WHERE e.id = " + id;
            List<Team> teams = em.createQuery(jpql, Team.class).getResultList();
            return teams.isEmpty() ? Optional.empty() : Optional.of(teams.get(0));
        } finally {
            em.close();
        }
    }

    public List<Team> readAllEager() {
        EntityManager em = null;

        try {
            em = HibUtil.getEntityManager();
            String jpql = "SELECT DISTINCT e FROM TeamS20 e JOIN FETCH e.coders";
            return em.createQuery(jpql, Team.class).getResultList();
        } finally {
            em.close();
        }
    }
}
