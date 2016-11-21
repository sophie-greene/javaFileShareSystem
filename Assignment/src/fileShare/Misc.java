package fileShare;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Misc {

	public static boolean deleteFile(String strFile) {

		File f = new File(strFile);
		// Attempt to delete it
		boolean success = f.delete();

		if (!success)
			throw new IllegalArgumentException("Delete: deletion failed");
		return success;
	}

	public static String FileExists(String strFile) {
		// A File object to represent the filename
		File f = new File(strFile);
		String msg = "";
		// Make sure the file or directory exists and isn't write protected
		if (!f.exists())
			msg = "Delete: no such file or directory: " + strFile;

		if (!f.canWrite())
			msg = "Delete: write protected: " + strFile;

		// If it is a directory, make sure it is empty
		if (f.isDirectory()) {
			String[] files = f.list();
			if (files.length > 0)
				msg = "Delete: directory not empty: " + strFile;
		}
		return msg;
	}

	public static String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
}
