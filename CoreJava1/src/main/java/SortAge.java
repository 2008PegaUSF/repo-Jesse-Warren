import java.util.Comparator;
public class SortAge implements Comparator<Employee>{	
	//utilizing the compare method on the age variable
	public int compare(Employee one, Employee second) {
	return one.age-second.age;
}


}
