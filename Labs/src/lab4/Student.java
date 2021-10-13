//Frank Yue Ying (yying2)
package lab4;

//DO NOT CHANGE THIS CLASS
public abstract class Student {
	char section;
	String lastName;
	String firstName;
	double income;
	double donation; //calculated by donate() method

	static double totalMoneyDonations; //total money donated by all students across all sections
	static int totalTimeDonations; //total time donated across all sections

	Student(char section, String lastName, String firstName, double income) {
		this.section = section;
		this.lastName = lastName;
		this.firstName = firstName;
		this.income = income;
	}
	
	abstract void donate(); 

}
