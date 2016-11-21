package fileShare;

public class TagModel {
	// attributes
	private int tid;

	private String tname;
	private boolean exists;
	// Constructor
	public TagModel() {
		super();
	}

	/**
	 * @return the exists
	 */
	public boolean doesExist() {
		return exists;
	}

	// setters and getters for attributes
	public int getTid() {
		return tid;
	}

	public String getTname() {
		return tname;
	}

	/**
	 * @param exists
	 *            the exists to set
	 */
	public void setExists(boolean exists) {
		this.exists = exists;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

}
