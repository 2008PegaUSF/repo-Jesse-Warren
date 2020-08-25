import java.util.Comparator;

public class SortEmployee implements Comparator<Employee> {
	//comparing the value of name in one object to the other using the comparator
	public int compare(Employee one, Employee second) {
		return one.name.compareTo(second.name);		
	}
	
	}
	

