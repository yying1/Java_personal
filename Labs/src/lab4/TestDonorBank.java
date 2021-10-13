package lab4;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestDonorBank {
	static DonorBank db;

	@BeforeClass
	public static void setupBeforeClass() {
		db = new DonorBank();
		db.getDonations();
	}
	
	@Test
	public void testStudentCount() {
		assertEquals(99, db.cmu.students.length);
	}

	@Test
	public void testSectionADonations() {
		assertEquals(114182.99, StudentA.studentADonations, .01);
	}
	
	@Test
	public void testSectionBDonations() {
		assertEquals(true, StudentB.studentBDonations < 36000);
	}
	
	@Test
	public void testSectionCDonations() {
		assertEquals(290.00, StudentC.studentCDonations, .01);
	}
	
	@Test
	public void testTimeDonation() {
		assertEquals(true, Student.totalTimeDonations < 2900 && Student.totalTimeDonations > 29);
	}

	@Test
	public void testSectionBMaxMoneyDonation() {
		assertEquals(1000, StudentB.MAX_MONEY_DONATION);
	}
	
	@Test
	public void testSectionCMaxMoneyDonation() {
		assertEquals(10, StudentC.MAX_MONEY_DONATION);
	}
	
	@Test
	public void testMaxServiceHours() {
		assertEquals(100, Serviceable.MAX_SERVICE_HOURS);
	}
}
