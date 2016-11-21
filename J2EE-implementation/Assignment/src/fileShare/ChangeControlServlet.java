package fileShare;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ChangeControlServlet
 */
public class ChangeControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangeControlServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		try {
			if (session.getAttribute("currentSessionUser") != null) {
				UserModel user = (UserModel) session
						.getAttribute("currentSessionUser");
				// make sure the request was received when an admin is
				// authenticated
				if (user.getGroup().equals("admin")) {
					ArrayList<UserModel> users = UserDAO.findAll();

					session.setAttribute("allusers", users);
					response.sendRedirect("controlPanel.jsp"); // logged-in page
				} else
					response.sendRedirect("error.jsp");
			} else
				response.sendRedirect("error.jsp");
		} catch (Throwable theException) {

			System.out.println(theException);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String message = null;
		try {
			// check if the request is for changing an ip

			if (request.getParameter("ip") != null) {
				// check if ip is valid

				if (AccessHandler.checkIP(request.getParameter("ip").trim())) {

					// System.out.println("hello :"+request.getParameter("ip")+" "+
					// request.getParameter("action"));
					if (request.getParameter("action").contains("add")) {
						AccessHandler.XMLAdd("IP", request.getParameter("ip"));
						message = "IP " + request.getParameter("ip") + " added";
					}
					if (request.getParameter("action").contains("remove")) {
						AccessHandler.XMLRemove("IP", request
								.getParameter("ip"));
						message = "IP " + request.getParameter("ip")
								+ " removed";
					}
				}
			}
			if (request.getParameter("maxsize") != null) {
				// check if maxsize is valid
				if (request.getParameter("maxsize").matches("[0-9]*")) {
					if (Long.parseLong(request.getParameter("maxsize")) > 0)
						AccessHandler.XMLModify("MAXSIZE", request
								.getParameter("maxsize"));
					message = "Maximum file size has been changed to "
							+ request.getParameter("maxsize");
				} else {
					message = "Invalid file size ("
							+ request.getParameter("maxsize")
							+ ") make sure you enter a valid number";
				}
			}
			if (request.getParameter("user") != null) {
				// change user group
				if (request.getParameter("ugroup") != null) {
					UserDAO.update(Integer.parseInt(request
							.getParameter("user")), request
							.getParameter("ugroup"));
					message = "User group has been updates";
				}
			}
			if (request.getParameter("file") != null) {
				// change file access
				if (request.getParameter("fgroup") != null) {
					UserDAO.update(Integer.parseInt(request
							.getParameter("file")), request
							.getParameter("fgroup"));
					message = "File Access has been updates";
				}
			}
			response.sendRedirect("controlPanel.jsp");
			session.setAttribute("changeMessage", message);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
