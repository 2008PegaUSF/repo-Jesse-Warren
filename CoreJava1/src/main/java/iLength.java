
public class iLength {
	public static int value;
	public static void main(String[] args) 	{
		{
			//if the argument length is greater than zero, print the value by adding the length to value;
		if(args.length > 0) {
			for (int i=0;i<args.length;i++) {
			System.out.println("Number of characters in the string is " +args[0].length());
			value+=args[i].length();}
			// if not? theres no arguments
		}else {System.out.println("There is no command line arguments");
			}
		} 
	}

}
