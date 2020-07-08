package dd.mhja.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibUtil {
    private static final Logger LOG = LoggerFactory.getLogger(HibUtil.class);

    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("me");

    public static EntityManager getEntityManager() {
        LOG.error("enter");
        EntityManager em = factory.createEntityManager();
        LOG.error(em.toString());
        
        return em;
    }
}
