package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class AccountDAOImp implements AccountDAO {
	
	private static Logger logger = Logger.getLogger(AccountDAOImp.class);


	@Override
	public List<Account> getAllAccounts() {
		List<Account> list = new ArrayList<>();
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "SELECT * FROM project0.account;";
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int account_id = rs.getInt("account_id");
				double balance = rs.getDouble("balance");
				boolean approved = rs.getBoolean("approved");
				list.add(new Account(approved, account_id, balance));			
			}
			rs.close();
		} catch (SQLException e) {
			//logger info
		}
		return list;
	}


	@Override
	public Account getAccountById(int id) {
		Account a = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "SELECT * FROM project0.account WHERE account_id = (?);";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int account_id = rs.getInt("account_id");
				double balance = rs.getDouble("balance");
				boolean approved = rs.getBoolean("approved");
				a = new Account(approved, account_id, balance);			
			}
			rs.close();
		} catch (SQLException e) {
			//logger info
		}
		return a;
	}

	@Override
	public boolean addAccount(Account a) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "INSERT INTO project0.account (approved, balance) VALUES"
					+ " (?, ?);";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setBoolean(1, a.getApproved());
			stmt.setDouble(2, a.getBalance());
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
	public boolean approveAccount(Account a) {
		// TODO Auto-generated method stub
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "UPDATE project0.account SET approved = (?) WHERE account_id = (?);";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setBoolean(1, true);
			stmt.setInt(2, a.getAccount_id());
			if(!stmt.execute()) {
				a.setApproved(true);
				return true;
			}
		} catch (SQLException e) {
			//logger info
			return false;
		}
		return true;
	}


	@Override
	public boolean deleteAccount(Account a) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "DELETE FROM project0.account WHERE account_id = (?);";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, a.getAccount_id());
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
	public boolean deposit(Account a, double amount) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "UPDATE project0.account SET balance = (?) WHERE account_id = (?);";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setDouble(1, a.getBalance() + amount);
			stmt.setInt(2, a.getAccount_id());
			if(!stmt.execute()) {
				a.setBalance(a.getBalance() + amount);
				return true;
			}
		} catch (SQLException e) {
			//logger info
			return false;
		}
		return true;
	}
	
	@Override 
	public boolean withdraw(Account a, double amount) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "UPDATE project0.account SET balance = (?) WHERE account_id = (?);";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setDouble(1, a.getBalance() - amount);
			stmt.setInt(2, a.getAccount_id());
			if(!stmt.execute()) {
				a.setBalance(a.getBalance() - amount);
				return true;
			}
		} catch (SQLException e) {
			//logger info
			return false;
		}
		return true;
		
	}
	
	@Override
	public boolean transfer(Account from, Account to, double amount) {
		if (amount > from.getBalance()) {
			return false;
		} else {
			if(withdraw(from, amount) && deposit(to, amount)) {
				return true;
			}
		}
		return false;
	}
	
	public int getMaxAccountID() {
		int result=0;
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "SELECT MAX(account_id) as result  FROM project0.account;";
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				result=rs.getInt("result");
			}
			rs.close();
		} catch (SQLException e) {
			//logger info
		}
		return result;
	}


	@Override
	public boolean denyAccount(Account a) {
		// TODO Auto-generated method stub
				try(Connection conn = ConnectionUtil.getConnection()){
					String query = "UPDATE project0.account SET approved = (?) WHERE account_id = (?);";
					PreparedStatement stmt = conn.prepareStatement(query);
					stmt.setBoolean(1, false);
					stmt.setInt(2, a.getAccount_id());
					if(!stmt.execute()) {
						a.setApproved(false);
						return true;
					}
				} catch (SQLException e) {
					//logger info
					return false;
				}
				return true;
	}
	
}
