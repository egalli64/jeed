package dd.mhja.dao;

import java.util.List;

import javax.persistence.EntityManager;

public class RegionDao {
    public List<Region> getAll() {
        EntityManager em = null;

        try {
            em = HibUtil.getEntityManager();
            return em.createQuery("SELECT r FROM Region r", Region.class).getResultList();
        } finally {
            em.close();            
        }
    }
}
