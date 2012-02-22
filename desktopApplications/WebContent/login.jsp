<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="header.inc"></jsp:include>

<body bgcolor="white" onload="document.loginForm.username.focus()">
	<center>
		<form name="loginForm" method="post" action="loginDo.jsp">
			<table>
				<tr>
					<td>Username:</td>
					<td><input type="text" name="username" align="right" />
					</td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password" align="right" />
					</td>
				</tr>
				<tr>
					<td></td>
					<td><center>
							<input type="submit" name="submit" value="Log In" />
						</center>
					</td>

				</tr>
			</table>
		</form>
		<font color=red> <% if (session.getAttribute("failLogin") == "true"){
			
			out.println("Invalid Username/Password. Please try again");
			}%> </font>
	</center>
</body>

<jsp:include page="footer.inc"></jsp:include>
