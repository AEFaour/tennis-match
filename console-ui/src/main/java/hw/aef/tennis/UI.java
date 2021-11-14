package hw.aef.tennis;

import hw.aef.tennis.controller.EpreuveController;
import hw.aef.tennis.controller.JoueurController;
import hw.aef.tennis.controller.MatchController;
import hw.aef.tennis.controller.ScoreController;
import hw.aef.tennis.controller.TournoiController;

public class UI {

	public static void main(String[] args) {

//		JoueurController controller = new JoueurController();
//		controller.afficheListJoueurs();
// 		controller.creerJoueur();
//  	controller.renommeJoueur();
//  	controller.changeSexe();
//	    controller.deleteJoueur();

//		TournoiController controller = new TournoiController();
//		controller.afficheDetailsTournoi();
// 		controller.creerTournoi();
// 		controller.supprimeTournoi();

//		ScoreController controller = new ScoreController();
//		controller.afficheDetailsScore();

		EpreuveController controller = new EpreuveController();
		controller.afficheListEpreuves();
//		controller.afficheDetailsEpreuve();
//		controller.afficheRolandGarros();

//		MatchController controller = new MatchController();
//		controller.tapisVert();
//		controller.afficheDetailsMatch();
//		controller.ajouterMatch();
//		controller.supprimeMatch();

//		ScoreController controller = new ScoreController();
//		controller.supprimeScore();
//		controller.afficheDetailsScore();

	}

}
