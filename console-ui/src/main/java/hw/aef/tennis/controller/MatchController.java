package hw.aef.tennis.controller;

import java.util.Scanner;

import hw.aef.tennis.core.dto.EpreuveFullDto;
import hw.aef.tennis.core.dto.JoueurDto;
import hw.aef.tennis.core.dto.MatchDto;
import hw.aef.tennis.core.dto.ScoreFullDto;
import hw.aef.tennis.core.service.EpreuveService;
import hw.aef.tennis.core.service.MatchService;

public class MatchController {
	private MatchService matchService;

	public MatchController() {
		this.matchService = new MatchService();
	}

	public void tapisVert() {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Quel est l'identifiant du match que vous voulez annuler?");
		long identifiant = scanner.nextLong();
		matchService.tapisVert(identifiant);
	}

	public void ajouterMatch() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Quel est l'identifiant de l'epreuve ?");
		long epreuveId = scanner.nextLong();
		scanner.nextLine();

		System.out.println("Quel est l'identifiant du vainqueur ?");
		long vainqueurId = scanner.nextLong();
		scanner.nextLine();

		System.out.println("Quel est l'identifiant du finaliste ?");
		long finalisteId = scanner.nextLong();
		scanner.nextLine();

		MatchDto dto = new MatchDto();
		dto.setEpreuve(new EpreuveFullDto());
		dto.getEpreuve().setId(epreuveId);
		dto.setVainqueur(new JoueurDto());
		dto.getVainqueur().setId(vainqueurId);
		dto.setFinaliste(new JoueurDto());
		dto.getFinaliste().setId(finalisteId);

		System.out.println("Quel est la valeur du 1er set ?");
		byte set1 = scanner.nextByte();
		scanner.nextLine();
		System.out.println("Quel est la valeur du 2eme set ?");
		byte set2 = scanner.nextByte();
		scanner.nextLine();
		System.out.println("Quel est la valeur du 3eme set ?");
		byte set3 = scanner.nextByte();
		scanner.nextLine();
		System.out.println("Quel est la valeur du 4eme set ?");
		byte set4 = scanner.nextByte();
		scanner.nextLine();
		System.out.println("Quel est la valeur du 5eme set ?");
		byte set5 = scanner.nextByte();
		scanner.nextLine();

		ScoreFullDto scoreDto = new ScoreFullDto();
		scoreDto.setSet1(set1);
		scoreDto.setSet2(set2);
		scoreDto.setSet3(set3);
		scoreDto.setSet4(set4);
		scoreDto.setSet5(set5);

		dto.setScore(scoreDto);
		scoreDto.setMatch(dto);

		matchService.createMatch(dto);
	}

	public void afficheDetailsMatch() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Quel est l'identifiant du match dont vous voulez afficher les informations?");
		long identifiant = scanner.nextLong();
		MatchDto dto = matchService.getMatch(identifiant);
		System.out.printf("Il s'agit d'un match de %s qui s'est déroulé à %s \n", dto.getEpreuve().getAnnee(),
				dto.getEpreuve().getTournoi().getNom());
		System.out.printf("Le nom et le prenom du vainqueur %s %s \n", dto.getVainqueur().getNom(),
				dto.getVainqueur().getPrenom());
		System.out.printf("Le nom et le prenom du finaliste %s %s \n", dto.getFinaliste().getNom(),
				dto.getFinaliste().getPrenom());
		System.out.println("Les sets du score sont :  ");
		System.out.println(dto.getScore().getSet1());
		System.out.println(dto.getScore().getSet2());
		if (dto.getScore().getSet3() != null) {
			System.out.println(dto.getScore().getSet3());
		}
		if (dto.getScore().getSet4() != null) {
			System.out.println(dto.getScore().getSet4());
		}
		if (dto.getScore().getSet5() != null) {
			System.out.println(dto.getScore().getSet5());
		}

	}

	public void supprimeMatch() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Quel est l'identifiant du match dont vous voulez supprimer?");
		long identifiant = scanner.nextLong();
		matchService.supprimeMatch(identifiant);
		System.out.printf("Le match numero %s supprimé ", identifiant);
	}
}
