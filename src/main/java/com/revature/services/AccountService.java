package com.revature.services;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.repositories.AccountDAO;
import com.revature.repositories.AccountDAOImp;

public class AccountService {
	private AccountDAO repository = new AccountDAOImp();
	
	public List<Account> getAllAccounts(){
		return repository.getAllAccounts();	
	}
	
	public  Account getAccountById(int id) {
		return repository.getAccountById(id);
	}
	
	public  boolean addAccount(Account a) {
		return repository.addAccount(a);
	}
	
	public  boolean approveAccount(Account a) {
		return repository.approveAccount(a);
	}
	
	public  boolean deleteAccount(Account a) {
		return repository.deleteAccount(a);
	}
		
	public  boolean deposit(Account a, double amount) {
		return repository.deposit(a, amount);
	}
	
	public boolean withdraw(Account a, double amount) {
		return repository.withdraw(a, amount);
	}
	public boolean transfer(Account from, Account to, double amount) {
		return repository.transfer(from, to, amount);
	}
	public int getMaxAccountID() {
		return repository.getMaxAccountID();
	}
	public boolean denyAccount(Account a) {
		return repository.denyAccount(a);
	}
}
