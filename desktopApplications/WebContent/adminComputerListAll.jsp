<jsp:include page="verifyAdminLogin.jsp"></jsp:include>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	
<%@ page import="edu.unlv.cs673.echoteam.PresentationComputerListAll" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="header.inc"></jsp:include>

<center>
	<form method="GET" action='ComputerController' name="listall">
	<table border="1">
		<tr>
			<th>&nbsp; &nbsp; &nbsp; Select Record &nbsp; &nbsp; &nbsp; </th>
			<th>&nbsp; &nbsp; &nbsp; userId &nbsp; &nbsp; &nbsp; </th>
			<th>&nbsp; &nbsp; &nbsp; computerIP &nbsp; &nbsp; &nbsp; </th>
			<th>&nbsp; &nbsp; &nbsp; networkId &nbsp; &nbsp; &nbsp; </th>
			<th>&nbsp; &nbsp; &nbsp; computerPort &nbsp; &nbsp; &nbsp; </th>
			<th>&nbsp; &nbsp; &nbsp; computerMAC &nbsp; &nbsp; &nbsp; </th>
		</tr>
		<%
			PresentationComputerListAll presentation = new PresentationComputerListAll(session);
			out.println(presentation.display());
		%>
	</table>
	<p>
		<input type="submit" name="add" value="Add System" /> &nbsp;
		<input type="submit" name="delete" value="Delete System" /> &nbsp; 
		<input type="submit" name="edit" value="Edit System" />  
	</p>
	</form>
</center>

<jsp:include page="footer.inc"></jsp:include>
