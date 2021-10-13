//Frank Yue Ying (yying2)
package lab4;

//DO NOT change this class
public class StudentA extends Student {

	static double studentADonations; //total money donated by Section A students
	static final double PERCENT_DONATION  = 0.01;
	
	StudentA(char section, String lastName, String firstName, double income) {
		super(section, lastName, firstName, income);
	}
	
	/**
	 * donate() Sets the value of donation as PERCENT_DONATION of student’s income. 
	 * Adds it to totalMoneyDonations and studentADonations. 
	 * Returns student object.
	 */
	@Override
	void donate() {
		donation = income * PERCENT_DONATION;  		
		Student.totalMoneyDonations += donation;
		StudentA.studentADonations += donation;
	}

}
