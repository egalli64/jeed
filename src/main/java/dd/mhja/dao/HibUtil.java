package dd.mhja.dao;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HibUtil {
    private static SessionFactory sessionFactory;
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("me");;
    
    static {
        Configuration configuration = new Configuration();
        Properties settings = new Properties();

        settings.put(Environment.DATASOURCE, "java:comp/env/jdbc/me");
        settings.put(Environment.SHOW_SQL, "true");
        configuration.setProperties(settings);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);        
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }
    
    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
}
