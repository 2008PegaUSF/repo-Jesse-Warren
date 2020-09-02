import java.sql.SQLException;
import org.junit.jupiter.api.*;
import dataService.*;
import beans.*;

public class TestBank {
	static int testId;
	static int testAccount;
	
	@BeforeAll//Creating a test user and bank account
	public static void createTestUser() throws SQLException{
		UsersDaoImpl a= new UsersDaoImpl();
		BankAccountDaoImpl b=new BankAccountDaoImpl();
		//if the username is vacant create the user and insert some good stuff
		if(a.getUserByName("test")==null) {
			a.createCustomer("test", "password", "Test", 27);
		}
		//create a bank account for the temp user.
		testId=a.getUserByName("test").getUserId();
		b.createAccount(testId);
		}
	
	
	
	@AfterAll //deleting the user and account
	public static void deleteTestUser() throws SQLException{
		BankAccountDaoImpl b= new BankAccountDaoImpl();
		//lookup variable
		String s="test";
		UsersDaoImpl a= new UsersDaoImpl();
		Users testUser=a.getUserByName("test");
		// if the record is not null,
		//delete the user and the account
		if(a.getUserByName(s) != null) {
			a.deleteUser(s);
			b.deleteAccount(testUser.getUserId());
		}
	}
	
	
	@Test
	void testWithdrawal() throws SQLException {
		BankAccountDaoImpl b= new BankAccountDaoImpl();
		UsersDaoImpl a= new UsersDaoImpl();
		BankAccount testAccount= b.getBankAccountsById(testId).get(0);
		b.withdraw(testAccount.getID(), 50);
		BankAccount secondTest=b.getBankAccountsById(testId).get(0);
		double targetValue=testAccount.getBalance() - 50;
		Assertions.assertEquals(targetValue, secondTest.getBalance());

}
	@Test
	void testDeposit() throws SQLException {
		BankAccountDaoImpl b= new BankAccountDaoImpl();
		UsersDaoImpl a= new UsersDaoImpl();
		BankAccount testAccount= b.getBankAccountsById(testId).get(0);
		b.deposit(testAccount.getID(), 50);
		BankAccount secondTest=b.getBankAccountsById(testId).get(0);
		double targetValue=testAccount.getBalance() + 50;
		Assertions.assertEquals(targetValue, secondTest.getBalance());
	
}
}