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
		$("#footer").corner("keep 15px");
		$("#mainMenu").corner("keep 15px");
		$(".grid1").corner("keep 15px");
		$(".grid2").corner("keep 15px");
		$(".grid3").corner("keep 15px");
		$(".grid4").corner("keep 15px");
		$(".grid5").corner("keep 15px");
		$(".grid6").corner("keep 15px");
		$(".link a").corner("keep 15px");
	});
</script>
</head>
<body>
<div id="wrapper">
	<div class="message"><strong>
<% if (session.getAttribute("changeMessage")!=null){%>
<%=session.getAttribute("changeMessage") %>
<%session.removeAttribute("changeMessage");} %>
</strong></div>
<div id="header">
<div class="link"><a href="index.jsp">go to website's home page</a></div>
<div class="link"><a href="viewstats.jsp">View Web site Usage</a><!-- #link --></div><br/>
<jsp:include page="WEB-INF/adminheader.jsp"></jsp:include>

<div class="logo">
	<h1>Control Panel</h1><br/>
<!--logo ends--></div>
<!-- #header--> </div>

<jsp:include page="ChangeControlServlet" />
<div class="grid1">
<h2>Restrictions</h2>
<%//get restricted ip's and store them in session
		ArrayList<String> acl = AccessHandler.XMLRreader();%>
<form action="ChangeControlServlet" method="post">
<fieldset>
<table>
	<tr>
		<th scope="col">Restricted IP Addresses</th>
	</tr>
	<%	for (int i = 0; i < acl.size() - 1; i++) {
			if (acl.get(i) != null) {%>
	<tr>
		<td><%=acl.get(i).trim()%></td>
	</tr>
	<%	}
			}%>
</table>
<ul>
	<li>
	<label for="ip">Enter IP:</label> 
	<input type="text" id="ip" name="ip" /> </li>
	<li>
		<input class="aradio" id="first" type="radio" name="action" value="add" checked>Add</input>
		<input class="aradio" type="radio" name="action" value="remove" >Remove</input>
		<input class="abutton" type="submit" id="submit1" value="Go" />
	</li>
</ul>
</fieldset>
</form>
<h2>Change Files Maximum Size</h2>
<p>Current Size Limit:<%=acl.get(acl.size() - 1).trim()%> Byte</p>
	<form action="ChangeControlServlet" method="post">
	<fieldset>
	<ul>
		
		<li><label for="maxsize">Enter new limit:</label><input type="text" id="maxsize" name="maxsize"/>B 
		<input type="submit"
			id="submit2" class="abutton" name="submit2" value="Change" /></li>
	</ul>
	</fieldset>
	</form>
<!-- .grid1--></div>
<div class="grid2">
<h2>Change Files Access</h2>
<jsp:include page="FileControlServlet" /> 
<%	if (session.getAttribute("allfiles") != null) {
	 		ArrayList<FileModel> fileList = (ArrayList<FileModel>) session.getAttribute("allfiles");%>
<form action="ChangeControlServlet" method="post">
<fieldset>
<ul>
	<li><label for="file">Select a file:</label> 
	<select name="file"	id="file">
		<% for (int i = 0; i < fileList.size(); i++) {	%>
		<option value="<%=fileList.get(i).getInode()%>"><%=fileList.get(i).getFname()%></option>
		<%	}	%>
	</select></li>
	<li><label for="fgroup">File Access Rights</label> 
	<select	name="fgroup" id="fgroup">
		<option value="777">Public</option>
		<option value="770">Group Access</option>
		<option value="700" selected="selected">Private</option>
	</select></li>
	<li><input class="abutton" type="submit" id="submit1" name="submit1"
		value="Change" /></li>
</ul>
</fieldset>
</form>
<%}	%>
<h2>Change Users Access Rights</h2>
<%		if (session.getAttribute("allusers") != null) {
		ArrayList<UserModel> userList = (ArrayList<UserModel>) session
					.getAttribute("allusers");	%>
<form action="ChangeControlServlet" method="post">
<fieldset>
<ul>
	<li><label for="user">Select a user:</label> 
	<select name="user"	id="user">
		<%	for (int i = 0; i < userList.size(); i++) {	%>
		<option value="<%=userList.get(i).getUid()%>"><%=userList.get(i).getEmail()%></option>
		<%	}%>
	</select></li>
	<li><label for="ugroup">User Group</label> <select name="ugroup"
		id="ugroup">
		<option value="1">Administrator</option>
		<option value="2">Power user</option>
		<option value="3" selected="selected">User</option>
	</select></li>
	<li><input type="submit" class="abutton" id="submit" name="submit" value="Change" /></li>
</ul>
</fieldset>
</form>
<!-- .grid2--></div><%	}	%>
<!-- #wrapper --></div>
</body>
</html>