package hw.aef.tennis.core.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hw.aef.tennis.core.EntityManagerHolder;
import hw.aef.tennis.core.HibernateUtil;
import hw.aef.tennis.core.dto.EpreuveFullDto;
import hw.aef.tennis.core.dto.EpreuveLightDto;
import hw.aef.tennis.core.dto.JoueurDto;
import hw.aef.tennis.core.dto.TournoiDto;
import hw.aef.tennis.core.entity.Epreuve;
import hw.aef.tennis.core.entity.Joueur;
import hw.aef.tennis.core.repository.EpreuveRepositoryImpl;

public class EpreuveService {

	private EpreuveRepositoryImpl epreuveRepository;

	public EpreuveService() {
		this.epreuveRepository = new EpreuveRepositoryImpl();
	}

	public EpreuveFullDto getEpreuveDetaillee(Long id) {
		Session session = null;
		Transaction tx = null;
		Epreuve epreuve = null;
		EpreuveFullDto dto = null;
		try {
			session = HibernateUtil.getSessionfactory().getCurrentSession();
			tx = session.beginTransaction();
			epreuve = epreuveRepository.getById(id);
			// Hibernate.initialize(epreuve.getTournoi());

			dto = new EpreuveFullDto();
			dto.setId(epreuve.getId());
			dto.setAnnee(epreuve.getAnnee());
			dto.setTypeEpreuve(epreuve.getTypeEpreuve());
			TournoiDto tournoiDto = new TournoiDto();
			tournoiDto.setId(epreuve.getTournoi().getId());
			tournoiDto.setNom(epreuve.getTournoi().getNom());
			tournoiDto.setCode(epreuve.getTournoi().getCode());
			dto.setTournoi(tournoiDto);

			dto.setParticipants(new HashSet<>());
			for (Joueur joueur : epreuve.getParticipants()) {
				final JoueurDto joueurDto = new JoueurDto();
				joueurDto.setId(joueur.getId());
				joueurDto.setNom(joueur.getNom());
				joueurDto.setPrenom(joueur.getPrenom());
				joueurDto.setSexe(joueur.getSexe());
				dto.getParticipants().add(joueurDto);
			}
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
		return dto;
	}

	public EpreuveLightDto getEpreuveSansTournoi(Long id) {
		Session session = null;
		Transaction tx = null;
		Epreuve epreuve = null;
		EpreuveLightDto dto = null;
		try {
			session = HibernateUtil.getSessionfactory().getCurrentSession();
			tx = session.beginTransaction();
			epreuve = epreuveRepository.getById(id);
			dto = new EpreuveLightDto();
			dto.setId(epreuve.getId());
			dto.setAnnee(epreuve.getAnnee());
			dto.setTypeEpreuve(epreuve.getTypeEpreuve());
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
		return dto;
	}

	public List<EpreuveFullDto> getListEpreuves(String codeTournoi) {
		EntityManager em = null;
//		Session session = null;
		EntityTransaction tx = null;
		List<EpreuveFullDto> dtos = new ArrayList<>();
		try {
//			session = HibernateUtil.getSessionfactory().getCurrentSession();
//			tx = session.beginTransaction();
			em = new EntityManagerHolder().getCurrentEntityManager();
			tx = em.getTransaction();
			tx.begin();
			List<Epreuve> epreuves = epreuveRepository.list(codeTournoi);

			for (Epreuve epreuve : epreuves) {
				final EpreuveFullDto dto = new EpreuveFullDto();
				dto.setId(epreuve.getId());
				dto.setAnnee(epreuve.getAnnee());
				dto.setTypeEpreuve(epreuve.getTypeEpreuve());
				TournoiDto tournoiDto = new TournoiDto();
				tournoiDto.setId(epreuve.getTournoi().getId());
				tournoiDto.setNom(epreuve.getTournoi().getNom());
				tournoiDto.setCode(epreuve.getTournoi().getCode());
				dto.setTournoi(tournoiDto);
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

}
