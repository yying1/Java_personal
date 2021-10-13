//Frank Yue Ying (yying2)
package lab4;
import java.util.Random;

public class StudentB extends Student {

	static double studentBDonations;
	static final int MAX_MONEY_DONATION  = 1000;
	
	StudentB(char section, String lastName, String firstName, double income) {
		super(section, lastName, firstName, income);
	}

	@Override
	void donate() {
		Random r = new Random();
		donation = r.nextInt(MAX_MONEY_DONATION)+1; 
		//adding 1 because the instruction asks the money donation to be 1 to 1000 inclusive
		Student.totalMoneyDonations += donation;
		StudentB.studentBDonations += donation;
	}
	
	
}
