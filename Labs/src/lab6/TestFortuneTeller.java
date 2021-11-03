package lab6;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestFortuneTeller {
	
	static FortuneTeller fortuneTeller; 
	
	@BeforeClass
	public static void setupClass() {
		fortuneTeller = new FortuneTeller();
		fortuneTeller.loadStudentsList("Fortunes.csv");
		fortuneTeller.loadCompaniesList();
	}

	@Test
	public void testStudentsList() {
		assertEquals("Test students count", 99, fortuneTeller.students.size());
	}
	
	@Test 
	public void testCompaniesList() {
		assertEquals("Test companies count", 10, fortuneTeller.companies.size());
	}
	
	@Test
	public void testStudentComparable() {  //sorting on andrew-id
		Collections.sort(fortuneTeller.students);
		assertEquals("Test first student in natural order", "aaronho", fortuneTeller.students.get(0).andrewID );
	}
	
	@Test
	public void testCompanyComparable() {  //sorting on company rank
		Collections.sort(fortuneTeller.companies);
		assertEquals("Test first company in natural order", "Hilton", fortuneTeller.companies.get(0).name );
	}
	
	@Test
	public void testStudentComparator() { //sorting students on company name and then student andrewid.
		Collections.sort(fortuneTeller.students, new FortuneTeller().new StudentCompanyComparator());
		assertEquals("Test first student on alternate order", "andreywc", fortuneTeller.students.get(0).andrewID);
		assertEquals("Test second student on alternate order", "chiachih", fortuneTeller.students.get(1).andrewID);
	}

	@Test
	public void testCompanyComparator() {  //sorting on hired count and company name
		Collections.sort(fortuneTeller.companies, new FortuneTeller().new CompanyHiredCountComparator());
		assertEquals("Test first company on alternate order", "Cisco", fortuneTeller.companies.get(0).name);
	}
	
	@Test
	public void testHighestCompanyHiredCount() {  // who hired most students
		Collections.sort(fortuneTeller.companies, new FortuneTeller().new CompanyHiredCountComparator());
		assertEquals("Test first company", 12, fortuneTeller.companies.get(0).hiredCount );
	}
	
	@Test
	public void testLowestCompanyHiredCount() {  //who hired least students
		Collections.sort(fortuneTeller.companies, new FortuneTeller().new CompanyHiredCountComparator());
		assertEquals("Test first company", 7, fortuneTeller.companies.get(fortuneTeller.companies.size()-1).hiredCount );
	}
	
	@Test
	public void testOverallHiredCount() {
		assertEquals("Test overall hire count", 99, Company.overallHiredCount);
	}
}
