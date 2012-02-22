
<%@ page import="edu.unlv.cs673.echoteam.PresentationVerifyLogin" %>
<%
	// Reset session data
	session.setAttribute("userId", -1);

	PresentationVerifyLogin services = new PresentationVerifyLogin(session);
	if (!services.verify(request)) {
		session.setAttribute("failLogin", "true");
		pageContext.forward("login.jsp");
		return;
	}
	pageContext.forward("computerListAll.jsp");
%>
