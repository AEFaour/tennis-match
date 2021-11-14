package hw.aef.tennis.core;

import hw.aef.tennis.core.dto.TournoiDto;
import hw.aef.tennis.core.entity.Tournoi;
import hw.aef.tennis.core.service.TournoiService;

public class TravauxPratiques {

	public static void main(String[] args) {
		//TournoiRepositoryImpl tournoiRepository = new TournoiRepositoryImpl();
		TournoiService tournoiService = new TournoiService();
		//List<Tournoi> tournois = tournoiRepository.list();
		//tournoiRepository.list().stream().forEach(t -> System.out.printf("%s:  %s %s\n", t.getId(), t.getNom(), t.getCode()) );
//		for (Tournoi t: tournois) {
//			System.out.printf("%s %s\n",t.getNom(), t.getCode());
//		}
		TournoiDto tournoi = new TournoiDto();
		tournoi.setNom("FR Paris");
		tournoi.setCode("FR");
		tournoiService.createTournoi(tournoi);
		
	}
}
