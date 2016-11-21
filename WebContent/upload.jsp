<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*" import="java.lang.*"
	import="fileShare.UserModel" import="fileShare.FileModel"%>
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
</head>
<body>
<div id="wrapper">
<div id="header"> <jsp:include page="/WEB-INF/header.jsp"></jsp:include></div>
<!-- #header-->
<div id="mainMenu"><jsp:include page="/WEB-INF/navigation.jsp"></jsp:include></div>
<!--mainMenu div ends!-->
<div id="middle">
<div id="container">
<div id="content">

<div class="upload">
	<form method="post" enctype="multipart/form-data" action="FileControlServlet" >
		<fieldset>
			<ul>
			<li><% if (session.getAttribute("UploadMessage")!=null){ 
					%>
						<strong><%=session.getAttribute("UploadMessage")%></strong>
					<% //reset the session message
					session.removeAttribute("UploadMessage");
					}  %></li>
				<li><label for='file'>Select a file:</label> 
					<input id="file" type="file" name="file" value=""/>
					
				</li>
				<li><label for='description'>Description:</label> 
					<textarea name="description" ></textarea>
				</li>
				<li><label>Tags:</label> 
					<input type="text" name="tag1"  id="tag1"/>
					<input type="text" name="tag2"  id="tag2" />
					<input type="text" name="tag3"  id="tag3"/>
					<input type="text" name="tag4"  id="tag4"/>
				</li>
				<li>
				<label for="access">File Access Rights</label>
				  <select name="access" id="access">
				    <option value="777">public full access</option>
				    <option value="770">group access</option>
				    <option value="700" selected="selected">private(only me)</option>
				  </select>
				</li>
				<li>
				 	<input type="hidden" name="process" value="true" />
					<input type='submit' value='Upload' class='submit_button' />
					
				</li>
			</ul>
			
		</fieldset>
	</form>
</div>
<!-- #content--></div>
<!-- #container--></div>
<!-- #middle--></div>
<div id="footer"><jsp:include page="/WEB-INF/footer.jspf"></jsp:include></div>
<!-- #wrapper --></div>
</body>
</html>