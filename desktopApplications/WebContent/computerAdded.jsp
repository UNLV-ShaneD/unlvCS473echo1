<jsp:include page="verifyLogin.jsp"></jsp:include>
<%@page import="edu.unlv.cs673.echoteam.PresentationComputerAdd"%>
<%@page import="org.eclipse.jdt.internal.compiler.ast.TryStatement"%>
<%@ page import="edu.unlv.cs673.echoteam.ComputerDAO,
					java.io.PrintWriter,java.sql.*,
					javax.persistence.*" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="header.inc"></jsp:include>

<center>
<%
	PresentationComputerAdd presentation = new PresentationComputerAdd(session, request);
	out.println(presentation.run());
%>
<br><br>
<a href="computerListAll.jsp">Return to Computer List</a>
</center>

<jsp:include page="footer.inc"></jsp:include>
