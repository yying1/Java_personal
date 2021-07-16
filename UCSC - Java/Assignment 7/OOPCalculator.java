import java.util.InputMismatchException;
import java.util.Scanner;

public class OOPCalculator {
	int choice_int = 0;
	Boolean reEnter;
	Scanner readInput = new Scanner(System.in);	
	double number_1;
	double number_2;
	public int askCalcChoice() {	
		System.out.println("Welcome to Frank Yue Ying's Handy Calculator");
		System.out.println("1. Addition" + "\n"+"2. Subtraction"+ "\n"+"3. Multiplication"+ "\n"+"4. Division"+ "\n"+"5. Exit"+ "\n");
		System.out.println("What would you like to do?");
		String choice;
		do {
			try {
				choice = readInput.next();
				switch (choice) {
				case "1":
					System.out.println("Please enter two floats to add, separated by a space:"); choice_int = 1;break;
				case "2":
					System.out.println("Please enter two floats to subtract, separated by a space:");choice_int = 2;break;
				case "3":
					System.out.println("Please enter two floats to multiply, separated by a space:");choice_int = 3;break;
				case "4":
					System.out.println("Please enter two floats to divide, separated by a space:");choice_int = 4;break;
				case "5":
					choice_int = 5;break;
				case "A":
					System.out.println("Please enter two floats to add, separated by a space:");choice_int = 1;break;
				case "S":
					System.out.println("Please enter two floats to subtract, separated by a space:");choice_int = 2;break;
				case "M":
					System.out.println("Please enter two floats to multiply, separated by a space:");choice_int = 3;break;
				case "D":
					System.out.println("Please enter two floats to divide, separated by a space:");choice_int = 4;break;
				case "X":
					choice_int = 5;break;	
				case "a":
					System.out.println("Please enter two floats to add, separated by a space:");choice_int = 1;break;
				case "s":
					System.out.println("Please enter two floats to subtract, separated by a space:");choice_int = 2;break;
				case "m":
					System.out.println("Please enter two floats to multiply, separated by a space:");choice_int = 3;break;
				case "d":
					System.out.println("Please enter two floats to divide, separated by a space:");choice_int = 4;break;
				case "x":
					choice_int = 5;break;
				default:
					throw new ArithmeticException();
				}
				reEnter = false;
			}
			catch (ArithmeticException e) { 
				System.out.println("You have not entered a number between 1 and 5. Try again.");
				reEnter = true;
			}
			catch (NumberFormatException e) { 
				System.out.println("You have not entered a number between 1 and 5. Try again.");
				reEnter = true;
			} 
		} while (reEnter == true);
		return choice_int;
	}
	
	public void askTwoValues() {
		String number1;
		String number2;	
		do {
			try {
				number1 = readInput.next();
				number2 = readInput.next();
				number_1 = Double.valueOf(number1);
				number_2 = Double.valueOf(number2);	
				if (number_2 == 0 && choice_int == 4) {
					throw new InputMismatchException();
				} 
				reEnter = false;
			}
			catch (InputMismatchException e) {
				System.out.println("You can't divide by zero please re-enter both floats:");
				reEnter = true;
			}
			catch (NumberFormatException e) { 
				System.out.println("You have entered invalid floats please re-enter:");
				reEnter = true;
			}	
			} while (reEnter == true);
	}
	
	public void displayResults() {
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
		try {
			System.in.read();
		}
		catch (Exception e) { 
			}
		}
	
	public void displayBye() {
		System.out.println("Thank you for using Frank Yue Ying's Handy Calculator");
	}

	public static void main(String[] args) {
		OOPCalculator calc = new OOPCalculator();
		while (calc.askCalcChoice () != 5){ 
		calc.askTwoValues (); 
		calc.displayResults(); 
		}
		calc.displayBye(); 
		} 
	
}
