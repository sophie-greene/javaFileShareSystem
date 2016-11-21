package fileShare;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RegisterControlServlet
 */
public class RegisterControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterControlServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(true);
			UserModel user = (UserModel) session
					.getAttribute("registering-user");

			user.setGroup("user"); // when registering set automatically, only
									// admin can change

			boolean result = UserDAO.register(user);

			if (result) {
				// if registration successful create a directory to hold user's
				// files
				new File("c:\\uploads\\" + user.getEmail()).mkdir();
				session.setAttribute("registred", true);
				response.sendRedirect("registrationSuccess.jsp"); // registration
																	// successful

			} else {
				session.setAttribute("registred", false);
				if (user.doesExist()) {
					session.setAttribute("exists", true);
				}
				response.sendRedirect("register.jsp"); // back to registration
														// with an error msg

			}
		} catch (Throwable theException) {
			System.out.println(theException);
		}
	}

}
