//Frank Yue Ying (yying2)
package lab4;


public class DonorBank {
	University cmu = new University();
	
	//DO NOT change main method
	public static void main(String[] args) {
		DonorBank db = new DonorBank();
		db.getDonations();
		db.printDonations();
	}

	//DO NOT change this method
	void printDonations() {
		System.out.printf("Section A money donations: $%,15.2f%n", StudentA.studentADonations);
		System.out.printf("Section B money donations: $%,15.2f%n", StudentB.studentBDonations);
		System.out.printf("Section C money donations: $%,15.2f%n", StudentC.studentCDonations);
		System.out.println("-----------------------------------------------------------");
		System.out.printf("Total money donations:     $%,15.2f%n", Student.totalMoneyDonations);
		System.out.println("-----------------------------------------------------------");
		System.out.printf("Section C time donations: \t%,5d hours%n", Student.totalTimeDonations);
	}
	
	/**getDonations() invokes cmu's loadRosterStrings() method and loadStudents() method. 
	 * It then invokes donate() method on each student in cmu's students array
	 * It also invokes serve() on students from Section C.
	 */
	void getDonations() {
		//write your code here
		cmu.loadRosterStrings();
		cmu.loadStudents();
		for (Student s:cmu.students) {
			s.donate();
			if (s instanceof Serviceable ) {
				((Serviceable) s).serve();
			}
		}
	}
}


