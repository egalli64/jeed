package dd.mhja.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegionDao {
    static final Logger LOG = LoggerFactory.getLogger(RegionDao.class);

    public List<Region> readAll() {
        EntityManager em = null;

        try {
            em = HibUtil.getEntityManager();
            return em.createQuery("SELECT r FROM Region r", Region.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Optional<Region> read(int id) {
        EntityManager em = null;

        try {
            em = HibUtil.getEntityManager();
            return Optional.ofNullable(em.find(Region.class, id));
        } finally {
            em.close();
        }
    }

    public boolean create(Region region) {
        EntityManager em = null;

        try {
            em = HibUtil.getEntityManager();
            EntityTransaction et = em.getTransaction();
            et.begin();
            em.persist(region);
            et.commit();
            return true;
        } catch (Exception ex) {
            LOG.warn("Can't persist region", ex);
            return false;
        } finally {
            em.close();
        }
    }

    public boolean update(Region region) {
        EntityManager em = null;

        try {
            em = HibUtil.getEntityManager();
            EntityTransaction et = em.getTransaction();
            et.begin();
            em.merge(region);
            et.commit();
            return true;
        } catch (Exception ex) {
            LOG.warn("Can't merge region", ex);
            return false;
        } finally {
            em.close();
        }
    }

    public boolean delete(Integer id) {
        EntityManager em = null;

        try {
            em = HibUtil.getEntityManager();
            EntityTransaction et = em.getTransaction();
            et.begin();
            Region region = em.find(Region.class, id);
            if (region != null) {
                em.remove(region);
                et.commit();
                return true;
            } else {
                LOG.info("Can't remove missing region " + id);
                et.rollback();
                return false;
            }
        } catch (Exception ex) {
            LOG.warn("Can't remove region " + id, ex);
            return false;
        } finally {
            em.close();
        }
    }
}
