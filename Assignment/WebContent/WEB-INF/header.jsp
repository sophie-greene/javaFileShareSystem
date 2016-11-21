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
	<br />
<br />
<div class="searchform">
	<form action="SearchControlServlet" method="post">
		<fieldset>
			<ul>
				<li>
					<label for="search">Search</label> 
					<input id="search" name="search" type="text" /> 
					<input id="submit" 	value="search" type="submit" class="submit_button" />
				</li>
			</ul>
		</fieldset>
	</form>
</div>

<br />
<div class="logo">
	<h1>File Share Website</h1>
	<h2>The Easy Way to Share Your Documents</h2>
</div><!--logo ends-->
<br />