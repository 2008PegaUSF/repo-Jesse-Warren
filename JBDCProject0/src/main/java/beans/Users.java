package beans;

public class Users {
	private int UserId;
	private String username;
	private String password;
	private String name;
	private int age;
	private int usertype;
	
	public Users (int userId,String username,String password, String name, int age, int type) {
		this.UserId=userId;
		this.username=username;
		this.password=password;
		this.name=name;
		this.age=age;
		this.usertype=type;
		
	}
	
	public Users() {
	}

	public int getUserId() {
		return UserId;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
	
	public int getType() {
		return usertype;
	}
	
	public void setUsername(String uname) {
		this.username=uname;
	}
	
	public String toString() {
		String accountType = "";
				switch(this.usertype) {
				case 1: 
					accountType="Customer";
					break;
				case 2:
					accountType="Admin";
					break;
				}
			return "User[Username: " +this.username+" Type: " +accountType+"]";
			
				}
		
	public boolean equals (Users other) {
		return this.UserId==other.getUserId() && this.getUsername().equals(other.getUsername()) && this.password.equals(other.getPassword())&& this.name==other.getName() && this.usertype==other.getType(); 
	
	}
	
	

}
