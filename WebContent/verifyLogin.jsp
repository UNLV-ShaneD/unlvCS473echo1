
<%@ page import="edu.unlv.cs673.echoteam.InterfaceVerifyLogin" %>
<%
	InterfaceVerifyLogin services = new InterfaceVerifyLogin(session);
	services.verify(response);
%>