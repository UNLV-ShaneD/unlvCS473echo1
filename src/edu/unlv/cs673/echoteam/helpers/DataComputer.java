package edu.unlv.cs673.echoteam.helpers;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import java.sql.Connection;

public class DataComputer {
	private int computerId = -1;
	private int userId = -1;
	private int networkId = -1;	
	private String computerIP = "";
	private int computerPort = 9;
	private String computerMAC = "";

	public DataComputer(int computerId, int userId, int networkId, String computerIP, int computerPort, String computerMAC) {
		this.computerId = computerId;
		this.userId = userId;
		this.networkId = networkId;
		this.computerIP = computerIP;
		this.computerPort = computerPort;
		this.computerMAC = computerMAC;
	}
	
	// Prepare to fill data from SQL query
	public DataComputer(int computerId, int userId) {
		this.computerId = computerId;
		this.userId = userId;
	}
	
	// Fill data from form input
	public DataComputer(int userId, HttpServletRequest request) {
		computerId = -1;
		this.userId = userId;
		networkId = -1;
		
		try {
			String port = request.getParameter("computerPort");
			computerPort = Integer.parseInt(port);
		} catch (NumberFormatException exception) {
			computerPort = 9;
		}
		
		computerIP = request.getParameter("computerIP");
		computerMAC = request.getParameter("computerMAC");
	
		// Check for nulls and replace with empty strings
		if(computerIP==null){
			computerIP="";
		}
		if(computerMAC==null){
			computerMAC="";
		}
	}
	
	// Generates HTML for computerListAll to allow editing of and sending commands to computers
	public String printListRow() {
		if(computerMAC==null){
			computerMAC="";
		}
		
		String out = "";
	
		out += "<tr>";
			// Check box for Primary Key (ComputerId)
			out += "<td><input type=\"checkbox\" name=\"" + computerId + "\" />";
			out += "</td>";
			out += "<td><a href=\"computerEdit.jsp?computerId=" + computerId + "\">Edit Computer " + computerId + "</a>";
			out += "</td><td>";
			out += computerIP;
			out += "</td><td>";
			out += "" + networkId;
			out += "</td><td>";
			out += "" + computerPort;
			out += "</td><td>";
			out += computerMAC;
			out += "</td><td>";
			out += "<a href=\"sleepSend.jsp?host=" + computerIP + "&port=" + computerPort + "\">Send Sleep Command</a>";
			out += "</td><td>";
			out += "<a href=\"magicPacketSend.jsp?host=" + computerIP + "&macAddress=" + computerMAC + "\">Send WoL Packet</a>";
			out += "</td>";
		out += "</tr>";
		
		return out;
	}

	// Generates HTML for administrative oversight of computers
	public String printListRowAdmin() {
		String out = "";
		
		out += "<tr>";
		// Check box for Primary Key (ComputerId)
		out += "<td><input type=\"checkbox\" name=\"" + computerId + "\" />";
		out += "</td><td>";
		out += userId;
		out += "</td><td>";
		out += computerIP;
		out += "</td><td>";
		out += networkId;
		out += "</td><td>";
		out += computerPort;
		out += "</td><td>";
		out += computerMAC;
		out += "</td>";
		out += "</tr>";
		
		return out;
	}

	public void prepareInsertStatement(PreparedStatement statement) throws SQLException {
		statement.setInt(1, userId);
		statement.setString(2, computerIP);
		statement.setInt(3, computerPort);
		statement.setString(4, computerMAC);
	}

	public String printEditHTML() {
		String out = "";
		out += "<tr>";
		out += "<td><input type=\"text\" name=\"computerIP\" value=\"" + computerIP + "\" /></td>";
		out += "<td><input type=\"text\" name=\"computerPort\" value=\"" + computerPort + "\" /></td>";
		out += "<td><input type=\"text\" name=\"computerMAC\" value=\"" + computerMAC + "\" /></td>";
		out += "</tr>";
		out += "<input type=\"hidden\" name=\"computerId\" value=\"" + computerId + "\" />";
		return out;
	}

	public void prepareIDFindStatement(PreparedStatement statement) throws SQLException {
		statement.setInt(1, computerId);
		statement.setInt(2, userId);
	}
}
