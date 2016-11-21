package fileShare;

public class FileHasTagModel {
	// attributes
	private int tfid;

	private String file;
	private String tag;
	private boolean exists;
	// constructor
	public FileHasTagModel() {
		super();
	}

	/**
	 * @return the exists
	 */
	public boolean doesExist() {
		return exists;
	}

	public String getFile() {
		return file;
	}

	public String getTag() {
		return tag;
	}

	// getters and setters to access attributes
	public int getTfid() {
		return tfid;
	}

	/**
	 * @param exists
	 *            the exists to set
	 */
	public void setExists(boolean exists) {
		this.exists = exists;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public void setTfid(int tfid) {
		this.tfid = tfid;
	}

}
