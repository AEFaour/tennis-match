package hw.aef.tennis.core.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;

import org.hibernate.Session;

import hw.aef.tennis.core.DataSourceProvider;
import hw.aef.tennis.core.EntityManagerHolder;
import hw.aef.tennis.core.HibernateUtil;
import hw.aef.tennis.core.entity.Joueur;

public class JoueurRepositoryImpl {

	public void create(Joueur joueur) {
		Session session = HibernateUtil.getSessionfactory().getCurrentSession();
		session.persist(joueur);
		System.out.println("Joueur crée");
	}

	public void update(Joueur joueur) {
		Connection conn = null;

		try {
			DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
			conn = dataSource.getConnection();

			PreparedStatement preparedStatement = conn
					.prepareStatement("update joueur set NOM=?, PRENOM=?, SEXE=? where ID=?");

			preparedStatement.setString(1, joueur.getNom());
			preparedStatement.setString(2, joueur.getPrenom());
			preparedStatement.setString(3, joueur.getSexe().toString());
			preparedStatement.setString(3, joueur.getSexe().toString());
			preparedStatement.setLong(4, joueur.getId());

			preparedStatement.executeUpdate();

			System.out.println("Joueur maj");

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (conn != null)
					conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void delete(Long id) {
		Joueur joueur = getById(id);
		Session session = HibernateUtil.getSessionfactory().getCurrentSession();
		session.delete(joueur);
		System.out.println("Joueur supprimé");
	}

	public Joueur getById(Long id) {

		Joueur joueur = null;
		Session session = null;
		session = HibernateUtil.getSessionfactory().getCurrentSession();
		joueur = session.get(Joueur.class, id);
		System.out.println("Joueur lu");
		return joueur;
	}

	public List<Joueur> list(char sexe) {
//		Session session = HibernateUtil.getSessionfactory().getCurrentSession();
		EntityManager em = EntityManagerHolder.getCurrentEntityManager();
		TypedQuery<Joueur> query = em.createNamedQuery("given_sexe", Joueur.class);
		query.setParameter(0, sexe);
		List<Joueur> joueurs = query.getResultList();
		System.out.println("Joueurs lus");
		return joueurs;
	}

}
