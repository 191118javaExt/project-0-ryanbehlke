package com.revature.models;

public class Account {
	
	private boolean approved; 
	private int account_id; 
	private double balance;
	
	public Account(boolean approved, int account_id, double balance) {
		super();
		this.approved = approved;
		this.account_id = account_id;
		this.balance = balance;
	}
	
	public Account(boolean approved, double balance) {
		super();
		this.approved = approved;
		this.balance = balance;
	}
	
	public boolean getApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	public int getAccount_id() {
		return account_id;
	}
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + account_id;
		result = prime * result + (approved ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (account_id != other.account_id)
			return false;
		if (approved != other.approved)
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Account [approved=" + approved + ", account_id=" + account_id + ", balance=" + balance + "]";
	} 
}
