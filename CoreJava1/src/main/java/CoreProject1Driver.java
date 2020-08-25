//Jesse Warren
//Revature Pega USF 
//08/23/2020

import java.io.IOException;
import java.util.*;
import float1package.float1;

public class CoreProject1Driver extends CoreProject1 {
	public static void main(String[] args) throws IOException {
		Scanner console=new Scanner(System.in);
		CoreProject1 P1= new CoreProject1();
//Question 1-Bubble Sort
		P1.getBubble();
		
//Question 2-The First 25 Fibonacci Numbers Starting with Zero
		P1.getFib();
		
//Question 3-Reverse a String with No Temporary Variable
		P1.getReverse("Put it in Reverse Terry");
		
//Question 4-Compute the Factorial of N
		P1.getFac(5);
		
//Question 5-
		System.out.println(P1.strMani("JesseWarren",5));
		
//Question 6-Determine if a Number is Even Without Modulus
		
		P1.canYouEven(7);
		P1.canYouEven(8);
		
//Question 7-Sort two Employees
		
		employeeRunner r=new employeeRunner();
		employeeRunner.main(null);

		
//Question 8-Take the palindromes from an arraylist
		P1.getPalin();


//Question 9-Create and Arraylist 1-100, Find the Primes
		P1.getPrimeLists();
		
//Question 10- Find the minimum of two numbers using ternary numbers
		P1.getMin(3,2);
		
//Question 11-2 Floats 2 Packages
		float1.main(null);		

//Question 12-Numbers 1-100 in an array print out all even numbers using enhanced for loop
		int[] arr= new int[100];
		P1.getEvenArr(arr);

//Question 13- Get the Triangle
		P1.getTri();
		
//Question 14-Make a switch case with functionalities
		P1.getSwitch();
		
//Question 15-Interface math
	    mathTest.main(null);
		
//Question 16-Find the number of characters using a command line argument
		iLength.main(args);
		
//Question 17- Calculate Simple Interest
		P1.getInterested();
		
//Question 19- Arraylist Magic
		P1.getArrListNumbers();
		
//Question 20- Print the employees from a file
		getPrint.readFile();
	
		
	}
}
