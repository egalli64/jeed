package dd.mhja.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Dao<T, U> {
    static final Logger LOG = LoggerFactory.getLogger(Dao.class);

    private final Class<T> clazz;
    
    // workaround for template type erasure
    protected Dao(Class<T> clazz) {
        this.clazz = clazz;
    }
    
    public List<T> readAll() {
        EntityManager em = null;

        try {
            em = HibUtil.getEntityManager();
            String jpql = "SELECT e FROM " + clazz.getSimpleName() + " e";
            return em.createQuery(jpql, clazz).getResultList();
        } finally {
            em.close();
        }
    }

    public Optional<T> read(U id) {
        EntityManager em = null;

        try {
            em = HibUtil.getEntityManager();
            return Optional.ofNullable(em.find(clazz, id));
        } finally {
            em.close();
        }
    }

    public boolean create(T entity) {
        EntityManager em = null;

        try {
            em = HibUtil.getEntityManager();
            EntityTransaction et = em.getTransaction();
            et.begin();
            em.persist(entity);
            et.commit();
            return true;
        } catch (Exception ex) {
            LOG.warn("Can't persist entity", ex);
            return false;
        } finally {
            em.close();
        }
    }

    public boolean update(T entity) {
        EntityManager em = null;

        try {
            em = HibUtil.getEntityManager();
            EntityTransaction et = em.getTransaction();
            et.begin();
            em.merge(entity);
            et.commit();
            return true;
        } catch (Exception ex) {
            LOG.warn("Can't merge entity", ex);
            return false;
        } finally {
            em.close();
        }
    }

    public boolean delete(U id) {
        EntityManager em = null;

        try {
            em = HibUtil.getEntityManager();
            EntityTransaction et = em.getTransaction();
            et.begin();
            T region = em.find(clazz, id);
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
