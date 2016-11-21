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
		
		$("input").corner("keep 15px");
	});
	</script>
</head>
<body>
	<div id="wrapper">
	    <div id="header">
	         <div class="logo">
	            <h1>File Share Web site</h1>
	            <h2>The Easy Way to Share Your Documents</h2>
	        </div><!--logo div ends!-->
	    </div><!-- #header-->
	   
	   <% if   (session.getAttribute("registred")!=null){%>
						<br />
						<strong> You have been registered successfully</strong>
					<% session.removeAttribute("registred");}%>
	   <a href="login.jsp" class="button">Continue to login Page </a>
	</div><!-- #wrapper -->
</body>
</html>

