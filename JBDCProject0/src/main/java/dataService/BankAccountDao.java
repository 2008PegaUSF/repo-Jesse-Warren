package dataService;
import java.util.*;
import beans.BankAccount;
import java.sql.SQLException;

//the bank account data access object interface
public interface BankAccountDao {
//the empty methods for the interface implementation	
public List<BankAccount> getBankAccounts() throws SQLException;
public List<BankAccount> getBankAccountsById(int id) throws SQLException;
public BankAccount getBankAccount(int id) throws SQLException;
public void withdraw(int id, double amount) throws SQLException;
public void deposit(int id, double amount) throws SQLException;
public void createAccount(int userid) throws SQLException;
public void deleteAccount(int id) throws SQLException;
}
