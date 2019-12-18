package com.revature.services;

import java.util.List;

import com.revature.models.User;
import com.revature.repositories.UserDAO;
import com.revature.repositories.UserDAOImp;

public class UserServices {
	UserDAO repository = new UserDAOImp();
	
	public  List<User> getAllUsers(){
		return repository.getAllUsers();
	}
	
	public  User getUserByName(String name) {
		return repository.getUserByName(name);
	}
	
	public  boolean addUser(User u, int account_id) {
		return repository.addUser(u, account_id);
	}
	
	public boolean addEmployee(User u) {
		return repository.addEmployee(u);
	}
	
	public boolean setAccountID(User u, int num) {
		return repository.setAccountID(u, num);
	}
}
