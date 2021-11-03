//Name: Frank Yue Ying (yying2)
package lab6;

public class Student implements Comparable<Student>{
	String lastName;
	String firstName;
	String andrewID;
	int companyRank;
	String companyName;
	
	Student(String lastname, String firstname, String andrewid, int companyRank, String companyName){
		this.lastName = lastname;
		this.firstName = firstname;
		this.andrewID = andrewid;
		this.companyRank = companyRank;
		this.companyName = companyName;
	}
	
	@Override
	public int compareTo(Student student) {
		return andrewID.compareTo(student.andrewID);
	}

}
