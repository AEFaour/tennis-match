package hw.aef.tennis.core.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import hw.aef.tennis.core.DataSourceProvider;
import hw.aef.tennis.core.EntityManagerHolder;
import hw.aef.tennis.core.entity.Tournoi;

public class TournoiRepositoryImpl {

	public void create(Tournoi tournoi) {
		EntityManager em = EntityManagerHolder.getCurrentEntityManager();
//		Session session = HibernateUtil.getSessionfactory().getCurrentSession();
		em.persist(tournoi);
		System.out.println("Tournoi ajouté");
	}

	public void update(Tournoi tournoi) {
		Connection conn = null;

		try {
			DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
			conn = dataSource.getConnection();

			PreparedStatement preparedStatement = conn.prepareStatement("update tournoi set NOM=?, CODE=? where ID=?");

			preparedStatement.setString(1, tournoi.getNom());
			preparedStatement.setString(2, tournoi.getCode());
			preparedStatement.setLong(3, tournoi.getId());

			preparedStatement.executeUpdate();

			System.out.println("Tournoi maj");

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

//		Tournoi tournoi = new Tournoi();
//		tournoi.setId(id);
		EntityManager em = EntityManagerHolder.getCurrentEntityManager();
		Tournoi tournoi = em.find(Tournoi.class, id);
		//Session session = HibernateUtil.getSessionfactory().getCurrentSession();
		em.remove(tournoi);
		System.out.println("Tournoi supprimé");

	}

	public Tournoi getById(Long id) {
		EntityManager em = EntityManagerHolder.getCurrentEntityManager();
		//Session session = HibernateUtil.getSessionfactory().getCurrentSession();
		Tournoi tournoi = em.find(Tournoi.class, id);
		System.out.println("Tournoi lu");
		return tournoi;
	}

	public List<Tournoi> list() {
		Connection conn = null;
		List<Tournoi> tournois = new ArrayList<>();
		try {
			DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
			conn = dataSource.getConnection();

			PreparedStatement preparedStatement = conn.prepareStatement("select ID, NOM, CODE from tournoi");

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Tournoi tournoi = new Tournoi();
				tournoi.setId(rs.getLong("ID"));
				tournoi.setNom(rs.getString("NOM"));
				tournoi.setCode(rs.getString("code"));
				tournois.add(tournoi);
			}
			System.out.println("Tournois lus");

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
		return tournois;
	}

}
