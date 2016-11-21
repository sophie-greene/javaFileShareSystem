package fileShare;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author somoud user data access object
 */
public class UserDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;

	public static ArrayList<UserModel> findAll() {
		// preparing some objects for connection
		Statement stmt = null;
		UserModel[] user = null;
		ArrayList<UserModel> Array = new ArrayList<UserModel>();
		int gid[] = null;
		int i, ss = 0;
		try {
			// connect to DB
			currentCon = ConnectionManager.createConnection();
			String searchQuery = "SELECT * FROM user";
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			ss = 0;
			while (rs.next())
				ss = ss + 1;

			rs.first();
			// Allocate required memory for the records retrieved from database
			user = new UserModel[ss];
			// Array.ensureCapacity(ss);
			gid = new int[ss];

			for (i = 0; i < ss; i++) {
				// copy the whole file record to the file object
				user[i] = new UserModel();
				user[i].setUid(rs.getInt(1));
				if (rs.getString(2) != null)
					user[i].setEmail(rs.getString(2));
				if (rs.getString(4) != null)
					user[i].setFirstname(rs.getString(4));
				if (rs.getString(5) != null)
					user[i].setLastname(rs.getString(5));
				gid[i] = rs.getInt(6);
				rs.next();
			}

			for (i = 0; i < ss; i++) {

				searchQuery = "SELECT * FROM cat WHERE gid=" + gid[i];

				stmt = currentCon.createStatement();
				rs = stmt.executeQuery(searchQuery);
				boolean more = rs.next();
				// if user does not exist set the isValid variable to false
				if (!more) {
					System.out.println("no records found");
				} else {
					user[i].setGroup(rs.getString(2));
				}
			}
			for (i = 0; i < ss; i++) {
				Array.add(user[i]);
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

	/**
	 * @param user
	 */
	/*
	 * get info from registration form and either write it to database or send
	 * an error
	 */
	public static UserModel login(UserModel user) {

		// preparing some objects for connection
		Statement stmt = null;
		String email = user.getEmail();
		int gid;
		String password = user.getPassword();
		String searchQuery = "SELECT * FROM user WHERE email='" + email
				+ "' AND password='" + password + "'";
		try {
			// connect to DB
			currentCon = ConnectionManager.createConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean more = rs.next();
			// if user does not exist set the isValid variable to false
			if (!more) {
				user.setValid(false);
			}
			// if user exists set the isValid variable to true
			else if (more) {
				// copy the whole user record to the user object except password
				user.setUid(rs.getInt(1));
				user.setFirstname(rs.getString(4));
				user.setLastname(rs.getString(5));
				gid = rs.getInt(6);
				user.setValid(true);
				// get the user's group form by querying the database
				searchQuery = "SELECT * FROM cat WHERE gid=" + gid;
				stmt = currentCon.createStatement();
				rs = stmt.executeQuery(searchQuery);
				if (rs.next())
					user.setGroup(rs.getString(2));

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
		return user;
	}

	public static boolean register(UserModel user) {
		// preparing some objects for connection
		Statement stmt = null;
		int gid = -1;

		boolean status = false;
		// get the user's gid form by querying the database
		String searchQuery = "SELECT * FROM cat WHERE gname='"
				+ user.getGroup() + "'";
		try {

			// connect to DB
			currentCon = ConnectionManager.createConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);

			if (rs.next())
				gid = rs.getInt(1);
			// check if user already exists
			searchQuery = "SELECT * FROM user where email='" + user.getEmail()
					+ "'";
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			if (rs.next()) {
				user.setExists(true);
				return false;
			} else {
				user.setExists(false);
			}
			// System.out.println("finished");
			// write data to database
			searchQuery = "INSERT INTO user (email ,password ,firstname ,lastname,fk1_gid)VALUES "
					+ "( '"
					+ user.getEmail()
					+ "', '"
					+ user.getPassword()
					+ "', '"
					+ user.getFirstname()
					+ "', '"
					+ user.getLastname() + "', '" + gid + "')";
			stmt = currentCon.createStatement();
			int xrs = stmt.executeUpdate(searchQuery);

			// if insert is successful
			if (xrs > 0) {
				status = true;

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

	public static boolean update(int id, String parameter) {
		// preparing some objects for connection
		Statement stmt = null;

		try {
			// connect to DB
			currentCon = ConnectionManager.createConnection();
			String searchQuery = "UPDATE user SET fk1_gid='" + parameter
					+ "' WHERE uid=" + id;
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
}
