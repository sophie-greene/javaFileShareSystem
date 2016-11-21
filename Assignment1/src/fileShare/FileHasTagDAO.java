package fileShare;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author somoud;
 */
/* tag file data access object */
public class FileHasTagDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;

	public static void delete(int inode) {

		// get file details
		// preparing some objects for connection
		Statement stmt = null;

		String searchQuery = "SELECT * FROM file WHERE inode=" + inode;
		try {
			// connect to DB
			currentCon = ConnectionManager.createConnection();
			stmt = currentCon.createStatement();

			// delete file record
			searchQuery = "DELETE FROM file_has_tag WHERE s_inode=" + inode;
			currentCon = ConnectionManager.createConnection();
			stmt = currentCon.createStatement();
			stmt.executeUpdate(searchQuery);

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

	}

	/**
	 * @param tag
	 */
	/*
	 * get info from database
	 */

	public static ArrayList<FileHasTagModel> retrieveAll() {

		// preparing some objects for connection
		Statement stmt = null;
		FileHasTagModel[] tagfile = null;
		ArrayList<FileHasTagModel> Array = new ArrayList<FileHasTagModel>();
		int i, ss = 0;
		try {
			// connect to DB
			currentCon = ConnectionManager.createConnection();
			String searchQuery = "SELECT * FROM file_has_tag";
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			ss = 0;
			while (rs.next())
				ss = rs.getInt(1);

			rs.first();
			// Allocate required memory for the records retrieved from database

			tagfile = new FileHasTagModel[ss];

			// Array.ensureCapacity(ss);
			for (i = 0; i < ss; i++) {

				// copy the whole tag record to the tag object
				tagfile[i] = new FileHasTagModel();
				tagfile[i].setTfid(rs.getInt(1));
				tagfile[i].setFile(rs.getString(2));
				tagfile[i].setExists(true);
				rs.next();
			}

			for (i = 0; i < ss; i++) {
				Array.add(tagfile[i]);
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

	public static boolean store(FileHasTagModel tagfile, int uid) {
		// preparing some objects for connection
		Statement stmt = null;
		boolean status = false;
		int tid = -1, fid = -1;
		// get tagname's corresponding tid
		String searchQuery = "SELECT * FROM tag where tname='"
				+ tagfile.getTag() + "'";
		try {

			// connect to DB
			currentCon = ConnectionManager.createConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);

			if (rs.next()) {
				tid = rs.getInt(1);
			}
			// get filename's correspoding fid
			searchQuery = "SELECT * FROM file where fname='"
					+ tagfile.getFile() + "' and fk1_uid=" + uid;
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);

			if (rs.next()) {
				fid = rs.getInt(1);
			}
			// System.out.println("finished");
			// write data to database
			searchQuery = "INSERT INTO file_has_tag (s_inode,d_tid)VALUES "
					+ "( " + fid + "," + tid + ")";
			stmt = currentCon.createStatement();
			int xrs = stmt.executeUpdate(searchQuery);

			// if insert is successful
			if (xrs > 0) {
				status = true;

			}
		} catch (Exception ex) {
			System.out.println("tag-file failed: An Exception has occurred! "
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
