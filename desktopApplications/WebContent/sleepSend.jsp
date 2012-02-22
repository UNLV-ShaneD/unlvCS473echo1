<jsp:include page="verifyLogin.jsp"></jsp:include>
<%@page import="javax.servlet.jsp.tagext.TryCatchFinally"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="edu.unlv.cs673.echoteam.PresentationSendSleep" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="header.inc"></jsp:include>
<%
	PresentationSendSleep presentation = new PresentationSendSleep();
	presentation.run(request, out);
%>
<jsp:include page="footer.inc"></jsp:include>
