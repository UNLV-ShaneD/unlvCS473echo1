
<%@ page import="edu.unlv.cs673.echoteam.PresentationVerifyLogin" %>
<%
	PresentationVerifyLogin services = new PresentationVerifyLogin(session);
	if (!services.verify(request)) {
		pageContext.forward("index.jsp");
		return;
	}
	pageContext.forward("computerListAll.jsp");
%>
