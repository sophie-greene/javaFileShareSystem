package fileShare;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SearchControlServlet
 */
public class SearchControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchControlServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UserModel user = new UserModel();
		ArrayList<FileModel> file = new ArrayList<FileModel>();
		HttpSession session = request.getSession(true);
		try {
			if (request.getParameter("search").length() > 1) {
				if (session.getAttribute("currentSessionUser") != null) {
					user = (UserModel) session
							.getAttribute("currentSessionUser");
					file = FileDAO.search(request.getParameter("search"), user
							.getUid());

					session.setAttribute("allfiles", file);

					session.setAttribute("Message", "Search result for "
							+ request.getParameter("search") + ": "
							+ file.size() + " matches");
					response.sendRedirect("files.jsp"); // logged-in page
				} else
					response.sendRedirect("files.jsp");
			} else
				response.sendRedirect("files.jsp");

		} catch (Throwable theException) {

			System.out.println(theException);
		}
	}
}
