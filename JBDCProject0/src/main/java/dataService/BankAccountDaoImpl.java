package dataService;
import java.util.*;

import beans.BankAccount;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import org.apache.logging.log4j.core.Logger;


public class BankAccountDaoImpl implements BankAccountDao {
	
	public static ConnFactory cf = ConnFactory.getConnected();
	
	//method to list all bank accounts
	public List<BankAccount> getBankAccounts() throws SQLException{
		//setting our bank account arraylist up
		List<BankAccount> accountList= new ArrayList<BankAccount>();
		Connection conn= cf.getConnect();//creating the prepared statement
		Statement stmt= conn.createStatement();
		ResultSet rs= stmt.executeQuery("SELECT * FROM BANKACCOUNT ORDER BY AccountId");
		BankAccount a= null;
		//while there is another value in the next slot in the value, add the two values to the list.
		while (rs.next()){
			a= new BankAccount(rs.getInt(1), rs.getDouble(2));
			accountList.add(a);
			}
		//return the arraylist
		return accountList;
}
	//the method to gather all accounts owned by a user
	public List<BankAccount>getBankAccountsById(int id) throws SQLException{
		//creating our array list to hold the bank account
		List<BankAccount> accountList= new ArrayList<BankAccount>();
		Connection conn = cf.getConnect();
		//our prepared statement 
		PreparedStatement pstmt= conn.prepareStatement("SELECT * FROM BANKACCOUNT WHERE ACCOUNTID IN (SELECT ACCOUNTID FROM BANKUSERS WHERE USERID= ?) ORDER BY ACCOUNTID");
		pstmt.setInt(1, id);
		BankAccount a= null;
		//filling the arraylist with data from our query
		ResultSet rs=pstmt.executeQuery();
		while (rs.next()){
			a= new BankAccount(rs.getInt(1), rs.getDouble(2));
			accountList.add(a);
			}
		//returning the arraylist
		return accountList;
}
	//the method to retrieve a singular account of a user
	public BankAccount getBankAccount(int id) throws SQLException {
		Connection conn = cf.getConnect();
		//the prepared statement
		PreparedStatement pstmt =conn.prepareStatement("SELECT * FROM BANKACCOUNT WHERE ACCOUNTID = ?");
		pstmt.setInt(1, id);
		ResultSet rs=pstmt.executeQuery();
		//filling the bank account with our data
		BankAccount a= null;
		while(rs.next()) {
			a=new BankAccount(rs.getInt(1), rs.getDouble(2));
		///returning the bank account
		} return a;
	}
	
	//the method to withdraw from our database
	public void withdraw(int id, double amount) throws SQLException{
		Connection conn = cf.getConnect();
		//the prepared statement
		PreparedStatement pstmt=conn.prepareStatement("UPDATE BANKACCOUNT SET BALANCE=BALANCE - ? WHERE ACCOUNTID=?");
		//setting the first argument to amount
		pstmt.setDouble(1,amount);
		//setting the second argument to amount
		pstmt.setInt(2, id);
		pstmt.execute();
		
	}
	//the method to deposit into our table row
	public void deposit (int id, double amount) throws SQLException{
		Connection conn = cf.getConnect();
		//the prepared statement
		PreparedStatement pstmt=conn.prepareStatement("UPDATE BANKACCOUNT SET BALANCE=BALANCE + ? WHERE ACCOUNTID=?");
		//setting amount to the first argument
		pstmt.setDouble(1,amount);
		//setting id to the second argument
		pstmt.setInt(2, id);
		pstmt.execute();
	}
	//the method to create a bankaccount for the user	
	public void createAccount(int userid) throws SQLException{
		Connection conn = cf.getConnect();
		//passing our id to the  sql function new_account 
		PreparedStatement pstmt = conn.prepareStatement("select new_account(?)");
		//setting the id as the argument
		pstmt.setInt(1, userid);
		pstmt.execute();
	}
	

	//the method to delete an account
	public void deleteAccount(int id) throws SQLException{
		Connection conn= cf.getConnect();
		//passing our account id to the prepared statement
		PreparedStatement pstmt= conn.prepareStatement("DELETE FROM BANKACCOUNT WHERE ACCOUNTID= ?");
		pstmt.setInt(1, id);
		pstmt.execute();
	}
	
}


