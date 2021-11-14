package hw.aef.tennis.core.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.sql.DataSource;

import org.hibernate.Session;

import hw.aef.tennis.core.DataSourceProvider;
import hw.aef.tennis.core.HibernateUtil;
import hw.aef.tennis.core.entity.Score;

public class ScoreRepositoryImpl {

	public void create(Score score) {
		Connection conn = null;
		try {
			DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
			conn = dataSource.getConnection();

			PreparedStatement preparedStatement = conn.prepareStatement(
					"insert into score_vainqueur (ID_MATCH, SET_1, SET_2, SET_3, SET_4, SET_5) values (?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setLong(1, score.getMatch().getId());
			if (score.getSet1() == null) {
				preparedStatement.setNull(2, Types.TINYINT);
			} else {
				preparedStatement.setByte(2, score.getSet1());
			}
			if (score.getSet2() == null) {
				preparedStatement.setNull(3, Types.TINYINT);
			} else {
				preparedStatement.setByte(3, score.getSet2());
			}
			if (score.getSet3() == null) {
				preparedStatement.setNull(4, Types.TINYINT);
			} else {
				preparedStatement.setByte(4, score.getSet3());
			}
			if (score.getSet3() == null) {
				preparedStatement.setNull(4, Types.TINYINT);
			} else {
				preparedStatement.setByte(4, score.getSet3());
			}
			if (score.getSet4() == null) {
				preparedStatement.setNull(5, Types.TINYINT);
			} else {
				preparedStatement.setByte(5, score.getSet4());
			}
			if (score.getSet5() == null) {
				preparedStatement.setNull(6, Types.TINYINT);
			} else {
				preparedStatement.setByte(6, score.getSet5());
			}
			preparedStatement.executeUpdate();

			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				score.setId(rs.getLong(1));
				;
			}

			System.out.println("Score crée");

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

	public Score getById(Long id) {

		Score score = null;
		Session session = null;
		session = HibernateUtil.getSessionfactory().getCurrentSession();
		score = session.get(Score.class, id);
		System.out.println("Score lu");
		return score;
	}

	public void delete(Long id) {

		Session session = HibernateUtil.getSessionfactory().getCurrentSession();
		Score score = session.get(Score.class, id);
		session.delete(score);
		System.out.println("Score supprimé");

	}
}
