package lab2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestGrader {
	
	static Grader grader;
	
	@BeforeAll 
	public static void setupTest() {
		grader = new Grader();
		grader.loadData("Grades.txt");
		grader.loadGrades();
		grader.loadGradeLetters();
		grader.buildGradeTable();
	}

	@Test
	void test1_FileDataLength() {
		assertEquals(4, grader.fileData.length);
	}
	
	@Test
	void test2_CoursesLength() {
		assertEquals(4, grader.courses.length);
	}
	
	@Test
	void test3_CoursesContent() {
		boolean found = false;
		for (String s : grader.courses) {
			if (s.equals("95643")) {
				found = true;
				break;
			}
		}
		assertTrue(found);
	}
	
	@Test
	void test4_CourseGradesLength() {
		assertEquals(4, grader.courseGrades.length);
		assertEquals(8, grader.courseGrades[0].length);
		assertEquals(5, grader.courseGrades[2].length);
	}
	
	@Test
	void test5_GradeLettersLength() {
		assertEquals(5, grader.gradeLetters.length);
	}
	
	@Test
	void test6_GradeLettersContent() {
		assertTrue(Arrays.asList(grader.gradeLetters).contains("F"));
		assertTrue(Arrays.asList(grader.gradeLetters).contains("D"));
	}
	
	@Test
	void test7_GradeTableLengths() {
		assertEquals(4, grader.gradeTable.length);
		assertEquals(5, grader.gradeTable[0].length);
	}
	
	@Test
	void test8_GradeTableContent() {
		assertEquals(3, grader.gradeTable[0][1]);
	}
	
}
