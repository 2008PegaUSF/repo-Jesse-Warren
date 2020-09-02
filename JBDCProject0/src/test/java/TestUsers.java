import java.sql.SQLException;
import org.junit.jupiter.api.*;
import dataService.*;
import beans.*;

public class TestUsers {
	//Variables to help us create a temporary user and account
	static int testId;
	static int testAccount;	
	
	@BeforeAll	//creating a temporary user and creating an account attached to them.
	public static void createTestUser() throws SQLException{
		//creating a new user instance
		UsersDaoImpl a= new UsersDaoImpl();
		//creating a new bank account instance
		BankAccountDaoImpl b=new BankAccountDaoImpl();
		//if their username is null, creating the user and inserting some tasty bits
		if(a.getUserByName("test")==null) {
			a.createCustomer("test", "password", "Test", 27);
		}
		//creating their account
		testId=a.getUserByName("test").getUserId();
		//create the account
		b.createAccount(testId);
		}
	
	
	
	@AfterAll	//cleaning up or temporary user and his test account
	public static void deleteTestUser() throws SQLException{
		String s="test";
		//to delete the test user
		String t="Changed";
		//to delete the target user		
		BankAccountDaoImpl b=new BankAccountDaoImpl();
		UsersDaoImpl a= new UsersDaoImpl();
		//if the target names are not null delete them
		if(a.getUserByName(s) != null) {
			a.deleteUser(s);
		}
		//if the target names are not null delete them
		if(a.getUserByName(t)!=null) {
			a.deleteUser(t);
			b.deleteAccount(testId);
		}
		
	}
	@Test	//testing update username
	void testUpdateUsername() throws SQLException{
			//new instance of the users dao
	UsersDaoImpl a=new UsersDaoImpl();
	//a new user object
	Users testUser= a.getUserByName("test");
	//changing the username
	a.changeUsername(testUser.getUsername(), "Changed");
	// a second user 
	Users target=a.getUserByName("Changed");
	//their usernames are the same
	Assertions.assertEquals("Changed", target.getUsername());
	
	}
	
	@Test //testing update name
	void testUpdateName() throws SQLException{
		//new instance of the users dao
		UsersDaoImpl a=new UsersDaoImpl();
		//new user object
		Users testUser= a.getUserByName("test");
		//changing that name
		a.changeName(testUser.getUsername(), "Changed");
		//a second user with same info
		Users target=a.getUserByName("test");
		//their names are the same
		Assertions.assertEquals("Changed", target.getName());
		
	}
	
	@Test // testing update age
	void testUpdateAge() throws SQLException{
		//new instance of the user dao
		UsersDaoImpl a=new UsersDaoImpl();
		//user from the table
		Users testUser= a.getUserByName("test");
		//changing their age
		a.changeAge(testUser.getUsername(), 15);
		//a second user with the same info
		Users target=a.getUserByName("test");
		//they're the same age
		Assertions.assertEquals(15, target.getAge());
	
	}
	
	@Test // testing update password
	void testUpdatePassword() throws SQLException{
		//creating a new instance of a user
		UsersDaoImpl a=new UsersDaoImpl();
		//the test user is equal to the user in the table
		Users testUser= a.getUserByName("Changed");
		//change their password
		a.changePassword(testUser.getUsername(), "Changed");
		//a second new user is equal to the user we just modified
		Users target=a.getUserByName("Changed");
		//their passwords are the same
		Assertions.assertEquals("Changed", target.getPassword());
	
	}
	
}
