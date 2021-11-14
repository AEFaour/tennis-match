package hw.aef.tennis.controller;

import java.util.List;
import java.util.Scanner;

import hw.aef.tennis.core.dto.JoueurDto;
import hw.aef.tennis.core.entity.Joueur;
import hw.aef.tennis.core.service.JoueurService;

public class JoueurController {

	private JoueurService joueurService;

	public JoueurController() {
		this.joueurService = new JoueurService();
	}

	public void afficheListJoueurs() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Vous voulez afficher une liste femmes (F) ou hommes (H)");
		char sexe = scanner.nextLine().charAt(0);
		for (JoueurDto dto : joueurService.getListJoueurs(sexe)) {
			System.out.printf("Le joueur numéro %s s'appele %s %s \n", dto.getId(), dto.getPrenom(),
					dto.getNom());
		}
	}

	public void afficheDetailsJoueur() {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Quel est l'indentifiant du joueur dont vous voulez afficher les informations");
		long identifiant = scanner.nextLong();
		Joueur joueur = joueurService.getJoueur(identifiant);
		System.out.printf("Le joueur séléctionné s'applle %s %s \n", joueur.getPrenom(), joueur.getNom());

	}

	public void creerJoueur() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Quel est le nom du joueur");
		String nom = scanner.nextLine();
		System.out.println("Quel est le prenom du joueur");
		String prenom = scanner.nextLine();
		System.out.println("Quel est le sexe du joueur");
		char sexe = scanner.nextLine().charAt(0);
		Joueur joueur = new Joueur();
		joueur.setNom(nom);
		joueur.setPrenom(prenom);
		joueur.setSexe(sexe);
		joueurService.createJoueur(joueur);
		System.out.printf("Le joueur a été créé, son identifiant est %s ", joueur.getId());

	}

	public void deleteJoueur() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Quel est l'indentifiant du joueur dont vous voulez afficher les informations");
		long identifiant = scanner.nextLong();
		joueurService.deleteJoueur(identifiant);
	}

	public void renommeJoueur() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Quel est l'indentifiant du joueur dont vous voulez afficher les informations");
		long identifiant = scanner.nextLong();
		scanner.nextLine();
		System.out.println("Quel est le nouveau nom du joueur");
		String nouveauNom = scanner.nextLine();
		Joueur joueur = joueurService.getJoueur(identifiant);
		joueurService.renomme(identifiant, nouveauNom);
		System.out.printf("Le joueur a été renommé, son nouveau nom est %s ",
				joueurService.getJoueur(identifiant).getNom());

	}

	public void changeSexe() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Quel est l'indentifiant du joueur dont vous voulez afficher les informations");
		long identifiant = scanner.nextLong();
		scanner.nextLine();
		Character nouveauSexe = 'F';
		Joueur joueur = joueurService.getJoueur(identifiant);
		if (joueur.getSexe() == 'F') {
			nouveauSexe = 'H';
		} else {
			nouveauSexe = 'F';
		}
		joueurService.changeSexe(identifiant, nouveauSexe);
		System.out.printf("Le joueur a été renommé, son nouveau nom est %s ",
				joueurService.getJoueur(identifiant).getSexe());

	}

}
