package hw.aef.tennis.core.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import hw.aef.tennis.core.EntityManagerHolder;
import hw.aef.tennis.core.dto.TournoiDto;
import hw.aef.tennis.core.entity.Tournoi;
import hw.aef.tennis.core.repository.TournoiRepositoryImpl;

public class TournoiService {

	private TournoiRepositoryImpl tournoiRepository;

	public TournoiService() {
		this.tournoiRepository = new TournoiRepositoryImpl();
	}

	public void createTournoi(TournoiDto dto) {
		EntityManager em = null;
//		Session session = null;
		EntityTransaction tx = null;
		try {
//			session = HibernateUtil.getSessionfactory().getCurrentSession();
//			tx = session.beginTransaction();
			em = EntityManagerHolder.getCurrentEntityManager();
			tx = em.getTransaction();
			tx.begin();
			Tournoi tournoi = new Tournoi();
			tournoi.setId(dto.getId());
			tournoi.setCode(dto.getCode());
			tournoi.setNom(dto.getNom());
			tournoiRepository.create(tournoi);
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
	}

	public void deleteTournoi(Long id) {

		EntityManager em = null;
//		Session session = null;
		EntityTransaction tx = null;
		try {
//			session = HibernateUtil.getSessionfactory().getCurrentSession();
			em = EntityManagerHolder.getCurrentEntityManager();
			tx = em.getTransaction();
			tx.begin();
			tournoiRepository.delete(id);
			;
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

	}

	public TournoiDto getTournoi(Long id) {
//		Session session = null;
		EntityManager em = null;
		EntityTransaction tx = null;
		Tournoi tournoi = null;
		TournoiDto dto = null;
		try {
//			session = HibernateUtil.getSessionfactory().getCurrentSession();
			em = EntityManagerHolder.getCurrentEntityManager();
			tx = em.getTransaction();
			tx.begin();
			tournoi = tournoiRepository.getById(id);
			dto = new TournoiDto();
			dto.setId(tournoi.getId());
			dto.setCode(tournoi.getCode());
			dto.setNom(tournoi.getNom());

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
		return dto;
	}

}
