<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*" import="java.lang.*"
	import="fileShare.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>File Share</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="css/layout.css" type="text/css" />
<link rel="stylesheet" href="css/default.css" type="text/css" />
<link rel="stylesheet" href="css/main.css" type="text/css" />
<script type="text/javascript" src="scripts/jquery.js"></script>
<script type="text/javascript" src="scripts/jquery.corner.js"></script>
<script type="text/javascript">
	$( function() {
		$("#content").corner("keep 15px");
		$(".link").corner("keep 15px");
	});
</script>
</head>
<body>
<div id="wrapper">
<div id="header">
<div class="link"><a href="index.jsp">go to website's home
page</a></div>
<jsp:include page="WEB-INF/adminheader.jsp"></jsp:include>

<div class="logo">
<h1>View Website's Statistics</h1>


<!--logo ends--></div>
<!-- #header--></div>


<div id="content"><jsp:include page="StatsControlServlet" />
<h2>Host Statistics</h2>
<table>
	<tr>
		<th>Host</th>
		<th>Clicks</th>
	</tr>
	<%
		Stat temp = new Stat();
			if (session.getAttribute("host") != null) {

		ArrayList<Stat> host = (ArrayList<Stat>) session
				.getAttribute("host");
		
		for (int j = 0; j < host.size(); j++) {
			temp=host.get(j);
	%>
	<tr>
		<td><%=temp.getHostName()%></td>
		<td><%=temp.getOccur()%></td>
	</tr>
	<%
		}
		}
		session.removeAttribute("host");
	%>
</table>
<br />
<h2>URI Statistics</h2>
<table>
	<tr>
		<th>uri</th>
		<th>Clicks</th>
	</tr>
	<%
		
			if (session.getAttribute("uri") != null) {

		ArrayList<Stat> host = (ArrayList<Stat>) session
				.getAttribute("uri");
		
		for (int j = 0; j < host.size(); j++) {
			temp=host.get(j);
	%>
	<tr>
		<td><%=temp.getHostName()%></td>
		<td><%=temp.getOccur()%></td>
	</tr>
	<%
		}
		}
		session.removeAttribute("uri");
	%>
</table>
<br />
<h2>Referrer Statistics</h2>
<table>
	<tr>
		<th>Referrer</th>
		<th>Clicks</th>
	</tr>
	<%
		
			if (session.getAttribute("referer") != null) {

		ArrayList<Stat> host = (ArrayList<Stat>) session
				.getAttribute("referer");
		
		for (int j = 0; j < host.size(); j++) {
			temp=host.get(j);
	%>
	<tr>
		<td><%=temp.getHostName()%></td>
		<td><%=temp.getOccur()%></td>
	</tr>
	<%
		}
		}
		session.removeAttribute("referer");
	%>
</table>

<!-- #content--></div>
<div class="link"><a href="controlPanel.jsp">Back to Control
Panel</a></div>
<!-- #wrapper --></div>
</body>
</html>