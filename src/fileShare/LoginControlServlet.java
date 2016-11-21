package fileShare;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginControlServlet
 */
public class LoginControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginControlServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * check if user is logged in --> log out doGet will only be called when
		 * a user is logging out but to be on the safe side we will check if
		 * they are logged in
		 */
		HttpSession session = request.getSession(false);
		if (session.getAttribute("currentSessionUser") != null) {
			session.removeAttribute("currentSessionUser");
			// free the session
			session.invalidate();
		}
		/*
		 * go to index page to make sure we avoid going back to a restricted
		 * page
		 */
		response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try { // use try and catch to cater for exceptions
			// create a new user object
			UserModel user = new UserModel();
			// read name and store it in user object
			user.setEmail(request.getParameter("name"));
			// read password store it in user object
			user.setPassword(request.getParameter("password"));
			/*
			 * call the user data access object to validate the given data this
			 * will also read the rest of the user details if the given data is
			 * valid
			 */
			user = UserDAO.login(user);
			HttpSession session = request.getSession(true);
			if (user.isValid()) {
				/*
				 * store the retrieved data of the valid user in session
				 * attribute which is of type UserModel
				 */
				session.setAttribute("currentSessionUser", user);
				response.sendRedirect("index.jsp"); // logged-in page
			} else {
				session.setAttribute("logInError", true);
				response.sendRedirect("login.jsp");
				/*
				 * back to login page with an error message
				 */
			}

		} catch (Throwable theException) {
			System.out.println(theException);
		}
	}

}
