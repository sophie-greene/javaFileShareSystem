<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*" import="java.lang.*"
	import="fileShare.UserModel" import="fileShare.FileModel"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%-- Instantiate the form validation class and supply the error messages  --%>
<%@ page import="fileShare.*" %>
<jsp:useBean id="form" class="fileShare.RegisterFormValidationModel" scope="request"></jsp:useBean>
<jsp:useBean id="strEmail" class="java.lang.String" scope="request"></jsp:useBean>
<jsp:useBean id="strFirstname" class="java.lang.String" scope="request"></jsp:useBean>
<jsp:useBean id="strLastname" class="java.lang.String" scope="request"></jsp:useBean>
<%
form = new fileShare.RegisterFormValidationModel();

    // If process is true, attempt to validate and process the form
    if ("true".equals(request.getParameter("process"))) {
    	if (request.getParameter("email")!=null){
    		form.setEmail(request.getParameter("email"));
    	}
    	if (request.getParameter("password")!=null){
    		form.setPassword(request.getParameter("password"));
    	}
    	if (request.getParameter("cpassword")!=null){
    		form.setCpassword(request.getParameter("cpassword"));
    	}
    	if (request.getParameter("firstname")!=null){
    		form.setFirstname(request.getParameter("firstname"));
    	}
    	if (request.getParameter("lastname")!=null){
    		form.setLastname(request.getParameter("lastname"));
    	}
         // Attempt to process the form
        if (form.process()) {
           //store data in a user object and send it in a session's attributes
            UserModel user=new UserModel();
            user.setEmail(request.getParameter("email"));
            user.setPassword(request.getParameter("password"));
            user.setFirstname(request.getParameter("firstname"));
            user.setLastname(request.getParameter("lastname"));
            session.setAttribute("registering-user",user);
            //go to servlet to store data
            response.sendRedirect("RegisterControlServlet");
        }else{
        	strEmail=request.getParameter("email");
        	strFirstname=request.getParameter("firstname");
        	strLastname=request.getParameter("lastname");
        }
    }
%>

	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
 	<title>Register</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="css/layout.css" type="text/css" />
    <link rel="stylesheet" href="css/default.css" type="text/css" />
    <link rel="stylesheet" href="css/main.css" type="text/css" />
   	<script type="text/javascript" src="scripts/jquery.js"></script>
	<script type="text/javascript" src="scripts/jquery.corner.js"></script>
	<script type="text/javascript">
	$(function(){
		
		$("#content").corner("keep 15px");
		$("#footer").corner("keep 15px");
		$("#mainMenu").corner("keep 15px");
		$("fieldset").corner("keep 15px");
		$("textarea").corner("keep 15px");
	});
	</script>
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<jsp:include page="WEB-INF/header.jsp"></jsp:include>
		</div><!-- #header-->
	    <div id="mainMenu">
	        <jsp:include page="WEB-INF/navigation.jsp"></jsp:include>
	    </div><!--mainMenu div ends!-->
	    <div id="middle">
			<div id="container">
				<div id="content">
                 	<p>please enter your details</p>
                 	<div class="register">
                 	<% if   (session.getAttribute("registred")!=null){%>
						<br />
						<strong>registration failed, try again later</strong>
					<% session.removeAttribute("registred");}%>
					<% if   (session.getAttribute("exists")!=null){%>
						<br />
						<strong>This email is already registered</strong>
					<% session.removeAttribute("exists");}%>
					<form action='<%= request.getRequestURI() %>' method="post">
						<fieldset>
							<ul>
								<li>
									<label for='email'>Email:</label>
									<input id='email' type='text' name='email' value="<%= strEmail %>" />
									<strong><%= form.getErrorMessage("email")%></strong>
								</li>
								<li>
									<label for='password'>Password:</label>
									<input id='password' type='password' name='password'/>
									<strong><%= form.getErrorMessage("password") %></strong>
								</li>
								<li>
									<label for='cpassword'>Confirm Password:</label>
									<input id='cpassword' type='password' name='cpassword' />
									<strong><%= form.getErrorMessage("cpassword") %></strong>
								</li>
								<li>
									<label for='firstname'>First Name:</label>
									<input id='firstname' type='text' name='firstname' value="<%= strFirstname %>"/>
									<strong><%= form.getErrorMessage("firstname") %></strong>
								</li>
								<li>
									<label for='lastname'>Last Name:</label>
									<input id='lastname' type='text' name='lastname' value="<%= strLastname %>"/>
									<strong><%= form.getErrorMessage("lastname") %></strong>
								</li>
							</ul>
							<input type='submit' value='Register' class='submit_button' />
							 <input type="hidden" name="process" value="true" />
						</fieldset>
					</form>
					</div>
				</div><!-- #content-->
			</div><!-- #container-->
		</div><!-- #middle-->
	
		<div id="footer">
		<jsp:include page="WEB-INF/footer.jspf"></jsp:include>
	    </div><!-- #footer -->
	</div><!-- #wrapper -->
</body>
</html>


	