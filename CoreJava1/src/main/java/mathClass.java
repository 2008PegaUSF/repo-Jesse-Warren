//implementing the interface
public class mathClass implements mathInter {
	
	//the addition method
	public int getAdd(int x, int y) {
		int z = x + y;
		System.out.println("The sum is "+z);
		
		return z;		
	}
	//the subtraction method
	public int getSub(int x, int y) {
		int z = x - y;
		System.out.println("The difference is "+z);
		return z;		
	}
	//the multiplication method
	public int getMult(int x, int y) {
		int z = x * y;
		System.out.println("The product is "+z);
		return z;		
	}
	//the division method
	public int getDiv(int x, int y) {
		int z = x/y;
		System.out.println("The remainder is "+z);
		return z;		
	}


}
