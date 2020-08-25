import java.util.ArrayList;
import java.util.Collections;

public class employeeRunner{

	public static void main(String[] args) {
		//creating the new object employee
		Employee sort=new Employee();
		//creating an arraylist of employee
		ArrayList<Employee>eList= new ArrayList<Employee>();
		//adding our employees
		eList.add(new Employee("Jesse",27,"Programming"));
		eList.add(new Employee("Shea",28,"Bartending"));
		eList.add(new Employee("Tori",26,"Framework"));
		//printing the array
		System.out.println("unsorted list");
		
		for (int i=0; i<eList.size(); i++) {
			System.out.println(eList.get(i));
		}
		//sorting the array by name
		Collections.sort(eList, new SortEmployee());
		
		System.out.println("sorted by Name");
		
		for (int i=0; i<eList.size(); i++) {
			System.out.println(eList.get(i));
		}
		//sorting it by age
	Collections.sort(eList, new SortAge());
		
		System.out.println("sorted by Age");
		
		for (int i=0; i<eList.size(); i++) {
			System.out.println(eList.get(i));
		}
		//sorting it by department
	Collections.sort(eList, new SortDept());
		
		System.out.println("sorted by Department");
		
		for (int i=0; i<eList.size(); i++) {
			System.out.println(eList.get(i));
		}
		
		
		
		

		
	
	}

}
