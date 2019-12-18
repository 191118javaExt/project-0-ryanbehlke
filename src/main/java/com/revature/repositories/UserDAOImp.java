package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class UserDAOImp implements UserDAO{
	
	
	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> list = new ArrayList<>();
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "SELECT * FROM project0.users;";
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				String role = rs.getString("role");
				int account_id = rs.getInt("account_id");
				list.add(new User(username, password, role, account_id));			
			}
			rs.close();
		} catch (SQLException e) {
			//logger info
		}
		return list;
	}

	@Override
	public User getUserByName(String name) {
		// TODO Auto-generated method stub
		User u;
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "SELECT * FROM project0.users WHERE username = (?);";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				String role = rs.getString("role");
				int account_id = rs.getInt("account_id");
				return new User(username, password, role, account_id);			
			}
			rs.close();
		} catch (SQLException e) {
			//logger info
		}
		return null;
	}


	public boolean addUser(User u, int account_id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "INSERT INTO project0.users (username, password, role, account_id) VALUES"
					+ "(?, ?, ?, ?);";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, u.getUsername());
			stmt.setString(2, u.getPassword());
			stmt.setString(3, u.getRole());
			stmt.setInt(4, account_id);
			if(!stmt.execute()) {
				return true;
			}
		} catch (SQLException e) {
			//logger info
			return false;
		}
		return true;
	}

	@Override
	public boolean addEmployee(User u) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "INSERT INTO project0.users (username, password, role, account_id) VALUES"
					+ "(?, ?, ?, ?);";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, u.getUsername());
			stmt.setString(2, u.getPassword());
			stmt.setString(3, u.getRole());
			stmt.setInt(4, java.sql.Types.NULL);
			if(!stmt.execute()) {
				return true;
			}
		} catch (SQLException e) {
			//logger info
			return false;
		}
		return true;
	}

	@Override
	public boolean setAccountID(User u, int num) {
		// TODO Auto-generated method stub
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "UPDATE project0.users SET account_id = (?) WHERE username = (?);";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, num);
			stmt.setString(2, u.getUsername());
			if(!stmt.execute()) {
				u.setAccount_id(num);
				return true;
			}
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	

	
}
