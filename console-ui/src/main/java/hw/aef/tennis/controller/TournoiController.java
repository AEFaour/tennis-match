package hw.aef.tennis.controller;

import java.util.Scanner;

import hw.aef.tennis.core.service.TournoiService;
import hw.aef.tennis.core.dto.TournoiDto;
import hw.aef.tennis.core.entity.Tournoi;;

public class TournoiController {
	
	private TournoiService tournoiService;
    public TournoiController()
    {
        this.tournoiService = new TournoiService();
    }
    public void afficheDetailsTournoi()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'identifiant du tournoi dont vous voulez afficher les informations?");
        long identifiant = scanner.nextLong();
        TournoiDto tournoi = tournoiService.getTournoi(identifiant);
        System.out.printf("Le tournoi selectionne s'appelle %s %s", tournoi.getNom(),  tournoi.getCode());
    }
    public void creerTournoi()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrer le nom du tournoi ");
        String nom = scanner.nextLine();

        System.out.println("Entrer le code du tournoi ");
        String code = scanner.nextLine();

        TournoiDto tournoi = new TournoiDto();
        tournoi.setNom(nom);
        tournoi.setCode(code);
        tournoiService.createTournoi(tournoi);
    }
    
    public void supprimeTournoi() {
    	 Scanner scanner = new Scanner(System.in);
         System.out.println("Quel est l'identifiant du tournoi dont vous voulez supprimer?");
         long identifiant = scanner.nextLong();
         tournoiService.deleteTournoi(identifiant);;
         System.out.printf("Le tournoi numero %s supprimé ", identifiant);
    }

}
