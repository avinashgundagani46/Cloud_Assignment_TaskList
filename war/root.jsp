
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Home</title>
</head>
<body>

	<%
	UserService userService = UserServiceFactory.getUserService();
	String url= "";
	String urlLinktext = "";
	User user = null;
	if(!userService.isUserLoggedIn()){
		url= userService.createLoginURL("\\");
		urlLinktext = "Login";
	}else{
		user = userService.getCurrentUser();
		url = userService.createLogoutURL("\\");
	    urlLinktext = "Logout";
	}
	    
	%>
	<div >
		<div style="margin-left: 10%; margin-top: 5%;"><a href = "<%= url %>"><%= urlLinktext %></a></div>
		<div style="margin-top: 10%; margin-left: 40%;">
				
		</div>
	</div>
</body>
</html>