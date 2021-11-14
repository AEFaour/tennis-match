package hw.aef.tennis.core.service;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hw.aef.tennis.core.HibernateUtil;
import hw.aef.tennis.core.dto.EpreuveFullDto;
import hw.aef.tennis.core.dto.MatchDto;
import hw.aef.tennis.core.dto.ScoreFullDto;
import hw.aef.tennis.core.dto.TournoiDto;
import hw.aef.tennis.core.entity.Score;
import hw.aef.tennis.core.repository.ScoreRepositoryImpl;

public class ScoreService {

	private ScoreRepositoryImpl scoreRepository;

	public ScoreService() {
		this.scoreRepository = new ScoreRepositoryImpl();
	}

	public ScoreFullDto getScore(Long id) {

		Session session = null;
		Transaction tx = null;
		Score score = null;
		ScoreFullDto dto = null;
		try {
			session = HibernateUtil.getSessionfactory().getCurrentSession();
			tx = session.beginTransaction();
			score = scoreRepository.getById(id);
			
			dto = new ScoreFullDto();
			dto.setId(score.getId());
			dto.setSet1(score.getSet1());
			dto.setSet2(score.getSet2());
			dto.setSet3(score.getSet3());
			dto.setSet4(score.getSet4());
			dto.setSet5(score.getSet5());
			
			MatchDto matchDto = new MatchDto();
			matchDto.setId(score.getMatch().getId());
			
			dto.setMatch(matchDto);
			
			EpreuveFullDto epreuveDto = new EpreuveFullDto();
			epreuveDto.setId(score.getMatch().getEpreuve().getId());
			epreuveDto.setAnnee(score.getMatch().getEpreuve().getAnnee());
			epreuveDto.setTypeEpreuve(score.getMatch().getEpreuve().getTypeEpreuve());
			TournoiDto tournoiDto = new TournoiDto();
			tournoiDto.setId(score.getMatch().getEpreuve().getTournoi().getId());
			tournoiDto.setNom(score.getMatch().getEpreuve().getTournoi().getNom());
			tournoiDto.setCode(score.getMatch().getEpreuve().getTournoi().getCode());
			epreuveDto.setTournoi(tournoiDto);
			
			matchDto.setEpreuve(epreuveDto);
			
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
	
	public void supprimeScore(Long id) {
		Session session = null;
		Transaction tx = null;

		try {
			session = HibernateUtil.getSessionfactory().getCurrentSession();
			tx = session.beginTransaction();
			
            scoreRepository.delete(id);
			
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
