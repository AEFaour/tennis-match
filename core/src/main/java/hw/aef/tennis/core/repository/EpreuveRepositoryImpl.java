package hw.aef.tennis.core.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import hw.aef.tennis.core.EntityManagerHolder;
import hw.aef.tennis.core.entity.Epreuve;

public class EpreuveRepositoryImpl {
	public Epreuve getById(Long id) {
//		Session session = HibernateUtil.getSessionfactory().getCurrentSession();
		EntityManager em = EntityManagerHolder.getCurrentEntityManager();
		Epreuve epreuve = em.find(Epreuve.class, id);
		System.out.println("Epreuve lu");
		return epreuve;
	}
	
	public List<Epreuve> list(String codeTournoi) {
		EntityManager em = EntityManagerHolder.getCurrentEntityManager();
//		Session session = HibernateUtil.getSessionfactory().getCurrentSession();
		TypedQuery<Epreuve> query = em.createQuery("select e from Epreuve e join fetch e.tournoi where e.tournoi.code=?0", Epreuve.class);
		query.setParameter(0, codeTournoi);
		List<Epreuve> epreuves =  query.getResultList();
		System.out.println("Eprouves lus");
		return epreuves;
	}

}
