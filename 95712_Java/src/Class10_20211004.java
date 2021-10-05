//Encapsulation

/**
 * Junit test case is a good example of encapsulation, allowing an external package to access public-level data in the code
 * Composition Vs. Aggregation: white/black diamond on UML diagram. 1:1 relationship 
 * @author yingy
 */

import others.SavingAccount;

public class Class10_20211004 {
	
	//For package with in package, use package p1.p2 (give fully qualified name/path), or use p1.*
	//Use import with full path instead of * to ensure getting the precise class
	//member variables should be first set as private, change to others if needed
	//different packages prevent access across classes, must made public
	//getter method should normally be public, setter method as friendly allows access within the package (no notation)
	//protected give child class (inheritance) access to + friendly (protected include friendly)
	//class can never be protected (must be public and friendly), private has ruled out
	
	//static variable stores in class memory, use class name to access static variable not object name to access
	public void main(String[] args) {
		// TODO Auto-generated method stub
		others.SavingAccount sa = new SavingAccount();
		//sa.interest = 0.05; // this will not work since interest is not available
		double newi = 0.05f;
		sa.changeinterest(newi);//this acts like a setter method to make edit on the interest variable
		System.out.println(sa.showinterest());//this acts like getter methods
		//System.out.println(sa.fakebalance);// cannot access friendly variable from different package
		//System.out.println(sa.fakebalance2);// cannot access protected variable from different package too
		
		Class10_20211004_SA sa1 = new Class10_20211004_SA();
		System.out.println(sa1.printbalance());//can access public method from child class that access protected method from parent
		System.out.println(sa1.fakebalance3);//can access friendly variable in the same package
	}

}


