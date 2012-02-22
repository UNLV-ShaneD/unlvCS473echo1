<jsp:include page="verifyLogin.jsp"></jsp:include>
<%@page import="edu.unlv.cs673.echoteam.PresentationComputerListAll"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="edu.unlv.cs673.echoteam.ComputerDAO,edu.unlv.cs673.echoteam.helpers.DataComputer,java.util.List,java.util.ArrayList,java.io.PrintWriter,java.sql.ResultSet,java.sql.SQLException" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="header.inc"></jsp:include>


<center>
	<form method="GET" action='ComputerController' name="listall">
	<table border="1">
		<tr>
			<th>&nbsp; &nbsp; &nbsp; Select &nbsp; &nbsp; &nbsp; </th>
			<th>&nbsp; &nbsp; &nbsp; Edit &nbsp; &nbsp; &nbsp; </th>
			<th>&nbsp; &nbsp; &nbsp; Host &nbsp; &nbsp; &nbsp; </th>
<!-- 			<th>&nbsp; &nbsp; &nbsp; networkId &nbsp; &nbsp; &nbsp; </th> -->
			<th>&nbsp; &nbsp; &nbsp; Port &nbsp; &nbsp; &nbsp; </th>
			<th>&nbsp; &nbsp; &nbsp; MAC Address &nbsp; &nbsp; &nbsp; </th>
			<th>&nbsp; &nbsp; &nbsp; Send Sleep &nbsp; &nbsp; &nbsp; </th>
			<th>&nbsp; &nbsp; &nbsp; Send WakeOnLAN &nbsp; &nbsp; &nbsp; </th>
		</tr>
	<%
		PresentationComputerListAll screen = new PresentationComputerListAll(session);
		String output = screen.display();
		out.println(output);
	%>
	</table>
	<p>
		<input type="submit" name="add" value="Add System" /> &nbsp;
		<input type="submit" name="delete" value="Delete System" /> &nbsp; 
	</p>
	</form>
</center>

<jsp:include page="footer.inc"></jsp:include>
