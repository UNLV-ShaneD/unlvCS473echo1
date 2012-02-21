package edu.unlv.cs673.echoteam;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.unlv.cs673.echoteam.helpers.DataComputer;

public class ComputerDAO extends DAO {
	// Use DAO for the connection, but do db selects here. Return RecordSet results as a 
	// collection of ComputerHelper objects

	public List<DataComputer> selectAllComputers() {
		List<DataComputer> results = new ArrayList<DataComputer>();
		String query = "SELECT computerId, userId, networkId, computerIP, computerPort, computerMAC FROM computers;";
		ResultSet rs = readQuery(query);
		results = buildResultList(rs);

		return results;
	}
	
	public String test() throws SQLException {
		String query = "";
		query = "SHOW TABLES";
		PreparedStatement p = null;
		p = connection.prepareStatement(query);
		p.execute();
		
		ResultSet result = p.getResultSet();
		return "" + result.getString(0);
	}

	@SuppressWarnings({ })
	private List<DataComputer> buildResultList(ResultSet rs) {
		List<DataComputer> results = new ArrayList<DataComputer>();
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

					results.add(new DataComputer(computerId, userId, networkId, computerIP, computerPort, computerMAC));
				}
			}
		} catch (SQLException e) {
			System.err.println("Error in buildResultList");
			e.printStackTrace();
		}
		return results;
	}
	
	public List<DataComputer> selectAllComputersForUser(int userId) {
		List<DataComputer> results = new ArrayList<DataComputer>();

		String query = "SELECT computerId, userId, networkId, computerIP, computerPort, computerMAC FROM computers WHERE userId=?;";
		PreparedStatement p = null;
		ResultSet rs = null;
		try {
			p = connection.prepareStatement(query);
			p.setString(1, "" + userId);
			rs = p.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		results = buildResultList(rs);

		return results;
	}
	
	public DataComputer getComputerByID(DataComputer computer) throws SQLException {
		List<DataComputer> results = new ArrayList<DataComputer>();

		String query = "SELECT computerId, userId, networkId, computerIP, computerPort, computerMAC FROM computers WHERE computerId=? AND userId=?;";
		ResultSet resultSet = null;
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			computer.prepareIDFindStatement(statement);
			resultSet = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		results = buildResultList(resultSet);
		
		if (results.size() == 0)
			return null;
		
		DataComputer resultComputer = (DataComputer) results.get(0);
		
		return resultComputer;
	}
	
	public void insertComputer(DataComputer computer) throws SQLException {
		@SuppressWarnings("unused")
		DAO myDao = new DAO();
		String query = "";
		query = "INSERT INTO computers (userId, computerIP, computerPort, computerMAC) Values (?, ?, ?, ?);";
		PreparedStatement statement = null;
		statement = connection.prepareStatement(query);
		computer.prepareInsertStatement(statement);
		System.out.println(statement.toString());
		statement.execute();
		query = "commit;";
		statement = connection.prepareStatement(query);
		statement.execute();
	}
	
	public void deleteComputerById(int computerId) throws SQLException {
		@SuppressWarnings("unused")
		DAO myDao = new DAO();
		String query = "DELETE FROM computers WHERE computerId = ?";
		PreparedStatement p = connection.prepareStatement(query);
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
			p = connection.prepareStatement(query);
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
