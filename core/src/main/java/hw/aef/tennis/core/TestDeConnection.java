package hw.aef.tennis.core;

import hw.aef.tennis.core.entity.Epreuve;
import hw.aef.tennis.core.entity.Joueur;
import hw.aef.tennis.core.entity.Match;
import hw.aef.tennis.core.entity.Score;
import hw.aef.tennis.core.service.MatchService;

public class TestDeConnection {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

//		JoueurRepositoryImpl joueurRepository = new JoueurRepositoryImpl();
//		Joueur ronyFerat = joueurRepository.getById(56L);
//		System.out.printf("%s %s \n",ronyFerat.getPrenom(), ronyFerat.getNom());
//		Joueur yannickNoah = new Joueur();
//		yannickNoah.setNom("Noah");
//		yannickNoah.setPrenom("Yannick");
//		yannickNoah.setSexe('H');
//		joueurRepository.create(yannickNoah);
//		System.out.printf("%s : %s %s\n",yannickNoah.getId(), yannickNoah.getPrenom(), yannickNoah.getNom());
//		Joueur noah = joueurRepository.getById(65L);
//		noah.setPrenom("Yannick");
//		joueurRepository.update(noah);
//		System.out.printf("%s %s\n",noah.getPrenom(), noah.getNom());
//		joueurRepository.delete(65L);
		
//		List<Joueur> joueurs = joueurRepository.list();
//		
//		for (Joueur j: joueurs) {
//			System.out.printf("%s %s\n",j.getPrenom(), j.getNom());
//		}
//
//		JoueurService joueurService = new JoueurService();
//		Joueur yannickNoah = new Joueur();
//		yannickNoah.setNom("Noah");
//		yannickNoah.setPrenom("Yannick");
//		yannickNoah.setSexe('H');
//		joueurService.createJoueur(yannickNoah);
		
		MatchService matchService = new MatchService();
		
		Match match = new Match();
		Score score = new Score();
		score.setSet1((byte)3);
		score.setSet2((byte)4);
		score.setSet3((byte)6);
		
		match.setScore(score);
		score.setMatch(match);
		
		Joueur federer = new Joueur();
		federer.setId(32L);
		Joueur murray = new Joueur();
		murray.setId(34L);
		match.setVainqueur(federer);
		match.setFinaliste(murray);
		Epreuve epreuve = new Epreuve();
		epreuve.setId(10L);
		match.setEpreuve(epreuve);
		matchService.enregistrerNouveauMatch(match);
		
		System.out.printf("L'indentifiant du match crée est : %s", match.getId());	
	}

}
