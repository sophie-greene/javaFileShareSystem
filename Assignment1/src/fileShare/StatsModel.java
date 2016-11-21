package fileShare;

/**
 * @author somoud
 * 
 */
public class StatsModel {
	private int id;
	private String uri;
	private String protocol;
	private String referer;
	private String user_agent;
	private String host;
	private String accept;

	public String getAccept() {
		return accept;
	}

	public String getHost() {
		return host;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	public String getProtocol() {
		return protocol;
	}

	public String getReferer() {
		return referer;
	}

	public String getUri() {
		return uri;
	}

	public String getUser_agent() {
		return user_agent;
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}

	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public void setUser_agent(String user_agent) {
		this.user_agent = user_agent;
	}

}
