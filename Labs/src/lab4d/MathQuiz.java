package lab4d;

public class MathQuiz extends Quiz{
	int operand1;
	int operand2;
	char operator;
	static int score;
	static int count;
	
	@Override
	void createQuestion() {
		this.operand1 = 0+(int)(Math.random()*9);
		this.operand2 = 0+(int)(Math.random()*9);
		char [] Ope = {'+','-','*'};
		int numOp = 0+(int)(Math.random()*2);
		operator = Ope[numOp];
		this.questionString = String.valueOf(operand1)+Ope[numOp]+String.valueOf(operand2);
	}

	@Override
	void createAnswer() {
		// TODO Auto-generated method stub
		int result = 0;
		if (operator == '+') {
			result= operand1 + operand2;
		}
		else if (operator == '-'){
			result= operand1 - operand2;
		} else {
			result= operand1 * operand2;
		}
		
		this.answerString = String.valueOf(result);
		
	}

	@Override
	boolean checkAnswer(String answer) {
		Quiz.count++;
		MathQuiz.count++;
		if (answer.equals(this.answerString)) {
			Quiz.score++;
			MathQuiz.score++;
			return true;
		} else {
			return false;
		}
	}

}
