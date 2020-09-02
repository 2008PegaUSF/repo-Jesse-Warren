package beans;

import java.io.Serializable;

public class BankAccount implements Serializable {
	protected int ID;
	protected double balance;
	
	public BankAccount(int ID, double balance) {
		this.ID=ID;
		this.balance=balance;
	}
	
	public BankAccount() {
		
	}

	public int getID() {
		return ID;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public String toString() {
		return "BankAccount[ID: " +this.ID+ ", balance: " +
	this.balance + "]";
	}
	
	public boolean equals(BankAccount other) {
		return this.ID==other.getID() && this.balance==other.getBalance();
	}
}
