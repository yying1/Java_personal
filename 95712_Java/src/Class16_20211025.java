// Collections
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Class16_20211025 {
		private Object a;
		public void set(Object a) {
			this.a = a;
		}
		public Object get() {
			return this.a;
		}
		
	public static void main(String[] args) {
		//ArrayList
			//internally implemented as Array with initial capacity of 10
		List<Integer> testlist = new ArrayList<>(); //up-casting to behave like a list, can change data type later
		ArrayList<Integer> testalist = new ArrayList<>();
			//List on the left, program to a interface, not to implementation
			// This create a list with 10 capacity (also default), will increment at 10 with more data
		int a = 10;
		testlist.add(a);
		testalist.add(20);//auto-boxing to primitive data type to reference data type 
		
		//Generic Types, all collections are built in to Generic types
		Class16_20211025 test1 = new Class16_20211025();
		test1.set("This is a test");
		int i = (int) test1.get(); // will through runtime error but not in complier
		
		GenericBox<String> test2 = new GenericBox<>();
		test2.set("This is a test too"); 
			//int ii = (int) test2.get(); //now complier will through cast error
		
		GenericBox2<Number> test3 = new GenericBox2<>();
		test3.set(10); //can set as int
		test3.set(10.0f); // or set as float
		float n = (float)test3.get(); //down-casting number type from generic box 
		
		//Collection.sort
		List<passenger> plist = new ArrayList<>();
		plist.add(new passenger("Frank",1));
		plist.add(new passenger("Stella",2));
		plist.add(new passenger("Bella",3));
			//natural ordering sort by Comparable using name
		Collections.sort(plist); //must implemented comparable
			//comparator method to design custom comparision
		
		//Iterator
		Iterator<passenger> iter = plist.iterator();
			//create iter inside the plist collection
		while (iter.hasNext()) {
			iter.next().talktome();//invoke a method from class
			//should use next() once in the iter while loop
			// can iter.remove the current element
		}
		for (passenger p:plist) {
			p.talktome();
			// read only and going forward, will through concurrentmodification exception
		}
		
		//Linked List: data structure
			//Node based, not continuous memory structure
			//Easy to add/remove, hard to find/access
		plist.get(2); // arraylist indexing works to get to anywhere in the list

	}

}

class passenger implements Comparable<passenger>{
	String name;
	int ID;
	passenger (String n, int id){
		this.name = n;
		this.ID = id;
	}
	@Override
	public int compareTo(passenger p) {
		return name.compareTo(p.name);
	}
	
	public String talktome() {
		return this.name;
	}
}

class passengercompare implements Comparator<passenger> {
	@Override
	public int compare(passenger p1, passenger p2) {
		return (p1.ID - p2.ID);
	}
}

class GenericBox<T>{
	private T a;
	public void set(T a) {
		this.a = a;
	}
	public T get() {
		return this.a;
	}
}

class GenericBox2<T extends Number>{ //can include any child of number class
	private T a;
	public void set(T a) {
		this.a = a;
	}
	public T get() {
		return this.a;
	}
}
