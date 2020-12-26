package dd.mhja.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegionDaoSimple {
    static private final Logger log = LoggerFactory.getLogger(RegionDaoSimple.class);

    public boolean create(RegionSimple entity) {
        EntityManager em = null;

        try {
            log.trace("enter");
            em = JpaUtil.createEntityManager();
            EntityTransaction et = em.getTransaction();
            et.begin();
            em.persist(entity);
            et.commit();
            return true;
        } catch (Exception ex) {
            log.warn("Can't persist entity", ex);
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public RegionSimple read(Integer id) {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            return em.find(RegionSimple.class, null);
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
