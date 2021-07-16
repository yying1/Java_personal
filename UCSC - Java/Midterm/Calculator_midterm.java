import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator_midterm {
	public static int choice_int;
	public static double number_1;
	public static double number_2;

	public static void displayCalculatorMenu() {
		System.out.println("Welcome to Frank Yue Ying's Handy Calculator");
		System.out.println("1. Addition" + "\n"+"2. Subtraction"+ "\n"+"3. Multiplication"+ "\n"+"4. Division"+ "\n"+"5. Exit"+ "\n");
		System.out.println("What would you like to do?");
	}
	
	public int getChoice(String choice) {
		choice_int = Integer.valueOf(choice);
		switch(choice_int) {
		case 1:
			System.out.println("Please enter two floats to add, separated by a space:");break;
		case 2:
			System.out.println("Please enter two floats to subtract, separated by a space:");break;
		case 3:
			System.out.println("Please enter two floats to multiply, separated by a space:");break;
		case 4:
			System.out.println("Please enter two floats to divide, separated by a space:");break;
		case 5:
			System.out.println("Thank you for using Frank Yue Ying's Handy Calculator");break;
		}
		return choice_int;
	}
	
	public static void getTwoNumberandResult(String number1, String number2, int choiceReturn) {
		number_1 = Double.valueOf(number1);
		number_2 = Double.valueOf(number2);	
		if (number_2 == 0 && choiceReturn == 4) {
			throw new InputMismatchException();
		} 
		switch(choice_int) {
		case 1:
			System.out.printf("Result of adding %1.2f and %1.2f is %1.2f"+"\n",number_1,number_2,number_1+number_2);break;
		case 2:
			System.out.printf("Result of subtracting %1.2f and %1.2f is %1.2f"+"\n",number_1,number_2,number_1-number_2);break;
		case 3:
			System.out.printf("Result of multiplying %1.2f and %1.2f is %1.2f"+"\n",number_1,number_2,number_1*number_2);break;
		case 4:
			System.out.printf("Result of dividing %1.2f and %1.2f is %1.2f"+"\n",number_1,number_2,number_1/number_2);break;
		}
		System.out.println("Press enter key to continue...");
	}
	
	public static void main(String[] args) {
		Scanner readInput = new Scanner(System.in);		
		String number1;
		String number2;
		String choice;
		int choiceReturn;
		Boolean reEnter;
		Boolean reEnter1;
		Calculator_midterm calculator = new Calculator_midterm();
		do {
			Calculator_midterm.displayCalculatorMenu();
			try {
				choice = readInput.next();
				choiceReturn = calculator.getChoice(choice);
				if (choiceReturn <1 || choiceReturn >5) {
					throw new ArithmeticException();
				}
				if (choiceReturn != 5) {
					do {
					try {
						number1 = readInput.next();
						number2 = readInput.next();
						Calculator_midterm.getTwoNumberandResult(number1,number2,choiceReturn);
						reEnter1 = false;
						readInput.next();
					}
					catch (InputMismatchException e) {
						System.out.println("You can't divide by zero please re-enter both floats:");
						reEnter1 = true;
						//number1 = readInput.next();
						//number2 = readInput.next();
					}
					catch (NumberFormatException e) { 
						System.out.println("You have entered invalid floats please re-enter:");
						reEnter1 = true;
						//number1 = readInput.next();
						//number2 = readInput.next();
					}	
					} while (reEnter1 == true);
				}
				reEnter = true;
				if (choiceReturn == 5) {
				reEnter = false;
				}
				}
			catch (ArithmeticException e) { 
					System.out.println("You have not entered a number between 1 and 5. Try again.");
					reEnter = true;
					//System.out.println("Enter your choice between 1 and 5 only:");
					//choice = readInput.next();
				}
			catch (NumberFormatException e) { 
					System.out.println("You have not entered a number between 1 and 5. Try again.");
					reEnter = true;
			}
		} while (reEnter == true);
		readInput.close();
	}
}
