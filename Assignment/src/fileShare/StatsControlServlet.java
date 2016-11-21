package fileShare;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class StatsControlServlet
 */
public class StatsControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StatsControlServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// store header info
		StatsDAO.store(request, response);
		HttpSession session = request.getSession(true);
		try {
			// only read stats if logged in as admin
			if (session.getAttribute("currentSessionUser") != null) {
				UserModel user = (UserModel) session
						.getAttribute("currentSessionUser");
				if (user.getGroup().equals("admin")) {
					// get hosts and occurrences
					ArrayList<Stat> hosts = StatsDAO.getHosts("host");
					session.setAttribute("host", hosts);
					// get referrer and occurrences
					ArrayList<Stat> referers = StatsDAO.getHosts("referer");
					session.setAttribute("referer", referers);
					// get uri's and occurrences
					ArrayList<Stat> uris = StatsDAO.getHosts("uri");
					session.setAttribute("uri", uris);
				}
			}

			response.sendRedirect("referer"); // statspage
		} catch (IOException e) {
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
