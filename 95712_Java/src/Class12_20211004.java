//Interfaces

public class Class12_20211004 extends auto implements flyable,flyable1 {

	//Display class signatures and capabilities above inheritance
	//Interface use blank arrow with dash line in UML diagram
	//Interface class cannot be abstract, because it will lead to multiple inheritance
	//If abstract class implement a interface, then it does not have to take all of interface'method
	//if concrete class implement a interface, then it must implement all methods
	//if implement an interface, it has to be public
	
	//inheritance Vs. interface 
	//inheritance children must be able to do parent's methods
	//if not, make that method class interface 
	
	//inheritance among interfaces
	
	//What methods need to be implemented?
	//inheritance from concrete class methods don't need to be implemented, can directly use it
	//inheritance from abstract class methods must be implemented
	//interface with inheritance, then concrete class must implement both methods
	//interface to abstract class don't need to implement methods
	//inheritance from interface to interface does not need to implement inheritance methods
	
	//static and default methods can be added into interface without breaking implement classes
	//however, must use interface class to invoke static method because it only have one memory copy
	
	//Arrays.sort(##) ## must implement comparable methods (such as string array), sort is static method
	void drive() {
		System.out.println("Class 12 can drive");
	}
	
	public void fly() { //implemented methods from interface must be public, can can only use once from either interfaces
		System.out.println("Class 12 can fly");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		auto [] machines = new auto[2];
		machines[0] = new car();
		machines[1] = new aircraft();
		
		for (auto a:machines) {
			if (a instanceof flyable) { // use instanceof interface to determine methods
				((flyable) a).fly();
			}
		}
		
	}

}

class jet implements Comparable {

	@Override
	public int compareTo(Object o) {
		// Must return int
		// be caution with smaller difference when comparing numbers
		// return -1(negative result), +1(positive result), 0(same) 
		// Strings compareto
		return 0;
	}
	
}

class car extends auto {
	void drive() {
		System.out.println("drive car!");
	}
}

class aircraft extends auto implements flyable {
	void drive() {
		System.out.println("drive aircraft!");
	}
	
	public void fly() {
		System.out.println("fly aircraft!");
	}
	
}

class auto extends auto1 {
	void drive() {
		
	}
	void drive2() {
		
	}
}

abstract class auto1 {
	abstract void drive2();
}

interface flyable extends flyable1 { //interface need no declaration, its default public and abstract
	void fly(); // interface can inherit as well 
	default void stop() {
		System.out.println("Stop!");
	}
	
	static void turn() {
		System.out.println("Turn!");
	}
}

interface flyable1{//does not matter if multiple interface have the same methods, it will be implement only once in the class
	void fly();
}