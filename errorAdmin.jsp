<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*" import="java.lang.*"
	import="fileShare.UserModel"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Error</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="css/layout.css" type="text/css" />
<link rel="stylesheet" href="css/default.css" type="text/css" />
<link rel="stylesheet" href="css/main.css" type="text/css" />
<script type="text/javascript" src="scripts/jquery.js"></script>
<script type="text/javascript" src="scripts/jquery.corner.js"></script>
<script type="text/javascript">
	$( function() {

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
	</div>
	<!--mainMenu div ends!-->
	<div id="middle">
	<div id="container">
	<div id="content">
		<h1>Only Administrators can access this page</h1>
		<h2>you need to login as admin to access it</h2>
		<a class="button" href="login.jsp" > Click for Login Page </a>
	</div>
	<!-- #content--></div>
	<!-- #container--></div>
	<!-- #middle-->
	
	<div id="footer">
		<jsp:include page="WEB-INF/footer.jspf"></jsp:include>
	</div>
	<!-- #footer --></div>
	<!-- #wrapper -->
</body>
</html>
