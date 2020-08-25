import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;


public class getPrint {
	public static void main(String[] args) throws IOException{
//calling the read file method
		readFile();
	

}

	public static boolean readFile() throws IOException{
	BufferedReader read = new BufferedReader(new FileReader("data"));
	boolean u=true;
	String l;
//while there is lines to read, split the line into a string and print it
while((l=read.readLine())!=null){
	String t[]=l.split(":");
	System.out.println("Name: " +t[0] +" "+ t[1]);
	System.out.println("Age: " +t[2] +" years");
	System.out.println("State: " +t[3] +" State");
	System.out.println("");
	
	
}read.close();return u;

}
}
