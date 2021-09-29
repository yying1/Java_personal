import java.util.Scanner;

//Week 3: Data types and program flow

public class Class4_20210913 {

	public static void main(String[] args) {
		// operators: +-*/%
		int x = 0;
		int y = 0;
		y = x++; //assign before addition
		System.out.println(x);
		y = ++x; //assign after addition
		System.out.printf("%d %n",y); //%d for int and long, %f for double & float, %n for newline, %t for tab 
		
		// double precision issue
		double z = 8.3;	//convert decimal to binary: 8.3 becomes 1000.010011.. infinite. Not a Java issue. Precision
						// Java uses bigdecimal
		
		//int,float precision issue
		int a = 16777216; //int & float are both 4 bytes, a = 2^24
		float b = a; //float chop off the last bit
		System.out.println(b);
		
		//string
		String c = "hello";//Immutable, heap memory
			//c = "hi";//point to a new memory location
		String d = "hello";
		String e = new String("hello"); //not using the string pool
		String f = new String("hello");
		System.out.println(d==c);//return true because of shared memory, == compares memory location
		System.out.println(e==f);//return false because both uses separate memory
		
		String g = "Hello";
		String h = " World";
		String i = "Hello World";
		String j = g+h;
		System.out.println(i == j);//false because jvm will not optimize this, try to avoid == for string comparison
		
		//Scanner & Regex
			//input.nextline() Vs. input.next: first uses newline as delimiter, second uses space/tab/newline
			// use nextline() to clear buffer of input
		String k = "abc def";
		Scanner tokens = new Scanner(k);//tokenizer and regex
		while (tokens.hasNext()) {
			if (tokens.next().matches("[0-9]+")) {
				System.out.println("");
			}
		}
	}

}
