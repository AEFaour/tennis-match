package hw.aef.tennis.core.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="MATCH_TENNIS")
public class Match {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name="ID_VAINQUEUR")
	private Joueur vainqueur;
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name="ID_FINALISTE")
	private Joueur finaliste;
	@OneToOne(fetch = FetchType.LAZY )
	@JoinColumn(name="ID_EPREUVE")
	private Epreuve epreuve;
	@OneToOne(mappedBy = "match", fetch = FetchType.LAZY, 
			cascade = CascadeType.PERSIST, orphanRemoval = true  )
	private Score score;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Joueur getVainqueur() {
		return vainqueur;
	}
	public void setVainqueur(Joueur vainquer) {
		this.vainqueur = vainquer;
	}
	public Joueur getFinaliste() {
		return finaliste;
	}
	public void setFinaliste(Joueur finaliste) {
		this.finaliste = finaliste;
	}
	public Epreuve getEpreuve() {
		return epreuve;
	}
	public void setEpreuve(Epreuve epreuve) {
		this.epreuve = epreuve;
	}
	public Score getScore() {
		return score;
	}
	public void setScore(Score score) {
		this.score = score;
	}

	
}
