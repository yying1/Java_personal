import java.lang.reflect.InvocationTargetException;

//2021-11-29: Reflection
//Final: 3 hours, similar to Wednesday's lab. First submit part 1 and then work on part 2
//Focus on topics with *

public class Class25_20211129 {
	//Reflection Meaning: 1. Mirror 2.Introspection (use this)
	//Talking to runtime JVM for metadata
	//Library built by IBM. Used in highly specialized situation
	
	//The Class class: metadata about class in JVM
	public static void main(String[] args) {
		try {
			Class<?> c = Class.forName("java.lang.String");
			System.out.println(c.getName());
			
			Object o = c.getConstructor().newInstance();
			System.out.println(o.getClass().getName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Class25_20211129 c1 = new Class25_20211129();
		c1.getwhoami();	
		
		//getdeclaredConstructor Vs. getConstructor
		//encapsulation constraint, first will return all constructors
	}
	void getwhoami() {
		System.out.println(this.getClass().getName());
		//getsimplename will return just the class name
	}
	
	//Use reflection to call the same methods from multiple classes/libraries
	// object --> getClass --> getMethod --> invoke
	
	//Multi-threading, JUNIT test cases are independent as different threads
	//Junit used reflection to get class and test run
	
	//Reflection used in tools and IDEs: pop-up window uses reflection
	
	//Serialization
	//create binary files to store class state and pass to other classes
	//Google gson library .fromJson method uses reflection to pick up class data from binrary file
}

