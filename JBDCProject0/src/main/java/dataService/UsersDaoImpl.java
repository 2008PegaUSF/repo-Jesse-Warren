package dataService;
import java.sql.*;
import java.util.*;
import beans.BankAccount;
import beans.Users;

public class UsersDaoImpl implements UsersDao {
	public static ConnFactory cf = ConnFactory.getConnected();

	public List<Users> getUsers() throws SQLException {
		//creating our array list of users
		List<Users> uList=new ArrayList<Users>();
		Connection conn=cf.getConnect();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM BANKUSERS");
		//filling our arraylist full of the data fetched from our prepared statement
		Users a= null;
		while(rs.next()) {
		a= new Users(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4),rs.getInt(5), rs.getInt(6));
			uList.add(a);
		}
		//returning the arraylist
		return uList;
		
	}
	//the method to get a user of a bankaccount by their bank id number
	public List<Users> getAccountUser(int bankid) throws SQLException {
		List<Users> oList = new ArrayList<Users>();
		Connection conn = cf.getConnect();//asking if a account id falls in the bankusers table
		PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM BANKUSERS WHERE USERID IN (SELECT UID FROM USERS WHERE ACCOUNTID = ?)");
		pstmt.setInt(1, bankid);
		Users a=null;
		ResultSet rs= pstmt.executeQuery();
		//filling our list
		while(rs.next()) {
			a= new Users(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4),rs.getInt(5), rs.getInt(6));
			oList.add(a);
		} return oList; //returning our list
	}
//the method to retrieve a user by their username
	public Users getUserByName(String uname) throws SQLException {
		Connection conn=cf.getConnect();//selecting all of the users of a certain username
		PreparedStatement pstmt= conn.prepareStatement("SELECT * FROM BANKUSERS WHERE BUSERNAME=?");
		pstmt.setString(1, uname);
		Users a=null;
		//filling the user object with the data from our query
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			a= new Users(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4),rs.getInt(5), rs.getInt(6));
			
			}return a;//returning the user object
	}
	
//the method to change a username
	public void changeUsername(String curName, String newName) throws SQLException {
		Connection conn= cf.getConnect();//change the username belonging to said username
		PreparedStatement  pstmt= conn.prepareStatement("UPDATE BANKUSERS SET BUSERNAME=? WHERE BUSERNAME=?");
		pstmt.setString(1, newName);
		pstmt.setString(2, curName);
		pstmt.execute();
		
		
	}
//the method to change a password
	public void changePassword(String uname, String pword) throws SQLException {
		Connection conn= cf.getConnect();//change the password of a username
		PreparedStatement pstmt= conn.prepareStatement("UPDATE BANKUSERS SET BPASSWORD = ? WHERE BUSERNAME = ?");
		pstmt.setString(1,pword);
		pstmt.setString(2,uname);
		pstmt.execute();
		
	}
//the method to change a name
	public void changeName(String uname, String name) throws SQLException {
		Connection conn= cf.getConnect();//change the name of a username
		PreparedStatement pstmt= conn.prepareStatement("UPDATE BANKUSERS SET BNAME = ? WHERE BUSERNAME= ?");
		pstmt.setString(1,name);
		pstmt.setString(2,uname);
		pstmt.execute();
		
	}
//the method to change an age
	public void changeAge(String uname, int age) throws SQLException {
		Connection conn= cf.getConnect();//change the age of a username
		PreparedStatement pstmt= conn.prepareStatement("UPDATE BANKUSERS SET BAGE = ? WHERE BUSERNAME = ?");
		pstmt.setInt(1,age);
		pstmt.setString(2,uname);
		pstmt.execute();
		
	}
//the method to create a customer 
	public void createCustomer(String uname, String pword, String name, int age) throws SQLException {
		Connection conn= cf.getConnect();//insert values into the bank user table
		PreparedStatement pstmt= conn.prepareStatement("INSERT INTO BANKUSERS(busername, bpassword, bname, bage, usertype) values (?,?,?,?,1)");
		pstmt.setString(1, uname);
		pstmt.setString(2, pword);
		pstmt.setString(3, name);
		pstmt.setInt(4, age);
		pstmt.execute();
	}
//the method to delete a user 
	public void deleteUser(String uname) throws SQLException {
		Connection conn= cf.getConnect();//calling the delete from users function which accepts the username as a parameter
		PreparedStatement pstmt= conn.prepareStatement("SELECT DELETE_FROM_USERS(?)");
		pstmt.setString(1,uname);
		pstmt.execute();
	}

}
