//Jesse Warren
//Revature Pega USF 
//08/23/2020



import java.util.*;

import java.time.*;


public class CoreProject1 {
	Scanner console=new Scanner(System.in);	
	//Question 1

	//call the sort and print method
	public int  [] getBubble(){
	int[] arr1=new int[]{1,0,5,6,3,2,3,7,9,8,4};
	bubbleSort(arr1);
	printBubble(arr1);
	return arr1;
	}

	//Sort Method
	public int[] bubbleSort(int[] arr) {
	int x= arr.length;
	//going through the array
	for (int i=0; i<x-1; i++) {
		for (int j=0; j<(x-i-1);j++) {
	//if one index is greater than the one in from of them
			if(arr[j]>arr[j+1]) {
				int temp = arr[j];
				arr[j]=arr[j+1];
	//swapping them
				arr[j+1]=temp;
				}
			}
		} return arr;
	}
	//Print Method
	public void printBubble(int[] arr2){
		for (int i=0; i<arr2.length;i++) {
				System.out.println("Bubble Sorted " +arr2[i]);

		}
	}
	//*********************************************************
	// Question 2
	public int[] getFib(){
		//setting up our variables
	int[] fibArr= new int[25];
	fibArr[0]=0;
	fibArr[1]=1;
		//a for loop in order to obtain and print the numbers
		for (int c=2; c<25;c++){				
			fibArr[c]= fibArr[c-1] + fibArr[c-2];				
		}
		
		for(int y=0;y<25;y++) {
		System.out.println(fibArr[y]);}
		return fibArr;
	}
	//**********************************************************
	// Question 3
	public String getReverse(String str){
		//creating and printing the original string
		System.out.println(str);
		//a loop setting string equal to the first char at string and constantly moving that char to to the back
		//effectively turning the string the opposite direction using addition.
	 	for(int i = 0; i<str.length();i++){
			str=str.substring(1, str.length()-i)+str.charAt(0)+str.substring(str.length()-i);
		}
		System.out.println(str);
		return str;

	}








	//**********************************************************
	// Question 4
	public int getFac(int n){
		//the number used to bounce int n against to find the factorials.
	int f=1;
		//the for loop used to list the variables
		for(int e=1;e<=n;e++){
				f=f*e;}
				System.out.println("Factorial of "+n+" is " +f);
				return f;
		}
	//**********************************************************
	// Question 5
	public String strMani(String s, int idx){
		String temp="";
		// for each charat until our index number is met, taking the number and adding it to a new string
		for(int i=0;i<idx;i++){
			temp=temp+s.charAt(i);
		}
		return temp;
	}




	//**********************************************************
	// Question 6
	//a boolean  to ask if a passed parameter is even or odd
	static boolean areYouEven(int n){
		boolean areYouEven=true;
		for(int x=1; x<=n;x++)
		areYouEven = !areYouEven;
		return areYouEven;
	}
	//a simple statement displaying whether its even or odd
	public void canYouEven(int j){
		if(areYouEven(j)){
			System.out.println("You can even with the number " +j);}
		else
			{System.out.println("You can't even with the number " +j);}
	}
	//***********************************************************
	// Question 7
	//Sort Two Employees 
	
	
	
	
	
	
	
	
	
	
	//***********************************************************
	// Question 8
	//Write an array list and then move the Palindromes to their own arraylist
	
	public ArrayList<String> getPalin() {
		//creating the two arraylists
		ArrayList<String> rList= new ArrayList();
		ArrayList<String> pList= new ArrayList();
		//populating the first arraylist
		rList.add("karan");
		rList.add("tom");
		rList.add("civic");
		rList.add("radar");
		rList.add("jimmy");
		rList.add("kayak");
		rList.add("john");
		rList.add("refer");
		rList.add("billy");
		rList.add("did");
		//printing it for validity 
		System.out.println(rList);
		//iterating through list 1, passing the value to the palindrome check, if it is one,
		//storing the value in the second arraylist
		for (int i = 0;i<rList.size();i++) {
			if(isPalindrome((String)rList.get(i))){
				pList.add((String)rList.get(i));
			}
			
				
			}
		//printing the second list
		System.out.println(pList);
		return pList;
		}	
		
		
	
	public static boolean isPalindrome(String s) {
		String r="";
		for(int i =s.length()-1; i>=0;i--) {
			r=r+s.charAt(i);
		}
		return s.equals(r);
	}
	//**************************************************************
	//Question 9
		public ArrayList<Integer> getPrimeLists(){
			ArrayList<Integer> theList= new ArrayList();
			//creating and populating the arraylist
			ArrayList<Integer> primelist=new ArrayList();
			for (int j=1;j<=100;j++){
				primelist.add(j);
			}
		//iterating through the list while handing the indexes off to the isPrime method
		//then printing them out if they're prime and doing nothing if they aren't
			for(int i=0;i<100;i++){
				if(isPrime(primelist.get(i))){
					System.out.println(primelist.get(i)+ " is a prime from the list");
					theList.add(i);}
					else{}
				}return theList;
		}
		//if statements and a for loop to check for the qualities of a prime number.
		public boolean isPrime(int n){
			if (n<2) {return false;}
			for (int i=2; i<=n/2; i++){
				if (n%i==0)
				{return false;}
			}return true;
		}

	//***********************************************************
	//Question 10
	public int getMin(int a, int b) {
		//the containing variable
		int result;
		//setting the containing variable to the ternary statement
		result= a <= b? a :b;
		//printing the result
		System.out.println("The smallest number is :" +result);
		return result;
	}
	
	//***********************************************************
	//Question 12
	
	public int[] getEvenArr(int[] evenArr) {
		//creating the array
		//populating the array
		for (int i = 1; i<=100; i++) {
			evenArr[i-1]=i;				
		}
		//iterating the array and if the modulus of 2 and the number is 0 
		//it is even and printing the number
		for (int value : evenArr) {
			if (value%2==0) {System.out.println("The number " +value+" is even.");}
			
		}
		return evenArr;
		
	}


	//***********************************************************
	// Question 13
	public int getTri(){
		//setting the variable for containing the int
		int a=0;
		//the outer loop for controlling the size of the rows
		for (int counter=0; counter<=4;counter++)
			{System.out.println("");
		//the rows for controlling the ints in the columns
			for (int i=0;i<counter;i++){
				if (a==0){System.out.print(a+" "); a=1;}
				else{System.out.print(a+" "); a=0;}		}

			}
		return a;
		}
	//***********************************************************
	// Question 14
	public boolean getSwitch() {
		
		boolean f=false;
		System.out.println("");//pretting up the console display
		int c;
		//asking the hard questions
		System.out.println("Enter a choice 1.Find the square root 2.Display the date 3. Split a string and store it in an array");
		//setting the variable to the choice for the hard questions
		c=console.nextInt();
		//setting the switch case
		switch(c) {
		case 1://entering a number then using the sqrt method to obtain the sqroot
			System.out.println("Enter a number");
			int x=console.nextInt();
			double sqrRoot=Math.sqrt(x);
			System.out.println(x+ "'s square root is " +sqrRoot);
			f=true;
			break;
		case 2://printing the current date
			LocalDateTime date = LocalDateTime.now();
			System.out.println(date.toString());
			f=true;
			break;
		case 3://spliting the string by char and assign each of those to an index, printing the array
			String s="I am learning Core Java";
			String[] sArr=s.split("");
			for (int i=0; i<sArr.length;i++) {
				System.out.println(sArr[i]);
				f=true;
			}
		} return f;
	}
	
	

	
	
	
	//***********************************************************
	// Question 17
	public void getInterested(){
		//just tidying up the console display.
		System.out.println("");
		//Asking the real questions
	System.out.println("What is the principal?");
		double principle=console.nextDouble();

	System.out.println("What is the rate?");
		double rate=console.nextDouble();

	System.out.println("What is the time in years?");
		double time=console.nextDouble();
		getInter(principle,rate,time);
		
	}
	
	public double getInter(double p, double r, double t) {
	//calculating the interest

		double simpleinterest=(p*r*t)/100;

	//notifying the customer.

		System.out.println("The simple interest for the loan is "+String.format("%,.2f",simpleinterest));
		return simpleinterest;
		}


	//************************************************************
	





	//************************************************************
	// Question 19
	//The list creation method and method driver
	public ArrayList<Integer> getArrListNumbers(){
		ArrayList<Integer> list= new ArrayList<Integer>();
		for (int i=1;i<=10;i++){
			list.add(i);}
			System.out.println(list);
			addeArrList(list);
			addoArrList(list);
			removePrimeArrList(list);
			System.out.println(list);
			return list;

	}
	//iterating through the arraylist and if a particular index is even
	//assigning it to be added to a cumulative variable
	public int addeArrList(ArrayList<Integer> arrList1){
	int a;
	int b=0;
	for (int i = 0; i<arrList1.size(); i++){
		if (arrList1.get(i)%2==0){a=arrList1.get(i);
		b=a+b;}
		else{}
		}
	System.out.println("The sum of the evens is " +b);
	return b;

	}

	//iterating through the arraylist and if a particular index is odd
	//assigning it to be added to a cumulative variable
	public int addoArrList(ArrayList<Integer> arrList1){
	int a;
	int b=0;
	for (int i = 0; i<arrList1.size(); i++){
		if (arrList1.get(i)%2 != 0){a=arrList1.get(i);
		b=a+b;}
		else{}
		}
	System.out.println("The sum of the odds is " +b);
	return b;

	}
	//iterating through the arraylist and if a number isn't prime doing nothing, and if its prime,
	//removing the number at the index and starting the iteration over again.
	public void removePrimeArrList(ArrayList<Integer> arrList1){
	for (int i = 0; i<arrList1.size(); i++){
		if (!isPrime(arrList1.get(i))){}
		else{arrList1.remove(i); i=0;}
	}
	}
	
	//************************************************************
	//Question 20
	
	
	


	}



