//Frank Yue Ying (yying2)
package lab4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class University {
	String[] rosterStrings;
	public Student[] students;

	//DO NOT CHANGE THIS METHOD
	public void loadRosterStrings() {
		Scanner fileContent = null;
		StringBuilder rosterData = new StringBuilder();
		try {
			fileContent = new Scanner (new File("Roster.csv"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (fileContent.hasNextLine()) {
			rosterData.append(fileContent.nextLine() + "\n");
		}
		rosterStrings = rosterData.toString().split("\n"); 
	}

	/**loadStudents() method takes rosterStrings data and creates a new Student object
	 * from data in each string. It uses 'Section' data to decide which type of 
	 * Student to create. For example, if Section is 'A', then it creates StudentA object. 
	 * It initializes instance variables of Student object from rosterString data after splitting
	 * each String on ',' as delimiter. 
	 * Hint: Use charAt(index) method to get Section name
	 */

	public void loadStudents(){
		students = new Student[rosterStrings.length];
		for (int i = 0; i < rosterStrings.length; i++) {
			char section = rosterStrings[i].charAt(0);
			switch (section) {
			case 'A':
				StudentA sa = new StudentA(section,rosterStrings[i].split(",")[1] ,rosterStrings[i].split(",")[2],Double.parseDouble(rosterStrings[i].split(",")[3]));
				students[i] = sa;
				break;
			case 'B':
				StudentB sb = new StudentB(section,rosterStrings[i].split(",")[1] ,rosterStrings[i].split(",")[2],Double.parseDouble(rosterStrings[i].split(",")[3]));
				students[i] = sb;
				break;
			case 'C':
				StudentC sc = new StudentC(section,rosterStrings[i].split(",")[1] ,rosterStrings[i].split(",")[2],Double.parseDouble(rosterStrings[i].split(",")[3]));
				students[i] = sc;
				break;
			}
		}
	}
}
