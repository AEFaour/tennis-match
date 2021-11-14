package hw.aef.tennis.core.dto;

public class JoueurDto {

	private Long id;
	private String prenom;
	private String nom;
	private Character sexe;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Character getSexe() {
		return sexe;
	}
	public void setSexe(Character sexe) {
		this.sexe = sexe;
	}
}
