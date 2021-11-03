//Map and Hashing
import java.util.*;

public class Class18_20211101 {
	// Memory address Vs. Object State/Behavior
	
	
	
	public static void main(String[] args) {
	//Hash Code
		//Exist in Object Class, all object have it
		//Cannot implement on primitive data types
		//Default to compute code using memory address, need to override
		Integer i = new Integer(10);
		Integer j = new Integer(10);
		System.out.println(i.hashCode());//10
		System.out.println(i.equals(j));//True
		System.out.println(i == j);//False, comparing memory location
		//String Class
		//String hash code is designed in the string source code
		//String hashing can leads to collision
		//https://stackoverflow.com/questions/2310498/why-doesnt-strings-hashcode-cache-0 
		
	//Hash Set
		Set<Student> stset = new HashSet<>();
		stset.add(new Student("abc","MISM"));
		stset.add(new Student("xyz","MISM"));
		// Note student with same id can still be added because default uses memory address to hash
		stset.add(new Student("abc","BIDA"));
		// Order will change because object is stored in random bucket in hashset
		for (Student s:stset) {
			System.out.println(s.ID+" "+s.program);
		}
		
		//Override hashing method
		Set<Student1> stset1 = new HashSet<>();
		stset1.add(new Student1("abc","MISM"));
		stset1.add(new Student1("xyz","MISM"));
		// will not be added: invoke hash code and equals. 
		stset1.add(new Student1("abc","BIDA"));
		// will print abc mism and xyz mism
		for (Student1 s1:stset1) {
			System.out.println(s1.ID+" "+s1.program);
		}
		
	//Hash Map
		Map<String,Student> stset2 = new HashMap<>();
		stset2.put("abc",new Student("abc","MISM"));
		stset2.put("xyz",new Student("xyz","MISM"));
		//null can be added
		stset2.put(null, new Student("null","MISM"));
		//Override: This will replace the previous null in the Hash Map
		stset2.put(null, new Student(null,null));
		//Override: This will also replace the previous entry in the Map
		stset2.put("abc",new Student("abc","BIDA"));
		// element of HashMap is Entry
		for (Map.Entry<String, Student> entry :stset2.entrySet()) {
			System.out.println(entry.getKey()+" "+entry.getValue().program);
		}
		// use .keySet() to print keys
		// use .values() to print values
		
	//Hash Map Searching 
		String id = "abc";
		for (Map.Entry<String, Student> entry :stset2.entrySet()) {
			if (entry.getKey().equals(id)) {
				System.out.println(entry.getKey()+" "+entry.getValue().program);
			}
		}
		//This will be ok, but entering the wrong key will throw exception
		System.out.println(stset2.get(id).program);
		//This will fix the above problem
		if (stset2.containsKey(id)) {
			System.out.println(stset2.get(id).program);
		}
		
	//Tree Set
		//Use a sorted set to always keep the data sorted
		//Use compareTo to sort the data
		//object with the same value from compareTo will not be added to tree set, the later duplicate will no be added
		//Should Override hashCode, equals, compareTo and stay all consistent
		//Tree set will not use hashcode & equals
		//Must implement Comparable for tree set
	
	//Tree Map
		//Using String as Key 
		//Then it will not require to override anything
		
		
		
	}

}

class Student {
	String ID,program;
	Student (String id, String p){
		this.ID = id;
		this.program = p;
	}
}

class Student1 {
	String ID,program;
	Student1 (String id, String p){
		this.ID = id;
		this.program = p;
	}
	
	@Override
	//must also override equals
	public int hashCode() {
		return Objects.hash(ID);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if (this == o) return true;//check memory location
		if (getClass() != o.getClass()) return false;
		Student1 s = (Student1) o;
		return ID.equals(s.ID);
	}
}
