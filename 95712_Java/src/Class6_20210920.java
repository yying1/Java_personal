import java.util.Arrays;

//Week #4: Classes and Objects

public class Class6_20210920 {
	// Null pointer exception when call on methods of reference data type without initialization
	
	// For each loop for arrays and collections:
		//1. only to read
		//2. forward iteration
		//3. only one array or collection
	
	public static void for_demo() {
		int[] numbers = {10,11,12,13,14};
		for (int i: numbers) {
		i = i+1; //this will makes a copy of the item in array
		System.out.printf("%d ", i); //this will shows +1
		}
		System.out.println();
		for (int i: numbers) {
			System.out.printf("%d ", i);//however, the original array does not change
			}
		//here is how to update array
		for (int i = 0; i < numbers.length; i++) {
		numbers[i]++;
		}
		System.out.println();
		for (int i: numbers) {
		System.out.printf("%d ", i);
		}
	}
	
	//Java has 4 types of memory area: Method area(S), PC register(U), Heap(S), Stack(U). U-unique among threads, S-Shared
	
	//Variables: member variables, instance variables
	String test; //This is a member variables that can be used by all methods
	public static void variable() {
		int page_n = 1; // this is a instance variables
		Class6_20210920 session = new Class6_20210920(); //create a stackframe/instance for session
		session.test = "This is a test"; //create heap memory with member variables
		System.out.println(session.test);
		session.for_demo();//create a stackframe for_demo()
	}
	
	//primitive variables in methods will be passed to others as copies, so changes is not connected
	//reference variables in methods will be sharing the same heap memory, so changes can occur
	public static void somemethod (int[] y, int y1 ) {
		y[0]++;
		y1++;
	}
	
	public static void someothermethod (int[] y) {
		int[] z = {5,6,7};
		y = z;
	}
	
	public static void someothermethod1 (String a) {
		a = "changed";
	}
	
	public static void someothermethod2 (String[] b) {
		Arrays.sort(b);
	}
	
	//Reference Data Types
	//direct change in reference data type will reference the same memory as the orginal variable, so changes occur
	//primitive variables will not change across methods (unless its member variable under the class)
	//if reference data is pointed with a new reference in method, original does not change
	//String assignment across method will also not change the original value. This is like reassigning reference data
	//but sort the string will change the original
	//https://www.programcreek.com/2013/09/string-is-passed-by-reference-in-java/
	public static void variable2() {
		int[] x = {1,2,3};
		int x1 = 1;
		somemethod(x,x1); //passing both types of variables
		System.out.println(x[0]); // reference variable as y can reference the same memory as x pointed to
		System.out.println(x1);//primitive variables will not change across methods (unless its member variable under the class)
		someothermethod(x);
		System.out.println(x[2]);// will not change as well
		String a = "same";
		someothermethod1(a);
		System.out.println(a);// String is a reference data type, so change in someothermethod1 (create new memory) will not affect a in varaible2
		String[] b = {"b","a","c"};
		someothermethod2(b);
		System.out.println(Arrays.toString(b));// Array sort will actually modify the original reference data
	}
	
	//method overloading
	//Java identify methods by complete characters, including names and parameter name & data types
	// if same methods happens, Java will directly throw compile error
	
	//packages: use Ctrl+Shift+O to clean un-used packages
	// must import packages to use classes that are not in the current package 
	
	
	//.txt or other data files, keep in project folder not in packages
	
	public static void main(String[] args) {
		variable2();

	}

}
