package hw.aef.tennis.controller;

import java.util.Scanner;

import hw.aef.tennis.core.dto.EpreuveFullDto;
import hw.aef.tennis.core.dto.EpreuveLightDto;
import hw.aef.tennis.core.dto.JoueurDto;
import hw.aef.tennis.core.service.EpreuveService;

public class EpreuveController {

	private EpreuveService epreuveService;

	public EpreuveController() {
		this.epreuveService = new EpreuveService();
	}

	public void afficheListEpreuves() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Quel est le code du tournoi ");
		String code = scanner.nextLine();
		for (EpreuveFullDto dto : epreuveService.getListEpreuves(code)) {
			System.out.printf(
					"%s %s %s %s \n",
					dto.getId(), dto.getAnnee(), dto.getTypeEpreuve(),
					dto.getTournoi().getNom());
		}
	}

	public void afficheDetailsEpreuve() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Quel est l'identifiant de l'epreuve dont vous voulez afficher les informations?");
		long identifiant = scanner.nextLong();
		EpreuveFullDto epreuve = epreuveService.getEpreuveDetaillee(identifiant);
		System.out.printf("Le nom du tournoi est %s \n", epreuve.getTournoi().getNom());
		for (JoueurDto joueurDto : epreuve.getParticipants()) {
			System.out.printf("Le joueur s'appelle %s %s \n", joueurDto.getPrenom(), joueurDto.getNom());
		}
	}

	public void afficheRolandGarros() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Quel est l'identifiant de l'epreuve dont vous voulez afficher les informations?");
		long identifiant = scanner.nextLong();
		EpreuveLightDto epreuve = epreuveService.getEpreuveSansTournoi(identifiant);
		//System.out.printf("Le nom du tournoi est %s \n", epreuve.getTournoi().getNom());
	}
}
