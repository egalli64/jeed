package dd.mhja.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibUtil {
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("me");;

    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
}
