<div class="loginform"><!--get user info!--> 
	<!--if user logged in display details-->
	
	<% if (session.getAttribute("currentSessionUser") != null) { %>
	<!--define an object currentUser of type UserModel-->
	<jsp:useBean id="currentUser" class="fileShare.UserModel">
	</jsp:useBean> 
	<% currentUser = (fileShare.UserModel) session.getAttribute("currentSessionUser");%>
		<div class="logout">
			
				<strong >Welcome <%= currentUser.getFirstname()%></strong>
				<a href="LoginControlServlet">logout</a>
			
		</div>
		
	<% } else { %> 
	<!--if user is not logged go to login page--> 
	<a href="login.jsp">Login</a> 
	<%} %>
	</div><!--login div ends!-->



