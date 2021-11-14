package hw.aef.tennis.controller;

import java.util.Scanner;

import hw.aef.tennis.core.dto.ScoreFullDto;
import hw.aef.tennis.core.entity.Joueur;
import hw.aef.tennis.core.entity.Score;
import hw.aef.tennis.core.service.ScoreService;

public class ScoreController {

	private ScoreService scoreService;

	public ScoreController() {
		this.scoreService = new ScoreService();
	}

	public void afficheDetailsScore() {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Quel est l'indentifiant du score dont vous voulez afficher les informations");
		long identifiant = scanner.nextLong();
		ScoreFullDto score = scoreService.getScore(identifiant);
		System.out.println("Les sets du score sont :  ");
		System.out.println(score.getSet1()); 
		System.out.println(score.getSet2()); 
		if(score.getSet3() != null) {System.out.println(score.getSet3()); }
		if(score.getSet4() != null) {System.out.println(score.getSet4()); }
		if(score.getSet5() != null) {System.out.println(score.getSet5()); }
		
		System.out.printf("Il s'agit du tournoi %s \n", 
				score.getMatch().getEpreuve().getTournoi().getNom());		
		System.out.printf("L'epreuve s'est déroulé en %s et il s'agissait d'une épreuve %s \n",
				score.getMatch().getEpreuve().getAnnee(), 
				(score.getMatch().getEpreuve().getTypeEpreuve().charValue()=='H'? "Homme": "Femme"));		
	}
	
	public void supprimeScore() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Quel est l'identifiant du score dont vous voulez supprimer?");
		long identifiant = scanner.nextLong();
		scoreService.supprimeScore(identifiant);
		System.out.printf("Le score numero %s supprimé ", identifiant);
	}
}
