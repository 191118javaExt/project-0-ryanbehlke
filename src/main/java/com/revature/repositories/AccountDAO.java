package com.revature.repositories;

import java.util.List;
import java.util.Set;

import com.revature.models.Account;
import com.revature.models.User;

public interface AccountDAO { 

	public List<Account> getAllAccounts();
		
	public  Account getAccountById(int id);
	
	public  boolean addAccount(Account a);
	
	public  boolean approveAccount(Account a);
	
	public  boolean deleteAccount(Account a);
		
	public  boolean deposit(Account a, double amount);
	public boolean withdraw(Account a, double amount);
	public boolean transfer(Account from, Account to, double amount);
	public boolean denyAccount(Account a);
	public int getMaxAccountID();
}
