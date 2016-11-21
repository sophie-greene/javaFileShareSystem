package fileShare;

import java.sql.*;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StatsDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;

	public static ArrayList<StatsModel> findAll() {

		// preparing some objects for connection
		Statement stmt = null;
		StatsModel[] stats = null;
		ArrayList<StatsModel> Array = new ArrayList<StatsModel>();
		int i, ss = 0;
		try {
			// connect to DB
			currentCon = ConnectionManager.createConnection();
			String searchQuery = "SELECT * FROM stats";
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			ss = 0;
			while (rs.next())
				ss = ss + 1;

			rs.first();
			// Allocate required memory for the records retrieved from database
			stats = new StatsModel[ss];

			for (i = 0; i < ss; i++) {
				// copy the whole stats record to the stats object
				stats[i] = new StatsModel();
				stats[i].setId(rs.getInt(1));
				if (rs.getString(2) != null)
					stats[i].setUri(rs.getString(2));
				if (rs.getString(3) != null)
					stats[i].setProtocol(rs.getString(3));
				if (rs.getString(4) != null)
					stats[i].setReferer(rs.getString(4));
				if (rs.getString(5) != null)
					stats[i].setUser_agent(rs.getString(5));
				if (rs.getString(6) != null)
					stats[i].setHost(rs.getString(6));
				if (rs.getString(7) != null)
					stats[i].setAccept(rs.getString(7));
				rs.next();
			}

			for (i = 0; i < ss; i++) {
				Array.add(stats[i]);
			}
		} catch (Exception ex) {
			System.out.println("Search failed: An Exception has occurred! "
					+ ex);
		}
		// some exception handling
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}

				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
				}
				stmt = null;
			}
			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}
				currentCon = null;
			}
		}

		return Array;

	}

	public static ArrayList<Stat> getHosts(String stat) {

		// preparing some objects for connection
		Statement stmt = null;
		ArrayList<Stat> Array = new ArrayList<Stat>();
		Stat[] h = null;
		int i, count = 0, ss = 0;
		try {
			// connect to DB
			currentCon = ConnectionManager.createConnection();
			String searchQuery = "SELECT DISTINCT " + stat + " FROM stats";
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			ss = 0;
			// count number of records found
			while (rs.next())
				ss = ss + 1;
			rs.first();
			// Allocate required memory for the records retrieved from database
			h = new Stat[ss];

			// read distinct values
			for (i = 0; i < ss; i++) {
				h[i] = new Stat();
				// copy the whole stats record to the stats object
				h[i].setHostName(rs.getString(1));
				rs.next();
			}
			for (i = 0; i < ss; i++) {
				searchQuery = "SELECT * FROM stats WHERE " + stat + "='"
						+ h[i].getHostName() + "'";
				stmt = currentCon.createStatement();
				rs = stmt.executeQuery(searchQuery);
				count = 0;

				while (rs.next())
					count = count + 1;
				h[i].setOccur(count);
			}

			for (i = 0; i < ss; i++) {
				Array.add(h[i]);
			}

		} catch (Exception ex) {
			System.out
					.println("getting stats failed: An Exception has occurred! "
							+ ex);
		}
		// some exception handling
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}

				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
				}
				stmt = null;
			}
			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}
				currentCon = null;
			}
		}

		return Array;

	}

	public static void store(HttpServletRequest req, HttpServletResponse res) {
		// preparing some objects for connection
		Statement stmt = null;
		String uri = "";
		String protocol = "";
		String referer = "";
		String user_agent = "";
		String host = "";
		String accept = "";

		uri = req.getRequestURI();
		protocol = req.getProtocol();
		if (req.getHeader("referer") != null)
			referer = req.getHeader("referer");
		if (req.getHeader("user-agent") != null)
			user_agent = req.getHeader("user-agent");
		if (req.getHeader("host") != null)
			host = req.getHeader("host");
		if (req.getHeader("accept") != null)
			accept = req.getHeader("accept");

		try {

			// connect to DB
			currentCon = ConnectionManager.createConnection();
			stmt = currentCon.createStatement();
			// write data to database
			String searchQuery = "INSERT INTO stats (uri,protocol,referer,user_agent,host,accept)VALUES "
					+ "( '"
					+ uri
					+ "', '"
					+ protocol
					+ "', '"
					+ referer
					+ "', '"
					+ user_agent
					+ "', '"
					+ host
					+ "', '"
					+ accept
					+ "')";
			stmt = currentCon.createStatement();
			stmt.executeUpdate(searchQuery);

		} catch (Exception ex) {
			System.out
					.println("Stats storage failed: An Exception has occurred! "
							+ ex);
		}
		// some exception handling
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}

				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
				}
				stmt = null;
			}
			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}
				currentCon = null;
			}
		}

	}
}