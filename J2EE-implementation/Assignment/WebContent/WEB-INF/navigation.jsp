	<ul>
		
		<% if (session.getAttribute("currentSessionUser") != null) { %>
		<!--define an object currentUser of type UserModel-->
		<jsp:useBean id="currentUser" class="fileShare.UserModel">
		</jsp:useBean> 
	<% currentUser = (fileShare.UserModel) session.getAttribute("currentSessionUser");
		if(currentUser.getGroup().equals("admin")){%>
		<li><a href="controlPanel.jsp">Control Panel </a></li>
	<%} }%>
	<li><a href="index.jsp">Home</a></li>
		<li><a href="files.jsp">Access Your Files</a></li>
		<li><a href="upload.jsp">Upload a file </a></li>
	</ul>