package lab4d;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EnglishQuiz extends Quiz{
	String[] wordStrings;
	int questionIndex;
	static int score;
	static int count;
	EnglishQuiz(String filename){
		String raw = "";
		try {
			Scanner readfile = new Scanner(new File (filename));
			while(readfile.hasNext()) {
				raw = raw.trim() + "\n"+ readfile.nextLine();
			}
			readfile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.wordStrings = raw.split("\n");
	}
	
	
	@Override
	void createQuestion() {
		this.questionIndex = 0 + (int)(Math.random()*this.wordStrings.length);
		this.questionString = wordStrings[questionIndex].split(":")[1].trim();
		
	}

	@Override
	void createAnswer() {
		this.answerString = wordStrings[questionIndex].split(":")[0].trim();
		
	}

	@Override
	boolean checkAnswer(String answer) {
		Quiz.count++;
		EnglishQuiz.count++;
		if (answer.toLowerCase().trim().equals(this.answerString.toLowerCase().trim())) {
			Quiz.score++;
			EnglishQuiz.score++;
			return true;
		} else {
			return false;
		}
	}

}
