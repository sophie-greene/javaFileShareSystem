package fileShare;

import java.io.IOException;

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
 * Servlet Filter implementation class AdminFilter
 */
public class AdminFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public AdminFilter() {
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
			FilterChain chain) throws IOException, ServletException {
		// get the request and response info
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = ((HttpServletRequest) request).getSession(false);
		try {
			if (session.getAttribute("currentSessionUser") != null) {
				UserModel f = (UserModel) session
						.getAttribute("currentSessionUser");
				// if user is admin goto pages
				if (f.getGroup().equals("admin"))
					chain.doFilter(request, response);
				else
					httpResponse.sendRedirect("errorAdmin.jsp");
			}
			// else continue by allowing the request to pass through the filter
			else {
				httpResponse.sendRedirect("errorAdmin.jsp");
			}
		} catch (ServletException e1) {
			httpResponse.sendRedirect("errorAdmin.jsp");

		} catch (IOException e) {
			httpResponse.sendRedirect("errorAdmin.jsp");
		} catch (NullPointerException e2) {
			httpResponse.sendRedirect("errorAdmin.jsp");
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
