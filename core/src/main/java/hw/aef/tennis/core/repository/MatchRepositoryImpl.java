package hw.aef.tennis.core.repository;

import org.hibernate.Session;

import hw.aef.tennis.core.HibernateUtil;
import hw.aef.tennis.core.entity.Match;

public class MatchRepositoryImpl {
	
	public Match getById(Long id) {
		Session session = HibernateUtil.getSessionfactory().getCurrentSession();
		Match match = session.get(Match.class, id);
		System.out.println("Match lu");
		return match;
	}
	
	public void create(Match match) {
		Session session = HibernateUtil.getSessionfactory().getCurrentSession();
		session.persist(match);
		System.out.println("Match ajouté");

	}
	
	public void delete(Long id) {

		Session session = HibernateUtil.getSessionfactory().getCurrentSession();
		Match match = session.get(Match.class, id);
		session.delete(match);
		System.out.println("Match supprimé");

	}
	
}
