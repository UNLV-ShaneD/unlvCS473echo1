package edu.unlv.cs673.echoteam;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.unlv.cs673.echoteam.helpers.UserHelper;

public class UserDAO extends DAO {
	// Use DAO for the connection, but do db selects here. Return RecordSet results as a 
	// collection of ComputerHelper objects
	

	public List<UserHelper> selectAllUsers() {
		List<UserHelper> results = new ArrayList<UserHelper>();
		String query = "SELECT userId, userName, userPassword, userEmail FROM users;";
		ResultSet rs = readQuery(query);
		results = buildResultList(rs);

		return results;
	}

	private List<UserHelper> buildResultList(ResultSet rs) {
		List<UserHelper> results = new ArrayList<UserHelper>();
		try {
			if (rs != null) {
				// Build results
				while (rs.next()) {
					int userId = Integer.parseInt(rs.getString(1));
					String userName = rs.getString(2);
					String userPassword = rs.getString(3);
					String userEmail = rs.getString(4);

					results.add(new UserHelper(userId, userName, userPassword, userEmail));
				}
			}
		} catch (SQLException e) {
			System.err.println("Error in buildResultList");
			e.printStackTrace();
		}
		return results;
	}
	
	public void insertUser(String userName, String userPassword, String userEmail) throws SQLException {
		String query = "";
		query = "INSERT INTO users (userName, userPassword, userEmail) Values (?, ?, ?);";
		PreparedStatement p = null;
		p = connection.prepareStatement(query);
		p.setString(1, userName);
		p.setString(2, userPassword);
		p.setString(3, userEmail);
		p.execute();
		query = "commit;";
		p = connection.prepareStatement(query);
		p.execute();
	}
	
	public void deleteUserById(int userId) throws SQLException {
		String query = "DELETE FROM users WHERE userId = ?";
		PreparedStatement p = connection.prepareStatement(query);
		p.setInt(1, userId);
		p.execute();
	}
	
	public void updateComptuerById(String select[], String usernames[], String passwords[], String emails[]) {
		String query = "UPDATE users set userName = ?, userPassword = ?, userEmail = ? WHERE userID = ?";
		PreparedStatement p;
		int id = 0;
		try {
			p = connection.prepareStatement(query);
			if (usernames != null) {
				for (int i = 0; i < usernames.length; i++) {
					id = Integer.valueOf(select[i]);
					p.setString(1, usernames[i]);
					p.setString(2, passwords[i]);
					p.setString(3, emails[i]);
					p.setInt(4, id);
					p.execute();
				}
				close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int authenticateUser(String userName, String userPassword){
		String query = "";
		query = "SELECT userId FROM users WHERE userName=? AND userPassword = ?;";
		PreparedStatement p = null;
		try {
			p = connection.prepareStatement(query);
			p.setString(1, userName);
			p.setString(2, userPassword);
			ResultSet rs;
			rs = p.executeQuery();
			
			if(rs.next()){
				String id = rs.getString(1);
				return Integer.parseInt(id);	// has matching record
			} else {
				return -1;	// empty record set.
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;		// empty record set.
		} finally {
		}
	}
	
	public String validUserPassword(String userName, String userPassword){
		String query = "";
		query = "SELECT userId FROM users WHERE userName=? AND userPassword = ?;";
		PreparedStatement p = null;
		try {
			p = connection.prepareStatement(query);
			p.setString(1, userName);
			p.setString(2, userPassword);
			ResultSet rs;
			rs = p.executeQuery();
			
			if(rs.next()){
				String id = rs.getString(1);
				return id;	// has matching record
			} else {
				return "";	// empty record set.
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "";		// empty record set.
		} finally {
		}
	}
}
