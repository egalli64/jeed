package dd.mhja.s19;

import java.util.List;

import javax.persistence.EntityManager;

import dd.mhja.dao.Dao;
import dd.mhja.dao.JpaUtil;

public class RegionDao extends Dao<Region, Integer> {
    public RegionDao() {
        super(Region.class);
    }

    public List<Region> readAllLazy() {
        return readAll();
    }

    public List<Region> readAllEager() {
        EntityManager em = null;

        try {
            em = JpaUtil.getEntityManager();
            String jpql = "SELECT e FROM RegionS19 e JOIN FETCH e.countries";
            return em.createQuery(jpql, Region.class).getResultList();
        } finally {
            em.close();
        }
    }

}
