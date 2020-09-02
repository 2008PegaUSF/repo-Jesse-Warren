package dataService;
import java.io.*;
import java.sql.*;
import java.util.*;

public class ConnFactory {
	private static ConnFactory cf=new ConnFactory();
	
	private ConnFactory() {
		super();
	}
	
	public static synchronized ConnFactory getConnected() {
		if (cf==null) {
			cf = new ConnFactory();
		}
		return cf;
		}
	public Connection getConnect() {
		Connection conn= null;
		Properties prop= new Properties();
		try {
			prop.load(new
					FileReader("database.properties"));
			conn=DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user"),
					prop.getProperty("password"));
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}return conn;
	}
	
	

}
