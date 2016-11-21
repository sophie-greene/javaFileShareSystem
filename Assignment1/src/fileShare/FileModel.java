package fileShare;

public class FileModel {
	private int inode; // file id
	private String fname; // file name
	private String description; // file description (optional)
	private String fpath; // file path
	private int access; // access rights
	private String tupload; // time and date file uploaded
	private float fsize; // file size KB
	private String mtime; // last modified time of file
	private String email; // owner id (foreign key from user table)
	private String type; // file type
	private boolean exists;// indicate if the file already exists in the user
							// files

	public FileModel() {

		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the exists
	 */
	public boolean doesExist() {
		return exists;
	}

	/**
	 * @return the access
	 */
	public int getAccess() {
		return access;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the fname
	 */
	public String getFname() {
		return fname;
	}

	/**
	 * @return the fpath
	 */
	public String getFpath() {
		return fpath;
	}

	public float getFsize() {

		return fsize;
	}

	/**
	 * @return the inode
	 */
	public int getInode() {
		return inode;
	}

	/**
	 * @return the mtime
	 */
	public String getMtime() {
		return mtime;
	}

	/**
	 * @return the tupload
	 */
	public String getTupload() {
		return tupload;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param access
	 *            the access to set
	 */
	public void setAccess(int access) {
		this.access = access;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param exists
	 *            the exists to set
	 */
	public void setExists(boolean exists) {
		this.exists = exists;
	}

	/**
	 * @param fname
	 *            the fname to set
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}

	/**
	 * @param fpath
	 *            the fpath to set
	 */
	public void setFpath(String fpath) {
		this.fpath = fpath;
	}

	/**
	 * @param fsize
	 *            the fsize to set
	 */
	public void setFsize(float fsize) {
		this.fsize = fsize;
	}

	/**
	 * @param inode
	 *            the inode to set
	 */
	// file id
	public void setInode(int inode) {
		this.inode = inode;
	}

	/**
	 * @param mtime
	 *            the mtime to set
	 */
	public void setMtime(String mtime) {
		this.mtime = mtime;
	}

	/**
	 * @param tupload
	 *            the tupload to set
	 */
	public void setTupload(String tupload) {
		this.tupload = tupload;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

}
