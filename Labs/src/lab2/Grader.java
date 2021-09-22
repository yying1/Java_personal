package lab2;
//Frank Yue Ying, yying2

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Grader {

	String[] fileData;  //stores each row from file as an array element
	String[] courses;	//will have all course codes
	String[][] courseGrades; //each i'th row will have grades for course in courses[i]
	String[] gradeLetters;  //will have all possible grade letters across all courses
	int[][] gradeTable;  //gradeTable[i][j] will have count of gradeLetters[j] in courses[i]
	
	Scanner input;

	//do not change this method
	public static void main(String[] args) {
		Grader grader = new Grader();
		
		grader.loadData("Grades.txt");
		grader.printData();
		
		grader.loadGrades();
		grader.printGrades();
		
		grader.loadGradeLetters();
		grader.printGradeLetters();
		
		grader.buildGradeTable();
		grader.printGradeTable();
	}

	/** loadData() takes filename as input, 
	 * reads its content, and loads each row 
	 * as a string in fileData array
	 * @param filename
	 */
	void loadData(String filename) {
		//write your code here
		String rawtext = "";
		try {
		Scanner readfile = new Scanner(new File("Grades.txt"));
		while(readfile.hasNextLine()) {
			rawtext = rawtext + readfile.nextLine()+"\n";
		}
		} catch (Exception e) {
			System.out.println(e);
		}
		fileData = rawtext.split("\n");
	}
	
	/** loadGrades() reads data from fileData[] array
	 * populates courses[] and courseGrades[][] arrays. 
	 * Each row in courses[] and courseGrades[][] represents one course
	 * Note that each row in courseGrades[][] has 
	 * a different number of elements
	 */
	void loadGrades() {
		//write your code here
		String course = "";
		String grades = "";
		for(String i:fileData) {
			course = course+i.substring( 0, i.indexOf(",")).trim()+"\n";
			grades = grades+i.substring(i.indexOf(",")+1, i.length()).trim()+"\n";
		}
		courses = course.split("\n");
		int row_count = course.split("\n").length;
		String [] rows = grades.split("\n");
		courseGrades = new String[row_count][];
		for (int a = 0;a<row_count;a++) {
			courseGrades[a] = rows[a].split(",");
		}
	}
	
	/** loadGradeLetters() populates the gradeLetters[] array
	 * by scanning the data in courseGrades[][].
	 * Each grade letter must appear only once in 
	 * gradeLetters[] 
	 */
	void loadGradeLetters() {
		//write your code here
		int row_count = courseGrades.length;
		String gradedata="";
		for (int b = 0;b<row_count;b++) {
			for (String c:courseGrades[b]) {
				if (!gradedata.contains(c.trim())) {
					gradedata = gradedata+c.trim()+",";
				}
			}
		}
		gradeLetters = gradedata.split(",");
	}
	
	/*** buildGradeTable() scans the data in courseGrades[][]
	 * and populates gradeTable[][]. Each row in grdeTable
	 * represents one course and each column represents a grade letter.
	 * Each cell contains the number of students who scored that grade 
	 * in that course
	 */
	void buildGradeTable() {
		//write your code here
		int course_count = courses.length;
		int grade_count = gradeLetters.length;
		gradeTable = new int[course_count][grade_count];
		for (int c = 0; c<course_count;c++) {
			for (int d = 0;d<grade_count;d++) {
				String gradeletter = gradeLetters[d];
				int counter = 0;
				for (String e:courseGrades[c]) {
					if (e.trim().equals(gradeletter.trim())) {
						counter++;
					}
				}
				gradeTable[c][d] = counter;
			}
		}
	}
	
	
/************************ print methods ********************************/	
	/** printData() prints all the rows 
	 * as shown in the handout under the heading
	 * *** File data ***
	 */
	void printData() {
		System.out.println("*** File data ***");
		for (int i = 0; i < fileData.length; i++) {
			System.out.println(fileData[i]);
		}
		System.out.println();
	}
	
	/** printGrades() prints data from courses[] and courseGrades[][]
	 * as shown in the handout under the 
	 * heading *** Course grades ***
	 */
	void printGrades() {
		System.out.println("*** Course grades ***");
		for (int i = 0; i < courses.length; i++) {
			System.out.print(courses[i] + " ");
			for (int j = 0; j < courseGrades[i].length; j++) {
				System.out.print(courseGrades[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	/** printGradeLetters prints letter grades stored in gradeLetters
	 * as shown in the handout under
	 * the heading *** Grade letters ***
	 */
	void printGradeLetters() {
		System.out.println("*** Grade letters ***");
		System.out.println(Arrays.toString(gradeLetters));
		System.out.println();
	}
	
	/** printGradeTable() prints the gradeTable
	 * as shown in the handout
	 * under the heading *** Grade Table ***
	 */
	void printGradeTable() {
		System.out.println("*** Grade Table ***");
		for (int i = 0; i < gradeLetters.length; i++) {
			System.out.print("\t" + gradeLetters [i]);
		}
		System.out.println();
		for (int i = 0; i < courses.length; i++) {
			System.out.print(courses[i] + "\t");
			for (int j = 0; j < gradeTable[i].length; j++) {
				System.out.print(gradeTable[i][j] + "\t");
			}
			System.out.println();
		}
	}
}

