package com.revature.repositories;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import com.revature.models.User;

public interface UserDAO {


	public  List<User> getAllUsers();
	
	public  User getUserByName(String name);
	
	public  boolean addUser(User u, int account_id);
	
	public boolean addEmployee(User u);
	
	public boolean setAccountID(User u, int num);

}
