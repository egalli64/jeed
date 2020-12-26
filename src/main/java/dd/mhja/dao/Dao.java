package dd.mhja.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Dao<T, U> {
    static private final Logger log = LoggerFactory.getLogger(Dao.class);

    private final Class<T> clazz;

    // workaround for template type erasure
    public Dao(Class<T> clazz) {
        this.clazz = clazz;
    }

    public List<T> readAll() {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            String jpql = "SELECT e FROM " + clazz.getName() + " e";
            return em.createQuery(jpql, clazz).getResultList();
        } catch (Exception ex) {
            log.error("Can't create query: " + ex.getMessage());
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Optional<T> read(U id) {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            T t = em.find(clazz, null);
            return Optional.ofNullable(t);
        } catch (Exception ex) {
            log.error("Can't create query: " + ex.getMessage());
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public boolean create(T entity) {
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

    public boolean contains(T entity) {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            em.refresh(entity);
            return em.contains(entity);
        } catch (Exception ex) {
            log.warn("Can't check entity", ex);
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public boolean update(T entity) {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            EntityTransaction et = em.getTransaction();
            et.begin();
            em.merge(entity);
            et.commit();
            return true;
        } catch (Exception ex) {
            log.warn("Can't merge entity", ex);
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public boolean delete(U id) {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            EntityTransaction et = em.getTransaction();
            et.begin();
            T entity = em.find(clazz, id);
            if (entity != null) {
                em.remove(entity);
                et.commit();
                return true;
            } else {
                log.info("Can't remove missing entity " + id);
                et.rollback();
                return false;
            }
        } catch (Exception ex) {
            log.warn("Can't remove entity " + id, ex);
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
