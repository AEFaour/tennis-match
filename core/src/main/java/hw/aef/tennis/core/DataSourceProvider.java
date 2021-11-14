package hw.aef.tennis.core;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class DataSourceProvider {
	private static BasicDataSource singleDataSource;
	
	public static DataSource getSingleDataSourceInstance() {
		if(singleDataSource == null) {
			singleDataSource = new BasicDataSource();
			singleDataSource.setInitialSize(5);
			singleDataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false&"
					+ "useLegacyDatetimeCode=false&serverTimezone=Europe/Paris");

			singleDataSource.setUsername("root");
			singleDataSource.setPassword("root");
		}
		return singleDataSource;
	}

}
