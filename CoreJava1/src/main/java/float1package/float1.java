package float1package;//creating the first float package
import float2package.float2;//calling the second package and the specific class
public class float1 {
	public static void main(String[] args) {
		float2  funWithFloats= new float2();//creating the the object from the second class and package
		System.out.println("Float One: " +funWithFloats.flt1);//calling the two public float variables
		System.out.println("Float two: " +funWithFloats.flt2);		
	}

}
