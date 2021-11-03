
// Week 5: Inheritance & Constructor


public class Class8_20210927 { // All classes are data types
	
	
	// supper() will invoke the parent constructor with default parameter
	
	public static void main(String[] args) {
		//Composition/aggregation: class has a pittstudent and cmustudent, and those two are inheritance from student
		pittstudent a = new pittstudent();
		a.give_name("Matt");
		System.out.println("\n"+"Print out for Inheritance");
		System.out.println(a.state+" "+a.college +" " +a.name);//child class but inherit method and variables from parent 
		
		cmustudent cs = new cmustudent();
		cs.give_name("Frank");
		System.out.println(cs.college +" " +cs.name);//child class but use its own override method 
		
		//Abstract Class Vs. Concrete Class with inheritance
			//course c = new course(); //This will not work since abstract class cannot use new
		cmucourse cmuc = new cmucourse(); // must instantiate concrete child class from abstract class
		System.out.println("\n"+"Print out for concrete class:");
		cmuc.set_start();
		System.out.println(cmuc.start);
		
		
		//Class with default constructor
		book b = new book();
		System.out.println("\n"+"Print out default constructor:");
		b.note(50);
		System.out.println(b.title + " "+b.author+" "+b.pages);
		
		ebook eb = new ebook();
		System.out.println("\n"+"Print out changed default constructor:");
		System.out.println(eb.title + " "+eb.author+" "+eb.pages);
		
		amzebook azeb = new amzebook("name","author",1000,"amazon");
		System.out.println("\n"+"Print out changed default constructor with super:");
		System.out.println(azeb.title + " "+azeb.author+" "+azeb.pages+" "+azeb.platform);
		
	}

}


//Inheritance is a design decision after careful review and not for in-flight design. "Signature"
// Uniform Interface to use classes that are related through inheritance, same vocab and same methods used by different ppl
// Modular Design
 class student{
	String name;
	String college;
	String state;
	public void give_name(String a) {
		name = a;
	}
}
 
 class pittstudent extends student {
	 String college = "Pitt";
 }
 
 class cmustudent extends student {
	String college;
	
	@Override
	// @Override will ensure developer to override the previous method, @Override will throw compile error
	public void give_name (String name) {
	this.college = "CMU";
	this.name = name;
	}
}
 
//Abstract Class vs. Concrete Class
// Abstract Class are there because design decision for child classes are not set but want to uniform them under the same format
// Cannot use new with abstract class, must use concrete child classes
 
 abstract class course{
	 String cname,start,end;
	 abstract void set_start();
	 abstract void set_end();
 }
 
 class cmucourse extends course{

	void set_start() {
		start = "aug";
	}

	void set_end() {
		end = "dec";
	}
	 
 }
 
 //Constructors
 class book {
	 String title, author; //with default constructors
	 int pages;
	 void note(int a) {
		 this.pages = a;
	 }
 }
 
 class ebook extends book {
	 ebook (){
		 this.title = "changed default t"; //this changed to default constructor
		 this.author = "changed default a";
		 this.pages = 1;
	 }
	 ebook(String title, String author,int pages){
		 this.title = title; //this overloads the constructor
		 this.author = author;
		 this.note(pages);
	 }
 }
 
  class amzebook extends ebook{
	  String platform;
	  amzebook(String title, String author, int pages, String platform){
		  super(title,author,pages); // use super to find the parent default classes
		  this.platform = platform;
	  }
  }
