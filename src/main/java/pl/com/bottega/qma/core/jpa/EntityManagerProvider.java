package pl.com.bottega.qma.core.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProvider {

    private static final String PERSISTENCE_UNIT_NAME = "qma";
    private static final ThreadLocal<EntityManager> THREAD_LOCAL = new ThreadLocal<>();
    private final EntityManagerFactory factory;

    public EntityManagerProvider() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public EntityManager getEntityManager() {
        if(THREAD_LOCAL.get() == null) {
            THREAD_LOCAL.set(factory.createEntityManager());
        }
        return THREAD_LOCAL.get();
    }
}
