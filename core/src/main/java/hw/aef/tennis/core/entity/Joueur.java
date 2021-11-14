package hw.aef.tennis.core.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


@NamedQuery(query = "select j from Joueur j where j.sexe=?0", name = "given_sexe")
@NamedQuery(query = "select j from Joueur j where j.nom=?0", name = "given_nom")
@Entity
public class Joueur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@Override
	public int hashCode() {
		return Objects.hash(id, nom, prenom, sexe);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Joueur other = (Joueur) obj;
		return Objects.equals(id, other.id) && Objects.equals(nom, other.nom) && Objects.equals(prenom, other.prenom)
				&& Objects.equals(sexe, other.sexe);
	}

}
