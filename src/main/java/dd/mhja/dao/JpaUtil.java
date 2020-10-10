package dd.mhja.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
    private static EntityManagerFactory factory;

    static {
        try {
            factory = Persistence.createEntityManagerFactory("me");
        } catch (Throwable th) {
            throw new IllegalStateException("Can't create EntityManagerFactory", th);
        }
    }

    // no instance of this class is allowed!
    private JpaUtil() {
    }

    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
}
