package fileShare;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author somoud user data access object
 */

public class TagDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;

	/**
	 * @param tag
	 */
	/*
	 * get info from database
	 */

	public static ArrayList<TagModel> retrieveAll() {

		// preparing some objects for connection
		Statement stmt = null;
		TagModel[] tag = null;
		ArrayList<TagModel> Array = new ArrayList<TagModel>();
		int i, ss = 0;
		try {
			// connect to DB
			currentCon = ConnectionManager.createConnection();
			String searchQuery = "SELECT * FROM tag";
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			ss = 0;
			while (rs.next())
				ss = rs.getInt(1);

			rs.first();
			// Allocate required memory for the records retrieved from database

			tag = new TagModel[ss];

			// Array.ensureCapacity(ss);
			for (i = 0; i < ss; i++) {

				// copy the whole tag record to the tag object
				tag[i] = new TagModel();
				tag[i].setTid(rs.getInt(1));
				tag[i].setTname(rs.getString(2));
				tag[i].setExists(true);
				rs.next();
			}

			for (i = 0; i < ss; i++) {
				Array.add(tag[i]);
			}
		} catch (Exception ex) {
			System.out.println("failed: An Exception has occurred! " + ex);
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

	public static boolean store(TagModel tag) {
		// preparing some objects for connection
		Statement stmt = null;

		boolean status = false;
		// check if tag already exists
		String searchQuery = "SELECT * FROM tag where tname='" + tag.getTname()
				+ "'";
		try {

			// connect to DB
			currentCon = ConnectionManager.createConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);

			if (rs.next()) {
				tag.setExists(true);
				return false;
			} else {
				tag.setExists(false);

				// System.out.println("finished");
				// write data to database
				searchQuery = "INSERT INTO tag (tname)VALUES " + "( '"
						+ tag.getTname() + "')";
				stmt = currentCon.createStatement();
				int xrs = stmt.executeUpdate(searchQuery);

				// if insert is successful
				if (xrs > 0) {
					status = true;

				}
			}
		} catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! "
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
		return status;

	}

}
