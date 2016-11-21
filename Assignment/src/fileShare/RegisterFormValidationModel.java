package fileShare;

import java.util.*;

/**
 * @author Somoud
 * 
 */
public class RegisterFormValidationModel {
	/* The properties */
	String email;
	String password;
	String cpassword;
	String firstname;
	String lastname;
	ArrayList<String[]> msgMap;
	/* Errors */
	public static final String ERR_EMAIL_ENTER = "Please enter an email address";
	public static final String ERR_EMAIL_INVALID = "The email address is invalid";
	public static final String ERR_PASSWORD_ENTER = "Please enter a Password";
	// password 6-12 character long (printable chars)
	public static final String ERR_PASSWORD_INVALID = "The password must be 6-12 characters";
	public static final String ERR_CPASSWORD_DONTMATCH = "The two passwords do not match";
	public static final String ERR_FIRSTNAME_ENTER = "Please enter your first name";
	public static final String ERR_LASTNAME_ENTER = "Please enter your last name";

	// Holds error messages for the properties
	ArrayList<String> errorCodes = new ArrayList<String>();

	// Maps error codes to textual messages.
	// This map must be supplied by the object that instantiated this bean.

	// find the corresponding error code of a property
	private String findCode(String str) {
		String code = null;
		// iterate through the ArrayList to find str
		if (str.equals("email"))
			code = errorCodes.get(0);
		if (str.equals("password"))
			code = errorCodes.get(1);
		if (str.equals("cpassword"))
			code = errorCodes.get(2);
		if (str.equals("firstname"))
			code = errorCodes.get(3);
		if (str.equals("lastname"))
			code = errorCodes.get(4);
		return code;
	}

	public String getErrorMessage(String propName) {
		// if the Array list is empty return an empty string
		try {
			if (errorCodes.isEmpty()) {
				return "";
			} else {

				String msg = findCode(propName.trim());

				if (msg != null) {
					return msg;
				}
			}
		} catch (Exception e) {
		}
		return "Error";
	}

	/* Form validation and processing */
	public boolean isValid() {

		// assume valid unless one of the if statements is true
		boolean valid = true;
		// Clear all errors
		errorCodes.clear();

		// Validate email
		if (email != null) {

			if (email.length() == 0) {
				errorCodes.add(0, "Please enter an email address");
				valid = false;
			} else if (!email.matches(".+@.+\\..+")) {
				errorCodes.add(0, "The email address is invalid");
				valid = false;
			} else
				errorCodes.add(0, "");
		} else {
			errorCodes.add(0, "Please enter an email address");
			valid = false;
		}

		// Validate password
		if (password != null) {
			if (password.length() == 0) {
				errorCodes.add(ERR_PASSWORD_ENTER);
				valid = false;
			} else if (password.length() < 6 || password.length() > 12) {
				errorCodes.add(1, ERR_PASSWORD_INVALID);
				valid = false;
			} else
				errorCodes.add(1, "");
		} else {
			errorCodes.add(1, ERR_PASSWORD_ENTER);
			valid = false;
		}
		if (password != null && cpassword != null) {
			if (!this.cpassword.equals(password)) {

				errorCodes.add(2, ERR_CPASSWORD_DONTMATCH);
				valid = false;
			} else
				errorCodes.add(2, "");

		} else {
			errorCodes.add(2, ERR_CPASSWORD_DONTMATCH);
			valid = false;
		}
		// validate first name
		if (firstname != null) {
			if (firstname.length() == 0) {
				errorCodes.add(3, ERR_FIRSTNAME_ENTER);
				valid = false;
			} else
				errorCodes.add(3, "");
			// validate last name
		} else {
			errorCodes.add(3, ERR_FIRSTNAME_ENTER);
			valid = false;
		}
		if (lastname != null) {
			if (lastname.length() == 0) {
				errorCodes.add(4, ERR_LASTNAME_ENTER);
				valid = false;
			} else
				errorCodes.add(4, "");
		} else {

			errorCodes.add(4, ERR_LASTNAME_ENTER);
			valid = false;
		}
		/*
		 * for(int i=0; i<errorCodes.size();i++){
		 * System.out.println(errorCodes.get(i)); }
		 */
		// If no errors, form is valid
		return valid;
	}

	public boolean process() {

		if (!isValid()) {
			return false;
		}
		errorCodes.clear();
		return true;
	}

	public void setCpassword(String cpassword) {
		this.cpassword = cpassword.trim();
	}

	// get properties and get rid of any leading or trailing white space
	public void setEmail(String email) {
		this.email = email.trim();
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname.trim();
	}

	public void setLastname(String lastname) {
		this.lastname = lastname.trim();
	}

	public void setPassword(String password) {
		this.password = password.trim();
	}

}
