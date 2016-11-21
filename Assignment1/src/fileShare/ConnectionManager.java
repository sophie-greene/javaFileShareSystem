package fileShare;

import java.sql.*;

import com.mysql.jdbc.Connection;

public class ConnectionManager {
	static Connection conn;
	static String host;
	static String database;
	static String user;
	static String passwd;

	public static Connection createConnection() {
		// could be read from .ini file
		Connection conn = null;
		String host = "localhost:3306";
		String database = "wse";
		String user = "root";
		String passwd = "";
		// First thing to do is ensure that driver class is loaded
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			// Then create connection to DB
			// Format of first string depends on the driver
			conn = (Connection) DriverManager.getConnection("jdbc:mysql://"
					+ host + "/" + database, user, passwd);

		} catch (SQLException e) {

			System.out.println("Error" + e.getMessage());

		} catch (Exception e) {

			System.out.println("Error while loading driver :" + e.getMessage());
		}

		return conn;
	}
}
