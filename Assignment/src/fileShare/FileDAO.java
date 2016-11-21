package fileShare;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FileDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;

	// checks if a file object exists in an ArrayList of files
	/**
	 * @param array
	 * @param file
	 * @return
	 */
	private static boolean containtsRecord(ArrayList<FileModel> array,
			FileModel file) {
		boolean contains = false;
		for (int i = 0; i < array.size(); i++) {
			if (array.get(i).getInode() == file.getInode()) {
				contains = true;
				break;
			}
		}
		return contains;
	}

	private static void copyResult(ArrayList<FileModel> Array)
			throws SQLException {
		FileModel[] file = null;
		int email[] = null;
		int i, ss = 0;
		String searchQuery = null;
		try {
			while (rs.next())
				ss = ss + 1;

			rs.first();
			// Allocate required memory for the records retrieved from database
			file = new FileModel[ss];
			// Array.ensureCapacity(ss);
			email = new int[ss];

			for (i = 0; i < ss; i++) {
				// copy the whole file record to the file object
				file[i] = new FileModel();
				// check if file can be viewed by the user before storing it
				// if (checkAccess (rs.getInt(5)));
				file[i].setInode(rs.getInt(1));
				if (rs.getString(2) != null)
					file[i].setFname(rs.getString(2));
				if (rs.getString(3) != null)
					file[i].setDescription(rs.getString(3).toString());
				if (rs.getString(4) != null)
					file[i].setFpath(rs.getString(4));
				if (rs.getString(5) != null)
					file[i].setAccess(rs.getInt(5));
				if (rs.getString(6) != null)
					file[i].setTupload(rs.getString(6));
				if (rs.getString(7) != null)
					file[i].setFsize(rs.getFloat(7));
				if (rs.getString(8) != null)
					file[i].setMtime(rs.getString(8));
				if (rs.getString(10) != null)
					file[i].setType(rs.getString(10));
				email[i] = rs.getInt(9);
				rs.next();
			}

			for (i = 0; i < ss; i++) {

				searchQuery = "SELECT * FROM user WHERE uid=" + email[i];

				Statement stmt = currentCon.createStatement();
				rs = stmt.executeQuery(searchQuery);
				boolean more = rs.next();
				// if user does not exist set the isValid variable to false
				if (!more) {
					System.out
							.println("Sorry, you are not a registered user! Please sign up first");
				} else {
					file[i].setEmail(rs.getString(2));
				}
			}
			for (i = 0; i < ss; i++) {
				// if file is in array don't add it
				if (!containtsRecord(Array, file[i]))
					Array.add(file[i]);
			}

		} catch (SQLException e) {
			System.out.println("sql error: " + e);
		}
	}

	public static boolean delete(FileModel file) {
		// get file details

		// preparing some objects for connection
		Statement stmt = null;
		boolean more = false;
		String searchQuery = "SELECT * FROM file WHERE inode="
				+ file.getInode();
		try {
			// connect to DB
			currentCon = ConnectionManager.createConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			more = rs.next();
			// if file does not exist set the isValid variable to false
			if (!more) {
				System.out.println("Sorry, file does not exist");

			}
			// if user exists set the isValid variable to true
			else if (more) {
				// copy the whole file record to the file object
				file.setFname(rs.getString(2).toString());
				file.setDescription(rs.getString(3).toString());
				file.setFpath(rs.getString(4));
				file.setAccess(rs.getInt(5));
				file.setTupload(rs.getString(6));
				file.setFsize(rs.getFloat(7));
				file.setMtime(rs.getString(8));
				file.setType(rs.getString(10));
				// delete file record
				searchQuery = "DELETE FROM file WHERE inode=" + file.getInode();
				currentCon = ConnectionManager.createConnection();
				stmt = currentCon.createStatement();
				int rsx = stmt.executeUpdate(searchQuery);
				if (rsx > 0)
					more = true;
				else
					more = false;
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

		return more;
	}

	/**
	 * @param file
	 * @return
	 */

	public static ArrayList<FileModel> findAll(int uid) {

		// preparing some objects for connection
		Statement stmt = null;
		FileModel[] file = null;
		ArrayList<FileModel> Array = new ArrayList<FileModel>();
		int email[] = null;
		int i, ss = 0;
		try {
			// connect to DB
			currentCon = ConnectionManager.createConnection();
			String searchQuery = "SELECT * FROM file where fk1_uid=" + uid
					+ " UNION SELECT * FROM file where access > 700";
			// uid is set to -1 when an admin is accessing files
			if (uid == -1)
				searchQuery = "SELECT * FROM file";
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			ss = 0;
			while (rs.next())
				ss = ss + 1;

			rs.first();
			// Allocate required memory for the records retrieved from database
			file = new FileModel[ss];
			// Array.ensureCapacity(ss);
			email = new int[ss];

			for (i = 0; i < ss; i++) {
				// copy the whole file record to the file object
				file[i] = new FileModel();
				file[i].setInode(rs.getInt(1));
				if (rs.getString(2) != null)
					file[i].setFname(rs.getString(2));
				if (rs.getString(3) != null)
					file[i].setDescription(rs.getString(3).toString());
				if (rs.getString(4) != null)
					file[i].setFpath(rs.getString(4));
				if (rs.getString(5) != null)
					file[i].setAccess(rs.getInt(5));
				if (rs.getString(6) != null)
					file[i].setTupload(rs.getString(6));
				if (rs.getString(7) != null)
					file[i].setFsize(rs.getFloat(7));
				if (rs.getString(8) != null)
					file[i].setMtime(rs.getString(8));
				if (rs.getString(10) != null)
					file[i].setType(rs.getString(10));
				email[i] = rs.getInt(9);
				rs.next();
			}

			for (i = 0; i < ss; i++) {

				searchQuery = "SELECT * FROM user WHERE uid=" + email[i];

				stmt = currentCon.createStatement();
				rs = stmt.executeQuery(searchQuery);
				boolean more = rs.next();
				// if user does not exist set the isValid variable to false
				if (!more) {
					System.out.println("no records found");
				} else {
					file[i].setEmail(rs.getString(2));
				}
			}
			for (i = 0; i < ss; i++) {
				Array.add(file[i]);
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

	public static ArrayList<FileModel> search(String search, int uid) {

		// preparing some objects for connection
		Statement stmt = null;
		ArrayList<FileModel> Array = new ArrayList<FileModel>();
		String[] sParts = search.split(" ");
		int i;
		try {
			// connect to DB
			currentCon = ConnectionManager.createConnection();
			String searchQuery = "SELECT * FROM FILE f INNER JOIN FILE_HAS_TAG fht ON f.inode = fht.s_inode and (fk1_uid="
					+ uid
					+ " or f.access>700)  INNER JOIN TAG t ON fht.d_tid = t.tid WHERE (t.tname like '%"
					+ search + "%'";
			// add search for words in the search phrase if the phrase has more
			// than one word
			// result will be added to the previous search result
			if (sParts.length > 1) {
				for (i = 0; i < sParts.length; i++) {
					if (sParts[i].length() > 0) {
						searchQuery = searchQuery + "OR t.tname like '%"
								+ sParts[i] + "%'";

					}
				}
			}
			searchQuery = searchQuery + ")";
			// System.out.println(searchQuery);
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			copyResult(Array);
			// search for the search phrase in file name and description
			searchQuery = "SELECT * FROM FILE where ((fname like '%" + search
					+ "%' and description like '%" + search + "%')";
			// add search for words in the search phrase if the phrase has more
			// than one word
			if (sParts.length > 1) {
				for (i = 0; i < sParts.length; i++) {
					if (sParts[i].length() > 0) {
						searchQuery = searchQuery + "OR (fname like '%"
								+ sParts[i] + "%'and description like '%"
								+ sParts[i] + "%')";

					}
				}
			}
			searchQuery = searchQuery + ")";
			// System.out.println(searchQuery);
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			copyResult(Array);

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

	public static boolean update(int id, String parameter) {
		// preparing some objects for connection
		Statement stmt = null;

		try {
			// connect to DB
			currentCon = ConnectionManager.createConnection();
			String searchQuery = "UPDATE file SET access='" + parameter
					+ "' WHERE fid=" + id;
			stmt = currentCon.createStatement();
			int xrs = stmt.executeUpdate(searchQuery);

			// if insert is successful
			if (xrs < 0)
				return false;
		} catch (Exception ex) {
			System.out.println("Search failed: An Exception has occurred! "
					+ ex);
			return false;
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
		return true;

	}

	public static boolean upload(FileModel file) {
		// preparing some objects for connection
		Statement stmt = null;
		boolean status = false;
		int uid = -1;
		String searchQuery = "SELECT * FROM user WHERE email='"
				+ file.getEmail() + "'";
		try {
			currentCon = ConnectionManager.createConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			if (rs.next())
				uid = rs.getInt(1);
			// check if file name already exist for the current user
			searchQuery = "SELECT * FROM file WHERE fname='" + file.getFname()
					+ "' AND fk1_uid=" + uid;
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			if (rs.next()) {
				file.setExists(true);
				return false;
			} else {
				file.setExists(false);

				// write data to database
				searchQuery = "INSERT INTO file ( fname, description,fpath, access, tupload, size,  mtime, fk1_uid,type) VALUES "
						+ "( '"
						+ file.getFname()
						+ "', '"
						+ file.getDescription()
						+ "', '"
						+ file.getFpath()
						+ "', '"
						+ file.getAccess()
						+ "', '"
						+ file.getTupload()
						+ "', '"
						+ file.getFsize()
						+ "', '"
						+ file.getMtime()
						+ "', "
						+ uid
						+ ", '"
						+ file.getType() + "')";
				stmt = currentCon.createStatement();
				int xrs = stmt.executeUpdate(searchQuery);

				// if user does not exist set the isValid variable to false
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