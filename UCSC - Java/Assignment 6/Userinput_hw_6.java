import java.util.InputMismatchException;
import java.util.Scanner;

public class Userinput_hw_6 {
	public static void displayMenuRequest() {
		System.out.println("Welcome to soring program");
		System.out.println("1. Title" + "\n"+"2. Rank"+ "\n"+"3. Date"+ "\n"+"4. Stars"+ "\n"+"5. Likes");
		System.out.println("Enter your choice between 1 and 5 only:");
	}
	
	public static void selectMenu(String choice) {
		//int choice;
		Scanner readInput = new Scanner(System.in);
		Boolean reEnter;
		int choice_int;
		do {
			try {
				choice_int = Integer.valueOf(choice);
				if (choice_int <1 || choice_int >5) {
					throw new ArithmeticException();
				}
				System.out.printf("You entered valid choice %d" + "\n",choice_int);
				System.out.println("Thank you for giving your choice");
				reEnter = false;
			}
			catch (InputMismatchException e) { 
				System.out.println("You have entered an invalid choice. Try again.");
				reEnter = true;
				//System.out.println("Enter your choice between 1 and 5 only:");
				choice = readInput.next();
			}
			catch (NumberFormatException e) { 
				System.out.println("You have entered an invalid choice. Try again.");
				reEnter = true;
				//System.out.println("Enter your choice between 1 and 5 only:");
				choice = readInput.next();
			}
			catch (ArithmeticException e) { 
				System.out.println("You have not entered a number between 1 and 5. Try again.");
				reEnter = true;
				//System.out.println("Enter your choice between 1 and 5 only:");
				choice = readInput.next();
			}			
		} while (reEnter == true);	
		readInput.close(); //5.1, and will be used for 6.1
	}
	
	public static void displayDoubleRequest() {
		System.out.println("Welcome to two floats program");
		System.out.println("Enter two floats separated by a space:");
	}

	public static void getTwoDouble(String choice1, String choice2) {
		Boolean reEnter;
		Scanner readInput = new Scanner(System.in);
		double choice1_double;
		double choice2_double;
		do {
			try {
				choice1_double = Double.valueOf(choice1);
				choice2_double = Double.valueOf(choice2);
				System.out.printf("You entered two valid floats %1.2f" + " and " + "%1.2f"+"\n",choice1_double, choice2_double);
				System.out.println("\n"+"Press enter key to continue...");
				reEnter = false;
				readInput.close(); //5.2
			}
			catch (InputMismatchException e) { 
				System.out.println("You have entered an invalid input. Try again.");
				reEnter = true;
				choice1 = readInput.next();
				choice2 = readInput.next(); 
			}	
			catch (NumberFormatException e) { 
				System.out.println("You have entered an invalid input. Try again.");
				reEnter = true;
				choice1 = readInput.next();
				choice2 = readInput.next(); 
			}	
		} while (reEnter == true);	
		
	}
	
	public static void main(String[] args) {
		Scanner readInput = new Scanner(System.in);	
		
		//6.1
		Userinput_hw_6 getUserChoice = new Userinput_hw_6();
		String choice;
		getUserChoice.displayMenuRequest();
		choice = readInput.next();
		getUserChoice.selectMenu(choice);
		
		//6.2
		Userinput_hw_6 getTwoFloat = new Userinput_hw_6();
		getTwoFloat.displayDoubleRequest();
		String choice1;
		String choice2;
		choice1 = readInput.next();
		choice2 = readInput.next();
		getTwoFloat.getTwoDouble(choice1,choice2);
		
		readInput.close();

	}

}
