package fileShare;

import java.util.ArrayList;

/**
 * @author Somoud
 * 
 */
public class UploadFormValidationModel {
	/* The properties */
	String file;
	String tag1;
	String tag2;
	String tag3;
	String tag4;
	String access;
	/* Errors */
	public static final String ERR_FILE_ENTER = "Please enter a file name";
	public static final String ERR_FILE_INVALID = "The file name is invalid";
	public static final String ERR_ACCESS_ENTER = "Please enter access rights";
	public static final String ERR_ACCESS_INVALID = "The access right is not valid";
	public static final String ERR_TAGS_ENTER = "Please enter at least one file tag";
	public static final String ERR_TAGS_INVALID = "The tag is invalid, only letters, numbers and - are accepted";
	// Holds error messages for the properties, file msg at 0, tag msg at 1 and
	// so on
	ArrayList<String> errorCodes = new ArrayList<String>();

	// find the corresponding error code of a property
	private String findCode(String str) {
		String code = null;
		// iterate through the ArrayList to find str
		if (str.equals("file"))
			code = errorCodes.get(0);
		if (str.equals("tag1") || str.equals("tag2") || str.equals("tag3")
				|| str.equals("tag4"))
			code = errorCodes.get(1);
		if (str.equals("access"))
			code = errorCodes.get(2);
		return code;
	}

	// gets error message associated with a specific property
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

		// Validate file name
		if (file != null) {

			if (file.length() == 0) {
				errorCodes.add(0, ERR_FILE_ENTER);
				valid = false;
			} else
				errorCodes.add(0, "");
		} else {
			errorCodes.add(0, ERR_FILE_ENTER);
			valid = false;
		}

		// Validate tags, at least one tag should be entered
		if (tag1 != null || tag2 != null || tag3 != null || tag4 != null) {
			if (tag1.length() == 0 && tag2.length() == 0 && tag3.length() == 0
					&& tag4.length() == 0) {
				errorCodes.add(1, ERR_TAGS_ENTER);
				valid = false;
			} else if (!tag1.matches("[A-Za-z0-9-]+")
					|| !tag2.matches("[A-Za-z0-9-]+")
					|| !tag3.matches("[A-Za-z0-9-]+")
					|| !tag4.matches("[A-Za-z0-9-]+")) {
				errorCodes.add(1, ERR_TAGS_INVALID);
				valid = false;
			} else
				errorCodes.add(1, "");
		} else {
			errorCodes.add(1, ERR_TAGS_ENTER);
			valid = false;
		}

		// validate access rights only 7[0-7][0-7] is allowed
		if (access != null) {
			if (access.length() == 0) {
				errorCodes.add(2, ERR_ACCESS_ENTER);
				valid = false;
			} else if (!access.matches("^7[0-7][0-7]$")) {
				errorCodes.add(2, ERR_ACCESS_INVALID);
				valid = false;
			} else
				errorCodes.add(2, "");
			// validate last name
		} else {
			errorCodes.add(2, ERR_ACCESS_ENTER);
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

	public void setAccess(String access) {
		this.access = access.trim();
	}

	// get properties and get rid of any leading or trailing white space
	public void setFile(String file) {
		this.file = file;
	}

	public void setTag1(String tag1) {
		this.tag1 = tag1.trim();
	}

	public void setTag2(String tag2) {
		this.tag2 = tag2.trim();
	}

	public void setTag3(String tag3) {
		this.tag3 = tag3.trim();
	}

	public void setTag4(String tag4) {
		this.tag4 = tag4.trim();
	}

}
