package fileShare;

//used to store host statistics
public class Stat {
	private String hostName;
	private int occur;

	// constructor
	public Stat() {
		super();
	}

	public String getHostName() {
		return hostName;
	}

	public int getOccur() {
		return occur;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public void setOccur(int occur) {
		this.occur = occur;
	}

}
