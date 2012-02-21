package edu.unlv.cs673.echoteam;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sun.java2d.SunGraphicsEnvironment.TTFilter;

import com.mysql.jdbc.Statement;

import edu.unlv.cs673.echoteam.helpers.ComputerHelper;

public class ComputerDAO extends DAO {
	// Use DAO for the connection, but do db selects here. Return RecordSet results as a 
	// collection of ComputerHelper objects

	public List<ComputerHelper> selectAllComputers() {
		List<ComputerHelper> results = new ArrayList<ComputerHelper>();
		String query = "SELECT computerId, userId, networkId, computerIP, computerPort, computerMAC FROM computers;";
		ResultSet rs = readQuery(query);
		results = buildResultList(rs);

		return results;
	}
	
	public String test() throws SQLException {
		String query = "";
		query = "SHOW TABLES";
		PreparedStatement p = null;
		p = con.prepareStatement(query);
		p.execute();
		
		ResultSet result = p.getResultSet();
		return "" + result.getString(0);
	}

	@SuppressWarnings({ })
	private List<ComputerHelper> buildResultList(ResultSet rs) {
		List<ComputerHelper> results = new ArrayList<ComputerHelper>();
		try {
			if (rs != null) {
				// Build results
				while (rs.next()) {
					int computerId;
					if(rs.getString(1)==null){
						computerId = -1;
					}else{
						computerId = Integer.parseInt(rs.getString(1));
					}
					
					int userId;
					if(rs.getString(2)==null){
						userId = -1;
					}else{
						userId = Integer.parseInt(rs.getString(2));
					}
					
					int networkId;
					if(rs.getString(3)==null){
						networkId = -1;
					}else{
						networkId = Integer.parseInt(rs.getString(3));
					}
					
					String computerIP;
					if(rs.getString(4)==null){
						computerIP = "-1";
					}else{
						computerIP = rs.getString(4);
					}
					
					int computerPort;
					if(rs.getString(5)==null){
						computerPort = -1;
					}else{
						computerPort = Integer.parseInt(rs.getString(5));
					}
					
					String computerMAC;
					if(rs.getString(6)==null){
						computerMAC = "-1";
					}else{
						computerMAC = rs.getString(6);
					}

					results.add(new ComputerHelper(computerId, userId, networkId, computerIP, computerPort, computerMAC));
				}
			}
		} catch (SQLException e) {
			System.err.println("Error in buildResultList");
			e.printStackTrace();
		}
		return results;
	}
	
	@SuppressWarnings({ "rawtypes" })
	public List selectAllComputersForUser(int userId) {
		List results = new ArrayList<ComputerHelper>();
		@SuppressWarnings("unused")
		String query = "SELECT computerId, userId, networkId, computerIP, computerPort, computerMAC FROM computers WHERE userId=?;";
		PreparedStatement p = null;
		ResultSet rs = null;
		try {
			p = con.prepareStatement(query);
			p.setString(1, "" + userId);
			rs = p.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		results = buildResultList(rs);

		return results;
	}
	
	public void insertComputer(String userId, String computerIP, String computerPort, String computerMAC) throws SQLException {
		@SuppressWarnings("unused")
		DAO myDao = new DAO();
		String query = "";
		query = "INSERT INTO computers (userId, computerIP, computerPort, computerMAC) Values (?, ?, ?, ?);";
		PreparedStatement p = null;
		p = con.prepareStatement(query);
		p.setString(1, userId);
		p.setString(2, computerIP);
		p.setString(3, computerPort);
		p.setString(4, computerMAC);
		System.out.println(p.toString());
		p.execute();
		query = "commit;";
		p = con.prepareStatement(query);
		p.execute();
	}
	
	public void deleteComputerById(int computerId) throws SQLException {
		@SuppressWarnings("unused")
		DAO myDao = new DAO();
		String query = "DELETE FROM computers WHERE computerId = ?";
		PreparedStatement p = con.prepareStatement(query);
		p.setInt(1, computerId);
		p.execute();
	}
	
	public void updateComputers(String select[], String IPs[], String Ports[], String MACs[]) {
		@SuppressWarnings("unused")
		DAO myDao = new DAO();
		String query = "UPDATE computers SET computerIP = ?, computerPort = ?, computerMAC = ? WHERE computerId = ?";
		
		PreparedStatement p;
		int id = 0;
		try {
			p = con.prepareStatement(query);
			if (select != null) {
				for (int i = 0; i < select.length; i++) {
					id = Integer.valueOf(select[i]);
					p.setString(1, IPs[i]);
					p.setString(2, Ports[i]);
					p.setString(3, MACs[i]);
					p.setInt(4, id);
					p.execute();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
