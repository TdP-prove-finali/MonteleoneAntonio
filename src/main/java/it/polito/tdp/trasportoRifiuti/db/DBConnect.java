package it.polito.tdp.trasportoRifiuti.db;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import it.polito.tdp.trasportoRifiuti.model.Model;

public class DBConnect {

	private static final String jdbcURL = "jdbc:mysql://localhost/amiu_db";
	private static HikariDataSource ds;
	
	public static Connection getConnection() {
		
		if (ds == null) {
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(jdbcURL);
			config.setUsername("root");
			config.setPassword("root");
			
			// configurazione MySQL
			config.addDataSourceProperty("cachePrepStmts", "true");
			config.addDataSourceProperty("prepStmtCacheSize", "250");
			config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
			
			ds = new HikariDataSource(config);
		}
		
		try {
			
			return ds.getConnection();

		} catch (SQLException e) {
			System.err.println("Errore connessione al DB");
			throw new RuntimeException(e);
		}
	}
}

//private Model model;
/* public void setModel(Model model) {
this.model = model;
}*/
