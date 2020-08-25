
public class runAbstract extends childClass {
;

	public static void main(String[] args) {
		
		//creating the child class object
		childClass run=new childClass();
		//setting up an if else statement for capitols
		if (run.getCap("hello World")) {
			System.out.println("There are capitols");
		}else {
			System.out.println("There are no capitols");
		}
		//calling the method to convert all of the characters to uppercase
		run.getUpper("hello world");
		//convert the string to integer and then adding ten
		run.getConvert("4938");
		
		}

	}


