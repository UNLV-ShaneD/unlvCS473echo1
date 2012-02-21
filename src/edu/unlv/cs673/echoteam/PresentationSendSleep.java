package edu.unlv.cs673.echoteam;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;

import com.jamesoravec.samplecode.examples.hibernate.Requestor;

public class PresentationSendSleep {
	public void run(HttpServletRequest request, JspWriter out) throws IOException {
		String host = request.getParameter("host");
		int port = Integer.parseInt(request.getParameter("port"));
		Requestor sleepRequestor = new Requestor();
		
		try{
			sleepRequestor.run(host, port);
			out.println("<center><b>Sleep Request Sent Successfully</b><BR> <a href=\"computerListAll.jsp\">Return</a></center>");
		}catch(Exception e){
			e.printStackTrace();
			out.println("<center><b>Error occured when trying to send Sleep Request</b><BR> <a href=\"computerListAll.jsp\">Return</a></center>");
		}
	}
}
