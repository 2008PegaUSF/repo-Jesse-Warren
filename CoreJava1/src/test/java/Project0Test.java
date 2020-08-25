//Jesse Warren
//Revature Pega USF 
//08/23/2020

import float2package.float2;

import java.io.IOException;
import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class Project0Test extends CoreProject1{
	
	CoreProject1  cp= new CoreProject1();
	
//1.	
	
	@Test 
	void testBub(){
		
	int[] testArr=new int[] {0,1,2,3,3,4,5,6,7,8,9};	
	Assertions.assertEquals(testArr,cp.bubbleSort(testArr));
	
	}
//2
	
	@Test 
	void testFib() {
		
		int[] fibArr= new int[25];
		fibArr[0]=0;
		fibArr[1]=1;
			//a for loop in order to obtain and print the numbers
			for (int c=2; c<25;c++){				
				fibArr[c]= fibArr[c-1] + fibArr[c-2];				
			}
			
			for(int y=0;y<25;y++) {
			System.out.println(fibArr[y]);}
			
	Assertions.assertArrayEquals(fibArr, cp.getFib());
		
		
		
	}
//3	
	
	@Test
	void testRev() {
		
		String f="Put it in Reverse Terry";
		String l ="yrreT esreveR ni ti tuP";
				Assertions.assertEquals(l,cp.getReverse(f));
		
		
	}
//4
	@Test
	void testFac() {
		
		Assertions.assertEquals(120,cp.getFac(5));	
		
	}
//5
	@Test
	void testStr(){
		
		String f="Jesse";
		Assertions.assertEquals(f,cp.strMani("Jesse Warren",5));
	}
//6
	@Test
	void testEven(){
		
		boolean f=true;
		Assertions.assertEquals(f,CoreProject1.areYouEven(2));
		
		
		
	}
//7 
	
	@Test
	void testComp() {
		Employee one= new Employee("Jesse",27,"Fisherman");
		Employee two= new Employee("Mark",26,"Fisherman");
	SortAge Ep= new SortAge();
	Assertions.assertEquals(1,Ep.compare(one, two));
	
	
	}
	
	
	
	
//8
	@Test
	void testPalin(){
		
		String s="did";
		boolean t=true;
		Assertions.assertEquals(t,CoreProject1.isPalindrome(s));
		
		
	}
	
//9
	@Test  
	void testPList(){
		
		boolean t=true;
		Assertions.assertEquals(t,cp.isPrime(7));
		
		
	}
	
//10
	@Test
	void testMin(){
		
		Assertions.assertEquals(2,cp.getMin(2,3));
		
		
	}
	
//11
	
	@Test
	void testFloat(){
		
	float2 f1=new float2();
	Assertions.assertEquals(5,f1.getFl());
	
}
	

	
	
//12
	@Test 
	void testEvenArr(){
		int[] testArr=new int[100];
		for (int i = 1; i<=100; i++) {
			testArr[i-1]=i;				
		}
		Assertions.assertArrayEquals(testArr,cp.getEvenArr(testArr));
		
		
		
	}
	
//13 
	@Test
	 void testTri(){
		int b = 0;
		Assertions.assertEquals(b,cp.getTri());		
		
		
	}
	
//14
	@Test
	void testSwitch(){
		boolean t=true;
		Assertions.assertEquals(t, cp.getSwitch());		
		
	}
//15
	@Test 
	void testInterface() {
		mathClass M1 = new mathClass();
		Assertions.assertEquals(4,M1.getAdd(2,2));	
		
	}
//16
	@Test
	void testILength() {
		String[] s=new String[]{"Hello"};
		iLength.main(s);
		
		Assertions.assertEquals(5,iLength.value);
		
		
	}

//17
	@Test 
	 void testInterest(){
		double s=2.50;
		Assertions.assertEquals(s,cp.getInter(5,10,5));
		

		
		
	}
	
//18
	@Test 
	void testAbs() {
		boolean t=true;
		childClass c=new childClass();
		Assertions.assertEquals(t, c.getCap("Hello"));
	}
	
	
//19
	@Test
	 void testAListFun(){
		ArrayList<Integer> testArr=new ArrayList<Integer>();
		testArr.add(1);
		testArr.add(4);
		testArr.add(6);
		testArr.add(8);
		testArr.add(9);
		testArr.add(10);
		
		Assertions.assertEquals(testArr, cp.getArrListNumbers());		
		
	}
	
	
//20
	@Test 
	void testPrint() throws IOException {
		getPrint p=new getPrint();
		boolean t=true;
		Assertions.assertEquals(t,getPrint.readFile());
	}
	
	
	

}
