package fileShare;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthorisationFilter
 */
public class AuthorisationFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public AuthorisationFilter() {
		// TODO Auto-generated constructor stub
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
			FilterChain chain) throws IOException, ServletException,
			NullPointerException {
		// get the request and response info
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = ((HttpServletRequest) request).getSession(false);
		try {
			// if no user is set as the current user redirect the request to an
			// error page
			if (session.getAttribute("currentSessionUser") != null) {
				// read maximum file size allowed
				ArrayList<String> acl = AccessHandler.XMLRreader();
				// maxsize is stored in the last field of the array list
				// index where maxsize is stored is sizeof the arraylist -1
				session.setAttribute("maxsize", acl.get(acl.size() - 1));
				chain.doFilter(request, response);
			}
			// else continue by allowing the request to pass through the filter
			else {
				httpResponse.sendRedirect("error.jsp");
			}
		} catch (ServletException e1) {
			httpResponse.sendRedirect("error.jsp");

		} catch (IOException e) {
			httpResponse.sendRedirect("error.jsp");
		} catch (NullPointerException e2) {
			httpResponse.sendRedirect("error.jsp");
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
