<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*" import="java.lang.*"
	import="fileShare.UserModel" import="fileShare.FileModel"
	import= "java.net.*" import="java.io.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>File Share</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="css/layout.css" type="text/css" />
<link rel="stylesheet" href="css/default.css" type="text/css" />
<link rel="stylesheet" href="css/main.css" type="text/css" />
<link rel="stylesheet" href="css/thickbox.css" type="text/css" />
<script type="text/javascript" src="scripts/jquery.js"></script>
<script type="text/javascript" src="scripts/jquery.corner.js"></script>
<script type="text/javascript" src="scripts/thickbox.js"></script>
<script type="text/javascript">
	$( function() {

		$("#content").corner("keep 15px");
		$("#footer").corner("keep 15px");
		$("#mainMenu").corner("keep 15px");
		$("fieldset").corner("keep 15px");
	});
</script>
<script type="text/javascript">
	jQuery(document).ready( function() {

		jQuery(".panel").hide();

		//toggle the componenet with class msg_body

			jQuery(".head").click( function() {
				

					jQuery(this).next(".panel").slideToggle(0);
					

				});

		});
</script>
</head>
<body>
<div id="wrapper">
<div id="header"><jsp:include page="/WEB-INF/header.jsp"></jsp:include></div>
<!-- #header-->
<div id="mainMenu"><jsp:include page="/WEB-INF/navigation.jsp"></jsp:include>
</div>
<!--mainMenu div ends!-->
<div id="middle">
<div id="container">

<div id="content">
<%if (session.getAttribute("Message") != null){ %>
<h2><%= session.getAttribute("Message")%></h2>
<% session.removeAttribute("Message");}else{ %>
<h2>Files available to you are: </h2>
<jsp:include page="FileControlServlet" />
<%} %>
<ul>
	<%
		if (session.getAttribute("allfiles") != null) {

			ArrayList<FileModel> fileList = (ArrayList<FileModel>) session
					.getAttribute("allfiles");
			for (int i = 0; i < fileList.size(); i++) {
	%>

	<div class="head"><li ><%=fileList.get(i).getFname()%></li></div>

	<div class="panel">
	<ul>
		<li><a
			href="<%=fileList.get(i).getFpath()%><%=fileList.get(i).getFname()%>">Download
		File</a></li>
		<form action="FileControlServlet" method="delete">
		<input type="hidden" name ="d" id="d" value="<%=fileList.get(i).getInode()%>" />
		<input type="submit" name="submit" value="Delete" />
		</form>
		
		<li>size:<%=fileList.get(i).getFsize()/1024%>KB</li> 
		<li>upload Time: <%=fileList.get(i).getTupload() %></li>
		<li>	Access:<% if (fileList.get(i).getAccess()==700){ %>
					Private<%}else if(fileList.get(i).getAccess()==770){%>
					Group<%}else {%>
					Public
				<%}%></li>
				</ul>
	</div>


	<%
		}
		} else 	out.println("No files to display");
			session.removeAttribute("allfiles");
		
	%>
</ul>
<!-- #content--></div>
<!-- #container--></div>
<!-- #middle--></div>
<div id="footer"><jsp:include page="/WEB-INF/footer.jspf"></jsp:include></div>
<!-- #footer -->
</div><!-- #wrapper -->
</body>
</html>
<%! 
public void deleteurl(String strUrl)throws IOException, ServletException{
	try{
	URL url = new URL(strUrl);
	HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
	httpCon.setDoOutput(true);
	httpCon.setRequestProperty(
	    "Content-Type", "application/x-www-form-urlencoded" );
	httpCon.setRequestMethod("DELETE");
	httpCon.connect();
	}catch (IOException e) {}
}
%>
