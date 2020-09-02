package dataService;
import java.util.List;
import java.sql.SQLException;
import beans.Users;
//the Users Data Access Object Interface
public interface UsersDao {
	//The empty methods for the implementation
	public List<Users> getUsers() throws SQLException;
	public List<Users> getAccountUser(int bankid) throws SQLException;
	public Users getUserByName(String uname) throws SQLException;	
	public void changeUsername(String curName,String newName) throws SQLException;
	public void changePassword(String uname, String pword) throws SQLException;
	public void changeName(String uname,String name)throws SQLException;
	public void changeAge(String uname, int age) throws SQLException;
	public void createCustomer(String uname, String pword, String name, int age) throws SQLException;
	public void deleteUser(String Uname) throws SQLException;

}
