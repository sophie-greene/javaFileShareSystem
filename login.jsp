<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*" import="java.lang.*"
	import="fileShare.UserModel" import="fileShare.FileModel"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
 	<title>Login</title>
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
                 	<p>Please Enter you email and password </p>
					<p>if you don't have an account <a href="register.jsp">Register</a></p>
					<div class="login">
					<form method='post' action='./LoginControlServlet' >
						<fieldset>
							<ul>
								<li>
									<label for='name'>Email:</label>
									<input id='name' type='text' name='name'/>
								</li>
								<li>
									<label for='password'>Password:</label>
									<input id='password' type='password' name='password' />
								</li>
							</ul>
							<input type='submit' value='Login' class='submit_button' />
								<% if   (session.getAttribute("logInError")!=null){%>
						<br />
						<strong>Invalid Email/ Password Combination...Please try again</strong>
					<% session.removeAttribute("logInError");}%>
						</fieldset>
					</form>
					</div><!--.login ends-->
				</div><!-- #content-->
			</div><!-- #container-->
		</div><!-- #middle-->
	
		<div id="footer">
	    	<jsp:include page="WEB-INF/footer.jspf"></jsp:include>
	    </div><!-- #footer -->
	</div><!-- #wrapper -->
</body>
</html>



	