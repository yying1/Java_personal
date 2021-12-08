package lab4d;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestQuizTime {

	String filename = "EnglishQuizWords.txt";
	Quiz[] quizzes = { new MathQuiz(), new EnglishQuiz(filename) };  
	
	//test if Math question is created correctly
	@Test
	public void test1_MathQuizCreateQuestion() {
		quizzes[0].createQuestion();
		assertTrue(quizzes[0].questionString.matches("[0-9]+[-+*]+[0-9]+"));
	}

	//test if Math answer is created correctly
	@Test
	public void test2_MathQuizCreateAnswer() {
		quizzes[0].createQuestion();
		quizzes[0].createAnswer();
		int operand1 = Integer.parseInt(Character.toString(quizzes[0].questionString.charAt(0)));
		char operator = quizzes[0].questionString.charAt(1);
		int operand2 = Integer.parseInt(Character.toString(quizzes[0].questionString.charAt(2)));
		switch (operator) {
		case '+': assertEquals(operand1 + operand2, Integer.parseInt(quizzes[0].answerString));
		break;
		case '-': assertEquals(operand1 - operand2, Integer.parseInt(quizzes[0].answerString));
		break;
		case '*': assertEquals(operand1 * operand2, Integer.parseInt(quizzes[0].answerString));
		break;
		default: break;
		}
	}

	//test if Math quiz answer is checked correctly
	@Test
	public void test3_MathQuizCheckAnswer() {
		int answer = 0;
		quizzes[0].createQuestion();
		quizzes[0].createAnswer();
		int operand1 = Integer.parseInt(Character.toString(quizzes[0].questionString.charAt(0)));
		char operator = quizzes[0].questionString.charAt(1);
		int operand2 = Integer.parseInt(Character.toString(quizzes[0].questionString.charAt(2)));
		switch (operator) {
		case '+': answer = operand1 + operand2; break;
		case '-': answer = operand1 - operand2; break;
		case '*': answer = operand1 * operand2; break;
		default: break;
		}
		assertEquals(answer, Integer.parseInt(quizzes[0].answerString));
	}
	
	//test if EnglishQuiz constructor has read all words in wordStrings from the file
	@Test
	public void test4_EnglighQuizConstructor() {
		assertEquals(10, ((EnglishQuiz)quizzes[1]).wordStrings.length);
	}
	
	
	//test if English quiz question and answer match
	@Test
	public void test5_EnglishQuizCreateQuestion() {
		quizzes[1].createQuestion();
		quizzes[1].createAnswer();
		boolean found = false;
		for (String s : ((EnglishQuiz)quizzes[1]).wordStrings) {
			if (s.contains(quizzes[1].answerString) && s.contains(quizzes[1].questionString) ) {
				found = true;
			}
		}
		assertTrue(found);
	}
	
	//test if English quiz answer is checked correctly
	@Test
	public void test6_EnglishQuizCheckAnswer() {
		quizzes[1].questionString = "Around; all round; on every side of.";
		quizzes[1].answerString = "About";
		assertTrue(quizzes[1].checkAnswer("About"));
	}
	
	//test Math score
	@Test
	public void test7_MathScore() {
		//reset scores
		Quiz.score = 0; 
		MathQuiz.score = 0;
		EnglishQuiz.score = 0;
		
		quizzes[0].questionString ="3+2";
		quizzes[0].answerString = "5";
		quizzes[0].checkAnswer("5");
		assertEquals(1, Quiz.score);
		assertEquals(1, MathQuiz.score);
		assertEquals(0, EnglishQuiz.score);
	}
	
	//test English score
	@Test
	public void test8_EnglishScore() {
		//reset scores
		Quiz.score = 0; 
		MathQuiz.score = 0;
		EnglishQuiz.score = 0;
		
		quizzes[1].questionString ="To do away with wholly; to annul; to make void;";
		quizzes[1].answerString = "Abolish";
		quizzes[1].checkAnswer("abolish");
		assertEquals(1, Quiz.score);
		assertEquals(0, MathQuiz.score);
		assertEquals(1, EnglishQuiz.score);
	}
}
