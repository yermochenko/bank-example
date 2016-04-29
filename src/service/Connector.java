package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
	private static String jdbcUrl;
	private static String jdbcUser;
	private static String jdbcPassword;

	public static void init(String jdbcDriver, String jdbcUrl, String jdbcUser, String jdbcPassword) {
		try {
			Class.forName(jdbcDriver);
			Connector.jdbcUrl = jdbcUrl;
			Connector.jdbcUser = jdbcUser;
			Connector.jdbcPassword = jdbcPassword;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection connect() throws SQLException {
		return DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
	}
}
