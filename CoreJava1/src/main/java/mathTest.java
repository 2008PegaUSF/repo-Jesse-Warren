
public class mathTest {
	public static void main(String[] args) {
		//hardcoding the operands
		int x = 100;
		int y =	10;
		//creating the instance of the class implementing the interface
    mathClass M1 = new mathClass();
    //calling the methods
   M1.getAdd(x, y);
   M1.getSub(x, y);
   M1.getMult(x, y);
   M1.getDiv(x, y);
   
	}

}
