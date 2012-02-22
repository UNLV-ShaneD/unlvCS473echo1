<%@page import="edu.unlv.cs673.echoteam.PresentationComputerAdd"%>
<jsp:include page="verifyLogin.jsp"></jsp:include>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="edu.unlv.cs673.echoteam.PresentationComputerEdit"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<jsp:include page="header.inc"></jsp:include>
	
	<center>
		<form method="GET" action="computerChanged.jsp" name="listall">
			<table border="1">
				<tr>
					<th>&nbsp; &nbsp; &nbsp; computerIP &nbsp; &nbsp; &nbsp;</th>
					<th>&nbsp; &nbsp; &nbsp; computerPort &nbsp; &nbsp; &nbsp;</th>
					<th>&nbsp; &nbsp; &nbsp; computerMAC &nbsp; &nbsp; &nbsp;</th>
				</tr>
				<%
					PresentationComputerEdit presentation = new PresentationComputerEdit(session);
					out.println(presentation.display(request));
				%>
			</table>
			<input type="submit" name="save" value="Save Changes" />
		</form>
		<a href="computerListAll.jsp">Return</a>
	</center>
	<jsp:include page="footer.inc"></jsp:include>
</body>
</html>
