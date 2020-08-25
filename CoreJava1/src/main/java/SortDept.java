import java.util.Comparator;
public class SortDept implements Comparator<Employee>{
	//comparing the value of department against one object to the other
	public int compare(Employee one, Employee second) {
		return one.department.compareTo(second.department);
	}

}
