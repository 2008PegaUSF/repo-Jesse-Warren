package BankingFunction;

import java.io.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.*;
import dataService.*;
import beans.*;


//The banking application!
public class BankApp {
	//declaring our logger
	static Logger log=LogManager.getLogger(BankApp.class);
	//The main method
	public static void main(String[] args) throws SQLException, InvalidClassException {
		Users theUser = null;
		Scanner console= new Scanner(System.in);
		Logger log=LogManager.getLogger(BankApp.class);
		boolean quit= false;
		//starting the UI
		while(!quit) {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("                              '/ \'                                ");
			System.out.println("            Welcome          '/_ _\'          To                   ");
			System.out.println("                         	'/\' '/\'                              ");
			System.out.println("                           '/'_\'/_'\'                             ");
			System.out.println("                       	Warren Golden Bank                         ");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("                  |       Choose an Option:    |                   ");
			System.out.println("                  |                            |                   ");
			System.out.println("                  |       	1.Login            |                   ");
			System.out.println("                  |                            |                   ");
			System.out.println("                  |        	2.Register         |                   ");
			System.out.println("                  |                            |                   ");
			System.out.println("                  |        	3.Quit             |                   ");	
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			int choice=validateInteger(console);
			switch (choice) {
			case 1: //starting the login process
				theUser=Login(console);
				if(theUser!=null) {
					switch(theUser.getType()) {
					case 1://sending to the customer UI
						CustomerUI(theUser, console);
						break;
					case 2: //sending to the admin UI
						AdminUI(theUser,console);
						break;
						
					}
				} break;
			case 2: //registering users
				registerUser(console, false);
				break;
			case 3://quitting the application
				quit=true;
				break;
			default://catching those ruffians and wildcats
				System.out.println("Please enter a valid input");
				break;
			}
		}//a goodbye message
		System.out.println("Thank you for choosing Warren Golden Bank");
	}
	
////////////////////////////////////////////////////////////////////////////////////////
	//updating users method
	public static void userUpdate(Users curUser, Scanner in)throws SQLException{
			//making a member of the users data access object
			UsersDaoImpl u= new UsersDaoImpl();
			System.out.println("Which username would you like to update?");
			String targetedUser=in.nextLine();
			//the user that they'd like to target is placed in our placeholder
			Users theUser=u.getUserByName(targetedUser);
			if(theUser==null) {//none is found
				System.out.println("That doesn't match our records.");
				return;
			}
			else {//a user is found start the update process
				System.out.println("What would you like to update? \n1: Username \n2: Password \n3: Name \n4: age \n Any other choice cancels the program");
				int choice= validateInteger(in);
				switch (choice) {
				case 1://update the username 
					System.out.println("What would you like the username to be?");
					String newUName= in.nextLine();
					if(u.getUserByName(newUName)==null) {
						u.changeUsername(theUser.getUsername(), newUName);
						System.out.println ("The new username is "+newUName);	
						if (newUName.equals(theUser.getUsername())) {
							theUser.setUsername(newUName);
							log.info("The user: "+theUser.getUsername()+" changed a username");
						}return;
					}
					else {
						System.out.println("Username is not available");
				}
				case 2: //update the password
					System.out.println("What would you like the new password to be?");
					String nPassword=in.nextLine();
					u.changePassword(theUser.getUsername(), nPassword);
					System.out.println("You've changed your password");
					log.info("The user: "+theUser.getUsername()+" changed a password");
					return;
				
				case 3://update the name
					System.out.println("Enter your new name!");
					u.changeName(theUser.getUsername(), in.nextLine());
					System.out.println("You've changed your name!");
					log.info("The user: "+theUser.getUsername()+" changed a name");
					return;
				case 4://update the age
					System.out.println("How old are you this time?");
					int nAge= validateInteger(in);
					if (nAge<=0) {System.out.println("You're pretty lively for an infant");}
					else if(nAge<=14) {System.out.println("You're not old enough to even be here! But since you're here..");
					}
					u.changeAge(theUser.getUsername(), nAge);
					log.info("The user: "+theUser.getUsername()+" changed an age");
					System.out.println("You've changed your age!");
					return;
				default://catching those ruffians again.
					System.out.println("No updates for now");
					return;
				}				
			}					
		}
	//////////////////////////////////////////////////////////////////////////////////////////	
	//admin method for deleting a user 
	public static void deleteUser (Users theUser, Scanner in) throws SQLException {
		UsersDaoImpl u= new UsersDaoImpl();
		System.out.println("Enter the username of the targeted user. This will erase all of their records");
		//calling our get user by username and storing the data it brings into a user object
		//if no data was found print an message
		Users targetUser=u.getUserByName(in.nextLine());
		if (targetUser==null) {
			System.out.println("This doesn't match our records");
			//if the username matches the current user print a message
		}else if (targetUser.getUsername().equals(theUser.getUsername())) {
			System.out.println("NOO!!!!! YOU HAVE SO MUCH TO STAY IN MEMORY FOR!");
		}
		else {//if all is good, log the transaction and call the delete method on the user object and confirm with a message
			log.info("The user: "+theUser.getUsername()+" deleted the user " +targetUser.getUsername());
			u.deleteUser(targetUser.getUsername());
			System.out.println("You took them out");			
		}
	}
	
//////////////////////////////////////////////////////////////////////////////////////////	
	//method for viewing accounts
	public static void viewAccounts(Users theUser, Scanner in) throws SQLException {
		BankAccountDaoImpl a= new BankAccountDaoImpl();
		ArrayList<BankAccount> existingAccounts=null;
		switch(theUser.getType()) {//if the user is type one get them their account
		case 1: 
			existingAccounts=(ArrayList<BankAccount>) a.getBankAccountsById(theUser.getUserId());
			break;
		case 2://if the user is type 2 get them all accounts
			existingAccounts=(ArrayList<BankAccount>) a.getBankAccounts();
			break;
		default:
			System.out.println("Something is amiss");
			return;
		}
		int col = 0; //display their existing accounts arraylist
		System.out.println("Your accounts: ");
		for(BankAccount account: existingAccounts) {
			System.out.print(account+ "");
			if(++col % 5 == 0) {
				System.out.println();
			}
		}
		System.out.println();
	}	
	
//////////////////////////////////////////////////////////////////////////////////////////
	
	public static void createAccount(Users theUser, Scanner in) throws SQLException{
		//prompting the user
		BankAccountDaoImpl g=new BankAccountDaoImpl();
		System.out.println("Would you like to open an account? y/n");
	//if they do want to, callingthe method and logging the transaction
		String create=in.nextLine();
		if (create.equals("y")) {
			g.createAccount(theUser.getUserId());
			log.info("The user: "+theUser.getUsername()+" created an account");
		//canceling the creation
		}else {
			System.out.println("Creation cancelled");
			return;
		}
	}
	
//////////////////////////////////////////////////////////////////////////////////////////
	
	//the method to drive deposits
	public static void depositBank(Users theUser, Scanner in) throws SQLException{
		UsersDaoImpl f= new UsersDaoImpl();
		BankAccountDaoImpl g=new BankAccountDaoImpl();
		System.out.println("Enter the bank id of the account you'd like to deposit to.");
	//assigning the account id to a variable after passing it through our validation method
		int bankid=validateInteger(in);
	//if its validated pass it to out getter method
		BankAccount account= g.getBankAccount(bankid);
		//if theres no account print a message
		if(account==null) {
			System.out.println("This doesn't match our records.");
		}
		else {
			if(theUser.getType()==1) {//if the user is a customer, check if they own the account using our boolean value
				ArrayList<Users> bankCustomers=(ArrayList<Users>)f.getAccountUser(account.getID());
				boolean userOwnership=false;
				for(Users owner: bankCustomers) {
					if (owner.getUsername().equals(owner.getUsername())){//cycle through the table, if their username is found , they own the account
						userOwnership=true;
						break;
					}
				}//or.... they dont own the account
				if(!userOwnership) {
					System.out.println("This is does not match our records.");
					return;
				}
			}//display the balance and prompt to enter amount
			System.out.println("The account balance is: " +account.getBalance());
			System.out.println("The enter how much you'd like to deposit: ");
			//validating the amount to deposit
			double amount=validateDouble(in);
			//if its a negative number say no
			if(amount<0) {
				System.out.println("Sorry, thats not a correct input");
				
			} else {//calling the deposit method and displaying a confirmation method
				    //logging the transaction
				g.deposit(account.getID(), amount);
				System.out.println("You got some walking around money! Available balance is: " +account.getBalance());
				log.info(new String("The user: "+theUser.getUsername()+" deposited " +amount+ " to account: " +account.getID()));
			}
		}
	}
	
//////////////////////////////////////////////////////////////////////////////////////////
	
	//the method for withdrawing from a bankaccount
	public static void withdrawBank(Users theUser, Scanner in) throws SQLException{
		UsersDaoImpl f= new UsersDaoImpl();
		BankAccountDaoImpl g=new BankAccountDaoImpl();
		//accepting input of a bank account number and validating
		System.out.println("Enter the bank id of the account you'd like to withdraw from.");
		int bankid=validateInteger(in);
		//looking up the account number in out bankaccount table
		BankAccount account= g.getBankAccount(bankid);
		//if it isn't found display a message
		if(account==null) {
			System.out.println("This doesn't match our records.");
		}
		else {//if its found, make sure if the users a customer that they own it
			if(theUser.getType()==1) {
				ArrayList<Users> bankCustomers=(ArrayList<Users>)f.getAccountUser(account.getID());
				boolean userOwnership=false;
				for(Users owner: bankCustomers) {
					if (owner.getUsername().equals(owner.getUsername())){
						userOwnership=true;
						break;
					}
				}// if they don't own it, print a message
				if(!userOwnership) {
					System.out.println("This is does not match our records.");
					return;
				}
			}//print the balance and ask for a withdraw amount, validate the input
			System.out.println("The account balance is: " +account.getBalance());
			System.out.println("The enter how much you'd like to withdraw: ");
			double amount=validateDouble(in);
			//if its a negative number, print a message
			if(amount<0) {
				System.out.println("Sorry, thats not a correct input");
			//don't let them overdraft
			}else if(amount>account.getBalance()) {	
				System.out.println("Whoa slow down bro");
			
			} else {//do the withdraw, put up a confirmation message, log the transaction
				g.withdraw(account.getID(), amount);
				System.out.println("Go walk around with that money! Your available balance is :" +account.getBalance());
				log.info("The user: "+theUser.getUsername()+" withdrew " +amount+ " from account: " +account.getID());
			}
		}
	}	
	
	
//////////////////////////////////////////////////////////////////////////////////////////
	//the method to delete an empty account
	public static void deleteBankAccount(Users theUser,Scanner in) throws SQLException {
		BankAccountDaoImpl g=new BankAccountDaoImpl();		
		//warn the user and accept an account id
		System.out.println("Please keep in mind, an account must be empty to delete it.");
		System.out.println("Enter the bank id number of the account you'd like to delete: ");
		int aId=validateInteger(in);//validate that sucker
		BankAccount account= g.getBankAccount(aId);
		//see if that account exists in our bank accounts
		if (account==null) {//if it doesn't print a message
			System.out.println("That doesn't match our records");
		}else if (account.getBalance()>0){
			//if its not empty print a message
			System.out.println("The account isn't empty, please empty it first.");
		}
		else {//if it is, ask then is they're sure
			System.out.println("Cast it into the fire? y/n?");
			String cDelete=in.nextLine();
			if (cDelete.equals("y")) {
				//log the transaction, delete the account and print a confirmation message
				log.info("The user: "+theUser.getUsername()+" deleted an account");
				g.deleteAccount(account.getID());
				System.out.println("You've destroyed it");
			}else {
				//just a heads up
				System.out.println("You didn't cast it into the fire");
			}
		}
	}	

//////////////////////////////////////////////////////////////////////////////////////////
	//the method to register a user
	public static boolean registerUser(Scanner in, boolean isAdmin) throws SQLException {
		UsersDaoImpl f=new UsersDaoImpl();
		System.out.println("If you'd like to cancel type cancel");
		//setting our value holders to empty
		String uName=null;
		String nPassword=null;
		String nName=null;
		Integer nAge=null;
		//creating a username until one is stored in our placeholder
		while (uName==null) {
			System.out.println("Enter your username");
			String confirmName=in.nextLine();
			if(confirmName.equalsIgnoreCase("cancel")) {
				return false;
			}//if theres no other in the bankusers table, its available! JUST DO IT!
			else if(f.getUserByName(confirmName)==null) {
				System.out.println("Username available");
				uName=confirmName;
			}else {//else, pick another
				System.out.println("Username unavailable");
			}
		}//creating a password until one is stored in our placeholder
		while (nPassword==null) {
			System.out.println("Enter your password");
			String confirmPassword=in.nextLine();
			if(confirmPassword.equalsIgnoreCase("cancel")) {
				return false;
			}//set the password unless they enter cancel
			else  {
				System.out.println("Password Set");
				nPassword=confirmPassword;
			}
		}//while our place holder is empty run the prompt
		while (nName==null) {
			System.out.println("Enter your name");
			String confirmRealName=in.nextLine();
			if(confirmRealName.equalsIgnoreCase("cancel")) {
				return false;
			}
			else  {//set the name
				System.out.println("Name Set");
				nName=confirmRealName;
			}
		}
		while(nAge==null) {//set the age
			System.out.println("How old are you?");
			String confirmAge= in.nextLine();
			if(confirmAge.equalsIgnoreCase("cancel")) {
				return false;
			}
			else {
				int realAge=validateInteger(confirmAge, in);
				nAge=realAge;
			}
		}
		//log the transaction and print a prompt call the method
		log.info("A user was created");
	System.out.println(isAdmin ? "New user registered": "You may now login");
	f.createCustomer(uName, nPassword, nName, nAge);
	return true;	
	}	
	
//////////////////////////////////////////////////////////////////////////////////////////
	//the login method
	public static Users Login(Scanner in) throws SQLException {
		UsersDaoImpl f=new UsersDaoImpl();
		while(true) {
			//accepting a username and password or cancel
			System.out.print("Enter your username and password with a space between");
			String namepassword=in.nextLine();
			if(namepassword.equalsIgnoreCase("cancel")) {
				return null;
			}
			else {
			//split the login info into a string array split on the space
			String[] logInfo= namepassword.split(" ");
			//looking for the info at the first index for username
			Users theUser=f.getUserByName(logInfo[0]);
			if(theUser==null) {System.out.println("That username doesn't match our records");}
			else {
				//if the password is correct log the user in and log the transaction
				if(theUser.getPassword().equals(logInfo[1])) {
				System.out.println("You've successfully logged in" +theUser.getUsername());
				log.info("The user: "+theUser.getUsername()+" logged in");
				return theUser;
				}
				else {
				//this password didn't work bro
					System.out.println("The password did not match our records");
				}
			}
		}
	}		
}
	
//////////////////////////////////////////////////////////////////////////////////////////
	//the customer UI method
	public static void CustomerUI(Users theUser, Scanner in) throws InvalidClassException, SQLException{
		Integer choice=null;
		boolean quit=false;
		
		while(!quit) {			
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("     C                        '/ \'         Make                   ");
			System.out.println("      u      r		         '/_ _\'            The                ");
			System.out.println("       s    e   M           '/\' '/\'             Right            ");
			System.out.println("        t  m     e u       '/'_\'/_'\'              Decision       ");
			System.out.println("         0        n     Warren Golden Bank                         ");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("                  |       Choose an Option:    |                   ");
			System.out.println("                  |                            |                   ");
			System.out.println("                  |      1.Create Account      |                   ");
			System.out.println("                  |                            |                   ");
			System.out.println("                  |      2.View Accounts       |                   ");
			System.out.println("                  |                            |                   ");
			System.out.println("                  |      3.Withdraw            |                   ");	
			System.out.println("                  |                            |                   ");
			System.out.println("                  |      4.Deposit             |                   ");
			System.out.println("                  |                            |                   ");
			System.out.println("                  |      5.Delete Account      |                   ");
			System.out.println("                  |                            |                   ");
			System.out.println("                  |      6.Quit                |                   ");	
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");			
			choice=validateInteger(in);
			switch (choice) {
			case 1:
				createAccount(theUser,in);
				break;
			case 2:
				viewAccounts(theUser,in);
				break;
			case 3:
				withdrawBank(theUser, in);
				break;
			case 4:
				depositBank(theUser, in);
				break;
			case 5:
				deleteBankAccount(theUser,in);
				break;
			case 6:
				log.info("The user: "+theUser.getUsername()+" logged out");
				quit=true;
				break;
			default:
				System.out.println("Please enter a valid choice");	
			}	
		}
	}
	
//////////////////////////////////////////////////////////////////////////////////////////
	//the UI for the admin menu
	public static void AdminUI(Users theUser, Scanner in) throws InvalidClassException,SQLException {
		Integer choice=null;
		boolean quit=false;
		
		while(!quit) {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("    A        R               '/ \'        M                        ");
			System.out.println("     D      T A	            '/_ _\'         E                      ");
			System.out.println("      M    S   T           '/\' '/\'          N            		   ");
			System.out.println("       I  I     I N       '/'_\'/_'\'           U				   ");
			System.out.println("        N        O     Warren Golden Bank                      	   ");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("                  |       Choose an Option:    |                   ");
			System.out.println("                  |                            |                   ");
			System.out.println("                  |      1.View Accounts       |                   ");
			System.out.println("                  |                            |                   ");
			System.out.println("                  |      2.Create User         |                   ");
			System.out.println("                  |                            |                   ");
			System.out.println("                  |      3.Update User         |                   ");	
			System.out.println("                  |                            |                   ");
			System.out.println("                  |      4.Delete User         |                   ");
			System.out.println("                  |                            |                   ");
			System.out.println("                  |      5.Withdraw            |                   ");
			System.out.println("                  |                            |                   ");
			System.out.println("                  |      6.Deposit             |                   ");
			System.out.println("                  |                            |                   ");
			System.out.println("                  |      7.Delete Account      |                   ");
			System.out.println("                  |                            |                   ");
			System.out.println("                  |      8.Quit                |                   ");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");	
			choice=validateInteger(in);
			switch(choice) {
			case 1:
				viewAccounts(theUser,in);
				break;
			case 2:
				registerUser(in, true);
				break;
			case 3:
				userUpdate(theUser,in);
				break;
			case 4:
				deleteUser(theUser,in);
				break;
			case 5:
				withdrawBank(theUser, in);
				break;
			case 6:
				depositBank(theUser, in);
				break;
			case 7:
				deleteBankAccount(theUser,in);
				break;
			case 8:
				log.info("The user: "+theUser.getUsername()+" logged out");
				quit=true;
			default:
				System.out.println("Please make a valid choice");
			}
		}		
	}
	
//////////////////////////////////////////////////////////////////////////////////////////
	//validation method for integers
	public static Integer validateInteger(Scanner in) {
		Integer out= null;
		while(out==null) {
			try {//if out cannot be parsed to an int throw an exception
				out=Integer.parseInt(in.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Incorrect Input, please try again");
				out=null;
				
			}
		}return out;
	}
	
////////////////////////////////////////////////////////////////////////////////////	
	//validation method for integers 
	public static Integer validateInteger(String s, Scanner in) {
		Integer out= null;
		while(out==null) {
			try {//if out cannot be parsed to an int throw an exception
				out=Integer.parseInt(s);
			} catch (NumberFormatException e) {
				System.out.println("Incorrect Input, please try again");
				out=null;
			}
		}
		return out;		
	}	
	
////////////////////////////////////////////////////////////////////////////////////	
	//validation method for doubles
	public static Double validateDouble(Scanner in) {
		Double out= null;
		while(out==null) {
			try {//if it can't be parsed to a double, throw an exception
				out=Double.parseDouble(in.nextLine());
				}
			catch (NumberFormatException e) {
				System.out.println("Incorrect Input, please try again");
				out=null;
			}
		}
		return out;
	}

}
