package fileShare;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class AccessFilter
 */
public class AccessFilter implements Filter {

	private FilterConfig fConfig = null;

	/**
	 * Default constructor.
	 */
	public AccessFilter() {
	}

	/**
	 * @param requestedIP
	 * @param StoredIP
	 * @return boolean
	 */
	// this function takes two IP addresses and finds if they match
	// ips match if each of their fragments match or the stored IP fragment is
	// zero
	public boolean containsIP(String requestedIP, String storedIP) {

		String[] rParts = requestedIP.split("\\.");
		String[] sParts = storedIP.split("\\.");
		// assume the strings match unless u find otherwise
		boolean match = true;
		int stemp = 0;
		int rtemp = 0;
		int j;
		int leng = rParts.length;

		if (leng > 1) {
			for (j = 0; j < leng; j++) {
				stemp = Integer.parseInt(sParts[j]);
				rtemp = Integer.parseInt(rParts[j]);
				if (stemp != rtemp && stemp != 0) {
					match = false;
					break;
				}
			}

		} else
			match = false;

		return match;

	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// log user ip
		ServletContext sc = fConfig.getServletContext();
		sc.log(request.getRemoteAddr());
		// read admin settings of restricted IP addresses and max file size
		ArrayList<String> acl = AccessHandler.XMLRreader();
		boolean restrict = false;
		// pass the request along the filter chain
		// this allows the rest of the application to be accessible
		String userip = request.getRemoteAddr();
		HttpServletResponse httpResponse = null;
		if (response instanceof HttpServletResponse) {
			httpResponse = (HttpServletResponse) response;
		}
		// check acl to find if remote addr is in the restricted list
		for (int i = 0; i < acl.size() - 1; i++) {
			if (containsIP(userip, acl.get(i).toString()))
				restrict = true;
		}
		if (restrict) {
			httpResponse
					.sendError(
							HttpServletResponse.SC_FORBIDDEN,
							"You are not  allowed to access this website, This is due to your IP address being on our restricted list!");
			System.out.println(request.getRemoteAddr() + " was restricted");
		} else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {

		this.fConfig = fConfig;
	}

}
