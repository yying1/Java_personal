package lab4d;

public abstract class Quiz {
	String questionString;  	//contains the question. 
							 	//For MathQuiz, it will have 3 elements: operand1, operator, operand2. 
								//The two operands can be any single digit between 0 and 9. 
								//The operator can be +, -, or *. 
	
								//For English quiz, it is the meaning of any one of the words 
								//read from the text file provided. 
	
	String answerString;		//contains the answer
								//For MathQuiz, it is the result of operation carried out 
								//as per questionString. 
								//For example, if questionString has 5+3, then answerString will have 8
	
								//For EnglishQuiz, the answerString has the word whose meaning is 
								//stored in questionString
								
	static int score;			//count of questions answered correctly in both Math and English quiz so far. 
	
	static int count; 			//count of questions asked and answered
	
	/** createQuestion() initializes the questionString */
	abstract void createQuestion(); 
	
	/** createAnswer() initializes the answerString */
	abstract void createAnswer();
	
	/** checkAnswer() compares the answer with expected result, 
	 * updates score and count as needed, returns true if the answer is correct, 
	 * else returns false */
	abstract boolean checkAnswer(String answer); 
}
