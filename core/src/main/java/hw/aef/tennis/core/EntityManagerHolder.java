package hw.aef.tennis.core;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerHolder {
	
	private static final ThreadLocal<EntityManager> entityManagerThereadLocal = new ThreadLocal<>();
	private static EntityManagerFactory entityManagerFactory = buildEntityManagerFactory();
	
	private static EntityManagerFactory buildEntityManagerFactory() {
		return Persistence.createEntityManagerFactory("tennis-unit");
	}
	
	/** 
	 * @return The {@link EntityManager} linked to this thread
	 */
	public static EntityManager getCurrentEntityManager() {
		
		EntityManager entityManager = entityManagerThereadLocal.get();
		
		if (entityManager == null) {
			entityManager = entityManagerFactory.createEntityManager();
			entityManagerThereadLocal.set(entityManager);
			
		}
		return entityManager;
	}

}
