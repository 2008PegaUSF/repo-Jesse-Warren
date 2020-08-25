// extending the abstract parent class
public class childClass extends parentAbstract{

	//the method to check if a character in a string is capital
	public boolean getCap(String s) {
		//iterating through the string
		for (int i=0; i<s.length(); i++) {
			char c = s.charAt(i);
			//checking if the character at index is upper
			if (Character.isUpperCase(c)) {
				return true;
			}
		}
	
		return false;		
	}	

	@Override
	public String getUpper(String s) {
		//making a string buffer object
		StringBuffer nStr=new StringBuffer(s);
		//changing the lowers to upper
		for (int i= 0; i<s.length(); i++) {
			if(Character.isLowerCase(s.charAt(i))) {
				nStr.setCharAt(i, Character.toUpperCase(s.charAt(i)));
			}
		}
		//printing the string
		System.out.println(nStr);
		return s;
	}

	@Override
	public int getConvert(String s) {
		//converting the string to int and storing it
	 int numHold=Integer.parseInt(s);
	 	//adding ten
		numHold+=10;
		//displaying the number
		System.out.println(numHold);
		return numHold;
	}

}
