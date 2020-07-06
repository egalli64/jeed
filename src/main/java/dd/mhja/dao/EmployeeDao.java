package dd.mhja.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmployeeDao {
    static final Logger LOG = LoggerFactory.getLogger(EmployeeDao.class);

    public List<Employee> readAll() {
        EntityManager em = null;

        try {
            em = HibUtil.getEntityManager();
            return em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Optional<Employee> read(int id) {
        EntityManager em = null;

        try {
            em = HibUtil.getEntityManager();
            return Optional.ofNullable(em.find(Employee.class, id));
        } finally {
            em.close();
        }
    }

    public boolean create(Employee employee) {
        EntityManager em = null;

        try {
            em = HibUtil.getEntityManager();
            EntityTransaction et = em.getTransaction();
            et.begin();
            em.persist(employee);
            et.commit();
            return true;
        } catch (Exception ex) {
            LOG.warn("Can't persist employee", ex);
            return false;
        } finally {
            em.close();
        }
    }

    public boolean update(Employee employee) {
        EntityManager em = null;

        try {
            em = HibUtil.getEntityManager();
            EntityTransaction et = em.getTransaction();
            et.begin();
            em.merge(employee);
            et.commit();
            return true;
        } catch (Exception ex) {
            LOG.warn("Can't merge employee", ex);
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
            Employee employee = em.find(Employee.class, id);
            if (employee != null) {
                em.remove(employee);
                et.commit();
                return true;
            } else {
                LOG.info("Can't remove missing employee " + id);
                et.rollback();
                return false;
            }
        } catch (Exception ex) {
            LOG.warn("Can't remove employee " + id, ex);
            return false;
        } finally {
            em.close();
        }
    }
}
