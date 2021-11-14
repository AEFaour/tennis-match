package hw.aef.tennis.core.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hw.aef.tennis.core.EntityManagerHolder;
import hw.aef.tennis.core.HibernateUtil;
import hw.aef.tennis.core.dto.JoueurDto;
import hw.aef.tennis.core.entity.Joueur;
import hw.aef.tennis.core.repository.JoueurRepositoryImpl;

public class JoueurService {

	private JoueurRepositoryImpl joueurRepository;

	public JoueurService() {
		this.joueurRepository = new JoueurRepositoryImpl();
	}

	public void renomme(Long id, String nouveauNom) {

		Joueur joueur = getJoueur(id);
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionfactory().getCurrentSession();
			tx = session.beginTransaction();
			joueur.setNom(nouveauNom);
			session.merge(joueur);
			tx.commit();
		} catch (Throwable t) {
			if (tx != null) {
				tx.rollback();
			}
			t.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public void changeSexe(Long id, Character nouveauSexe) {
		Joueur joueur = getJoueur(id);
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionfactory().getCurrentSession();
			tx = session.beginTransaction();
			if (joueur.getSexe() == 'F') {
				nouveauSexe = 'H';
			} else {
				nouveauSexe = 'F';
			}
			joueur.setSexe(nouveauSexe);
			session.merge(joueur);
			tx.commit();
		} catch (Throwable t) {
			if (tx != null) {
				tx.rollback();
			}
			t.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	public List<JoueurDto> getListJoueurs(char sexe) {
		EntityManager em = null;
//		Session session = null;
		EntityTransaction tx = null;
		List<JoueurDto> dtos = new ArrayList<JoueurDto>();
		try {
//			session = HibernateUtil.getSessionfactory().getCurrentSession();
//			tx = session.beginTransaction();
			em = EntityManagerHolder.getCurrentEntityManager();
			tx = em.getTransaction();
			tx.begin();
			List<Joueur> joueurs = joueurRepository.list(sexe);

			for (Joueur joueur : joueurs) {
				final JoueurDto dto = new JoueurDto();
				dto.setId(joueur.getId());
				dto.setNom(joueur.getNom());
				dto.setPrenom(joueur.getPrenom());
				dto.setSexe(joueur.getSexe());
				dtos.add(dto);
			}

			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return dtos;
	}

	public void createJoueur(Joueur joueur) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionfactory().getCurrentSession();
			tx = session.beginTransaction();
			joueurRepository.create(joueur);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public Joueur getJoueur(Long id) {

		Session session = null;
		Transaction tx = null;
		Joueur joueur = null;
		try {
			session = HibernateUtil.getSessionfactory().getCurrentSession();
			tx = session.beginTransaction();
			joueur = joueurRepository.getById(id);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return joueur;
	}

	public void deleteJoueur(Long id) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionfactory().getCurrentSession();
			tx = session.beginTransaction();
			joueurRepository.delete(id);
			;
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
}
