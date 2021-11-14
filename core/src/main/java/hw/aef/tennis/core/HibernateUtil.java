package hw.aef.tennis.core;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private static final SessionFactory sessionFactory = buildSessionFactory();
	

	public static SessionFactory getSessionfactory() {
		return sessionFactory;
	}


	private static SessionFactory buildSessionFactory() {
		try {
			
			return new Configuration().configure().buildSessionFactory();
			
		} catch (Throwable ex) {
			System.err.printf("Intial SessionFactory creation failed %s", ex);
			throw new ExceptionInInitializerError(ex);
		}
		
	}

}
