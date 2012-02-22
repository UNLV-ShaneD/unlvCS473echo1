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
	<form method="POST" action="computerAdded.jsp">
	  <p align="center">&nbsp;</p>
	  <div align="center">
	    <center>
	    <table border="0">
	      <tr>
	        <td width="50%" align="center">
	          <p align="center">Field</p></td>
	        <td width="50%" align="center">
	          <p align="center">Value</p></td>
	      </tr>
	      <tr>
	        <td width="50%" align="center">
	          <p align="center">IP Address/Host</p></td>
	        <td width="50%" align="center">
	          <p align="center"><input type="text" name="computerIP" size="20" /></p></td>
	      </tr>
	      <tr>
	        <td width="50%" align="center">
	          <p align="center">Sleep Port</p></td>
	        <td width="50%" align="center">
	          <p align="center"><input type="text" name="computerPort" size="20" value="2004"/></p></td>
	      </tr>
	      <tr>
	        <td width="50%" align="center">MAC Address</td>
	        <td width="50%" align="center"><input type="text" name="computerMAC" size="20"/></td>
	      </tr>
	    </table>
	    </center>
	  </div>
	  <p align="center"><input type="submit" value="Submit" name="B1"></p>
	</form>
<br><br>
<a href="computerListAll.jsp">Return to Computer List</a>
</center>

<jsp:include page="footer.inc"></jsp:include>
