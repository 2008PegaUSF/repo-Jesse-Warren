import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;
//the employee class

public class Employee {
	String name;
	int age;
	String department;
	//variables
	//the first constructor
	public Employee(){
		String name;
		int age;
		String department;
	}
	//the parameterized constructor
	public Employee(String name, int age,String department) {
		this.name=name;
		this.age=age;
		this.department=department;
	}
	//the toString method for printing the employees
	public String toString() {
		return "Employee - Name:" +name+ ", Age:" +age+ ", Department: "+department;
		
	}

}
