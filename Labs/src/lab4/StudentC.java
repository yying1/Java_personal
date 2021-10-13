//Frank Yue Ying (yying2)
package lab4;
import java.util.Random;

public class StudentC extends Student implements Serviceable{
	
	static double studentCDonations;
	static int timeDonation;
	static final int MAX_MONEY_DONATION  = 10;
	
	StudentC(char section, String lastName, String firstName, double income) {
		super(section, lastName, firstName, income);
	}

	@Override
	void donate() {
		donation = MAX_MONEY_DONATION ;  		
		Student.totalMoneyDonations += donation;
		StudentC.studentCDonations += donation;
		
	}
	
	@Override
	public void serve() {
		Random r = new Random();
		int time = r.nextInt(MAX_SERVICE_HOURS)+1;
		//adding 1 because the instruction asks the service hours to be 1 to 100 inclusive
		Student.totalTimeDonations += time;
		StudentC.timeDonation += time;
	}
}
