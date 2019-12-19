package com.revature;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.services.AccountService;
import com.revature.services.UserServices;

public class Driver { 
	
	public static void main(String[] args){
		
		Bank b = new Bank();
		b.LogInOrRegister();
	}
}

class Bank{
	private User user;
	UserServices us = new UserServices();
	AccountService as = new AccountService();
	private Account current;
	public Bank() {
		
	}
	
	void LogInOrRegister() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to the RTB Banking App! [1]Log In or [2]Register?");
		String option = sc.nextLine();
		switch(option) {
			case "1":{
				System.out.println("Please enter a username");
				String username = sc.nextLine();
				System.out.println("Please enter a password");
				String password = sc.nextLine();
				if(us.getUserByName(username) != null) {
					if(us.getUserByName(username).getPassword().equals(password)) {
						user = us.getUserByName(username);
						current = as.getAccountById(user.getAccount_id());
						if(user.getRole().equals("Customer")) {
							if(current.getApproved()) {
								showMenu();
							} else {
								System.out.println("Account has not been approved");
							}
						} else if (user.getRole().equals("Employee")) {
							employeeMenu();
						} else {
							adminMenu();
						}
					} else {
						System.out.println("Password incorrect");
					}
				} else {
					System.out.println("User does not exist");
				}
				break;	
			}
			case "2":{
				System.out.println("Please enter the username you want to use");
				String username = sc.nextLine();
				if(us.getUserByName(username) == null) {
					System.out.println("[1] Customer, [2] Employee, or [3] Admin");
					String choice = sc.nextLine();
					String role ="";
					switch(choice) {
					case "1":{
						role = "Customer";
						System.out.println("Please enter a password");
						String password = sc.nextLine();
						int curr_id;
						if(as.getMaxAccountID() == 0) {
							curr_id = 1;
						} else {
							curr_id = as.getMaxAccountID() + 1;
						}
						User u = new User(username, password, role, curr_id);
						Account a = new Account(false, curr_id, 0.0);
						as.addAccount(a);
						if(us.addUser(u, curr_id)) {
							System.out.println("User created with account ID" + curr_id);
						} else {
							System.out.println("User creation failed");
							as.deleteAccount(a);
						}
						
						break;
					}
					case "2":{
						role = "Employee";
						System.out.println("Please enter a password");
						String password = sc.nextLine();
						User u = new User(username, password, role, -1);
						if(us.addEmployee(u)) {
							System.out.println("Employee created");
						} else {
							System.out.println("Employee creation failed");
						}
						break;
					}
					case "3":{
						role = "Admin";
						System.out.println("Please enter a password");
						String password = sc.nextLine();
						User u = new User(username, password, role, -1);
						if(us.addEmployee(u)) {
							System.out.println("Admin created");
						} else {
							System.out.println("Admin creation failed");
						}
						break;
					}
					default:{
						System.out.println("Invalid Input");
						break;
					}
					}
					if(role.equals("")) {
						break;
					}
					
			} else {
				System.out.println("Username already exists");
			}
			break;
			}
			default:{
				System.out.println("Invalid Input");
			}
		}
	}
	void adminMenu() {
		Scanner scanner = new Scanner(System.in); 
		String option="";
		boolean quit = false;
		do {
			System.out.println("Welcome, "+ user.getUsername());
			System.out.println("\n");
			System.out.println("1. User methods");
			System.out.println("2. Employee methods");
			System.out.println("3. Delete Account");
			System.out.println("4. Quit");
			option=scanner.nextLine();
			switch(option) {
			case "1":{
				System.out.println("Enter the username of the User whose account you would like to use");
				String username = scanner.nextLine();
				if(us.getUserByName(username) != null) {
					current = as.getAccountById(us.getUserByName(username).getAccount_id());
					showMenu();
				} else {
					System.out.println("User does not exist");
				}
				break;
			}
			case "2":{
				employeeMenu();
				break;
			}
			case "3":{
				System.out.println("Enter the username of the User you would like to delete");
				String username = scanner.nextLine();
				if(us.getUserByName(username) != null) {
					User u = us.getUserByName(username);
					int temp = u.getAccount_id();
					us.setAccountID(u, java.sql.Types.NULL);
					Account a = as.getAccountById(temp);
					if(as.deleteAccount(a)) {
						System.out.println("Account Deleted");
					} else {
						us.setAccountID(u, temp);
						System.out.println("Deletion Failed");
						
					}
				} else {
					System.out.println("User does not exist");
				}
				break;
			}
			case "4":{
				quit = true;
				break;
			} default :{
				System.out.println("Invalid Input");
				break;
			}
			}
		} while (!quit);
		System.out.println("Thank you for using the RTB Banking app");
	}
	void employeeMenu() {
		Scanner scanner = new Scanner(System.in); 
		String option="";
		boolean quit = false;
		do {
			System.out.println("Welcome, "+ user.getUsername());
			System.out.println("\n");
			System.out.println("1. View Users");
			System.out.println("2. View Accounts");
			System.out.println("3. Approve User");
			System.out.println("4  Deny User");
			System.out.println("5. Exit");
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			System.out.println("Please enter an option.");
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			option = scanner.nextLine(); 
			System.out.println("\n");	
			switch(option) {
			
			case "1":
				System.out.println("-------------------------------");
				
				for(User u: us.getAllUsers()) {
					System.out.println(u.toString());
				}
				System.out.println("-------------------------------");
				System.out.println("\n");
				break; 
				
			case "2": 
				System.out.println("-------------------------------");
				
				for(Account a: as.getAllAccounts()) {
					System.out.println(a.toString());
				}
				System.out.println("-------------------------------");
				System.out.println("\n");
				break;
				
			case "3":{
				System.out.println("-------------------------------");
				System.out.println("Enter the username of the User you want to approve:");
				System.out.println("-------------------------------");
				String username = scanner.nextLine();
				if(us.getUserByName(username) != null) {
					User u = us.getUserByName(username);
					if(as.approveAccount(as.getAccountById(u.getAccount_id()))) {
						System.out.println("Account Approved");
					} else {
						System.out.println("Account Approval Failed");
					}
				} else {
					System.out.println("Username not found");
				}
				System.out.println("\n"); 
				break;
			}
			case "4":
				System.out.println("-------------------------------");
				System.out.println("Enter the username of the User you want to deny:");
				System.out.println("-------------------------------");
				String username = scanner.nextLine();
				if(us.getUserByName(username) != null) {
					User u = us.getUserByName(username);
					if(as.denyAccount(as.getAccountById(u.getAccount_id()))) {
						System.out.println("Account Denied");
					} else {
						System.out.println("Account Denial Failed");
					}
				} else {
					System.out.println("Username not found");
				}
				System.out.println("\n"); 
				break;
			case "5":
				quit = true;
				break;
			default:
				System.out.println("Invalid option! Please enter again");
				break;
			}
			
		}while(!quit);
		
		System.out.println("Thank you for using the RTB Banking app");
	}
		
	
	void showMenu() {
		
		int option = '\0'; 
		Scanner scanner = new Scanner(System.in); 
		
		boolean quit = false;
		
		do {
			System.out.println("Welcome, "+ user.getUsername());
			System.out.println("\n");
			System.out.println("1. Check Balance");
			System.out.println("2. Deposit");
			System.out.println("3. Withdraw");
			System.out.println("4. Transfer");
			System.out.println("5. Exit");
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			System.out.println("Please enter an option.");
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			option = scanner.next().charAt(0); 
			System.out.println("\n");
			
			switch(option) {
			
			case '1':
				System.out.println("-------------------------------");
				
				System.out.println("Balance =$" + current.getBalance());
				System.out.println("-------------------------------");
				System.out.println("\n");
				break; 
				
			case '2': 
				System.out.println("-------------------------------");
				System.out.println("Enter an amount to deposit:");
				System.out.println("-------------------------------");
				try{
					double amount = scanner.nextDouble();
					if(amount < 0) {
						System.out.println("Amount must be non-negative");
						break;
					}
					if(as.deposit(current, amount)) {
						System.out.println("Deposit Success");
					} else {
						System.out.println("Deposit Failed");
					}
				} catch (InputMismatchException e) {
					System.out.println("Invalid Input");
				}
				System.out.println("\n");
				break;
				
			case '3':
				System.out.println("-------------------------------");
				System.out.println("Enter an amount to withdraw:");
				System.out.println("-------------------------------");
				try {
					double amount = scanner.nextDouble(); 
					if(amount < 0 || amount > current.getBalance()) {
						System.out.println("Unacceptable Input");
						break;
					}
					if(as.withdraw(current, amount)) {
						System.out.println("Withdrawal Success");
					} else {
						System.out.println("Withdrawal Failed");
					}
				} catch (InputMismatchException e) {
					System.out.println("Invalid Input");
				}
				System.out.println("\n"); 
				break;
				
			case '4':
				System.out.println("-------------------------------");
				System.out.println("Enter the account ID of account you are transferring to");
				try{
					int to_acc = scanner.nextInt();
					if(as.getAccountById(to_acc) == null) {
						System.out.println("Account does not exist");
						break;
					}
					Account to = as.getAccountById(to_acc);
					System.out.println("Enter the amount you are transferring");
					double amount = scanner.nextDouble();
					if(amount < 0 || amount > current.getBalance()) {
						System.out.println("Unacceptable Input");
						
						break;
					}
					if(as.transfer(current, to, amount)) {
						System.out.println("Transfer Success");;
					} else {
						System.out.println("Transfer Failed");
					}
				} catch(InputMismatchException e) {
					System.out.println("Invalid Input");
				}
				System.out.println("-------------------------------");
				System.out.println("\n");
				break;
			case '5':{
				quit = true;
				break;
			}
			default:
				System.out.println("Invalid option! Please enter again");
				break;
			}
			
		}while(!quit); 
		
		System.out.println("Thank you for using the RTB Banking app");
	}
}