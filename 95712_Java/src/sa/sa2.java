package sa;

public class sa2 {
	static boolean bdef;

	public static void main(String[] args) {
		
		//Primitive data types
		byte b = 127;
		short sh = 1;
		int i = 2;
		int ii = i; //will always make a copy of primitive value instead of referencing
		ii = 3;
		long l = 1L; //note that we can use either l/L to force represent long
		
		float f = 1f;
		double d = 1d;
		
		System.out.println(b);
		if (b < Byte.MAX_VALUE) { //use this to check the max value of a data type
			b++;
		}	
		System.out.println(b);//max value for byte is 127
		
		//Conversion
		long l2 = sh;//widening the type, convert a smaller to larger primitive data type
		short sh2 = (short)l;//narrowing the type, vice versa. Need to add casting
			// note that for narrowing with casting might lead to data lost (become 0) 
		
		//Math Operations
		double d2 = (double) ii/i; //be aware of division as you might lose value without casting
		double d3 = ii/i;//this will not produce the right answer. The result data-type of any mathematical operation in Java is the Widest of the data-type of its operands
		System.out.println(d2);
		System.out.println(d3);
		System.out.println(ii/i);
		System.out.println(Math.round(d2));//use Math method. Use Shift+F1 to explore method
			//Note that math methods are all static, meaning that you can call it directly from the method instead of creating a class
		
		//boolean operations
		boolean b1 = true;
		boolean b2 = !b1; //! is a negative method that reverse the value
		System.out.println(b1);
		System.out.println(b2);
		System.out.println(bdef);//default value is false
		int i1 =0;
		boolean b3 = (i1 != 0);//creating boolean from expression
		String sboolean = "true";
		boolean b4 = Boolean.parseBoolean(sboolean); //boolean parising from String
		
		//char
		char c1 = 'a'; //char using single quote, string uses double quote
		char dollarsign = '\u0024'; //also can use byte code
		char c2 = 'b';
		char c3 = 'c';
		System.out.print(Character.toUpperCase(c1));
		System.out.print(Character.toUpperCase(c2));
		System.out.println(Character.toUpperCase(c3));//there are character class methods that can be used
		
		// for statement
		
		//switch statement
		int i2 = 5;
		switch (i2) {
		case 1:System.out.println("Its one");
		case 5:System.out.println("Its five"); break;//don't forget to add break or it will continue to run the next case statement
		case 8:System.out.println("Its eight");
		}
		
		/* Classname:Javadoc
		 * Author:yying2
		 * Version:SA2
		 */
	
	}

}
