package edu.unlv.cs673.echoteam;

import java.util.List;

import javax.servlet.http.HttpSession;

import edu.unlv.cs673.echoteam.helpers.ComputerHelper;

public class ScreenComputerListAll {
	HttpSession session;
	
	public ScreenComputerListAll(HttpSession session) {
		this.session = session;
	}
	
	public String display() {
		if (session == null)
		{
			return "Error: Session is null.";
		}
		
		String out = "";
		
		int currUserId = Integer.getInteger("" + session.getAttribute("userId"),-1);
 		ComputerDAO computerDao = new ComputerDAO();
 		List<ComputerHelper> results = computerDao.selectAllComputersForUser(currUserId);
		
 		for(ComputerHelper ch: results){
 			if (ch == null)
 				break;
			
 			String computerId = "" + ch.getComputerId();
 			String computerIP = "" + ch.getComputerIP();
 			String networkId = "" + ch.getNetworkId();
 			String computerPort = "" + ch.getComputerPort();
 			String computerMAC = ch.getComputerMAC();
 			if(computerMAC==null){
 				computerMAC="";
 			}
			
 			out.concat("<tr>");
 				// Check box for Primary Key (ComputerId)
 				out.concat("<td><input type=\"checkbox\" name=\"" + computerId + "\" />");
 				out.concat("</td>");
 				out.concat("<td><a href=\"computerEdit.jsp?computerId=" + computerId + "\">Edit Computer " + computerId + "</a>");
 				out.concat("</td><td>");
 				out.concat(computerIP);
 				out.concat("</td><td>");
 				out.concat(networkId);
 				out.concat("</td><td>");
 				out.concat(computerPort);
 				out.concat("</td><td>");
 				out.concat(computerMAC);
 				out.concat("</td><td>");
 				out.concat("<a href=\"sleepSend.jsp?host=" + computerIP + "&port=" + computerPort + "\">Send Sleep Command</a>");
 				out.concat("</td><td>");
 				out.concat("<a href=\"magicPacketSend.jsp?host=" + computerIP + "&macAddress=" + computerMAC + "\">Send WoL Packet</a>");
 				out.concat("</td>");
 			out.concat("</tr>");
 		}
 		
 		return out;
	}
}
