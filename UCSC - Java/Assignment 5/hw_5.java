
import java.util.Scanner;
import java.util.InputMismatchException;

public class hw_5 {
	public static void selectMenu() {
		System.out.println("Welcome to soring program");
		System.out.println("1. Title" + "\n"+"2. Rank"+ "\n"+"3. Date"+ "\n"+"4. Stars"+ "\n"+"5. Likes");
		int choice;
		Boolean reEnter;
		Scanner readInput = new Scanner(System.in);
		
		do {
			System.out.println("Enter your choice between 1 and 5 only:");
			try {
				choice = readInput.nextInt();
				if (choice <1 || choice >5) {
					throw new ArithmeticException();
				}
				System.out.printf("You entered valid choice %d" + "\n",choice);
				System.out.println("Thank you for giving your choice");
				reEnter = false;
			}
			catch (InputMismatchException e) { 
				System.out.println("You have entered an invalid choice. Try again.");
				reEnter = true;
				//System.out.println("Enter your choice between 1 and 5 only:");
				readInput.next();
			}
			catch (ArithmeticException e) { 
				System.out.println("You have not entered a number between 1 and 5. Try again.");
				reEnter = true;
				//System.out.println("Enter your choice between 1 and 5 only:");
				//readInput.next();
			}			
		} while (reEnter == true);	
		readInput.close(); //5.1
	}
	
	public static void enterDouble() {
		System.out.println("Welcome to two floats program");
		double choice1 = 0;
		double choice2 = 0;
		Boolean reEnter;
		//Scanner readInput = new Scanner(System.in);
		
		do {
			System.out.println("Enter two floats separated by a space:");
			try {
				Scanner readInput = new Scanner(System.in);
				choice1 = readInput.nextDouble();
				choice2 = readInput.nextDouble();
				System.out.printf("You entered two valid floats %1.2f" + " and " + "%1.2f"+"\n",choice1, choice2);
				System.out.println("Thank you for giving two floats");
				reEnter = false;
				readInput.close(); //5.2
			}
			catch (InputMismatchException e) { 
				System.out.println("You have entered an invalid input. Try again.");
				reEnter = true;
				//readInput.next();
				//readInput.close(); 
			}			
		} while (reEnter == true);	
		
	}
	
	public static void weeklyTemp() {

		int weeklyTemp[] = {69,70,71,68,66,71,70};
		int i, max = 0, min = 0;
		float total = 0, average;
		
		for (i =0;i<7; i++)
			System.out.printf("The temperature on day %d was %d:\n", i+1, weeklyTemp[i]);
		
		System.out.println();
		min = weeklyTemp[1];
		for (i =0;i<7; i++) { 
			if (min > weeklyTemp[i])
				min = weeklyTemp[i];	
		}	
		
		System.out.printf("The Minimum temperature is: %d\n", min);
		
		for (i =0;i<7; i++) { 
			if (max < weeklyTemp[i])
				max = weeklyTemp[i];	
		}	
		
		System.out.printf("The Maximum temperature is: %d\n", max);
		for (i =0;i<7; i++) { 
			total += weeklyTemp[i];	
		}	
		average = total/weeklyTemp.length;
		System.out.printf("The average temperature for the week is: %1.5f\n", average);
		System.out.println();
		System.out.println("Thank you for using my homework #5 solution");
	}
	
	public static void main(String[] args) {
		selectMenu();
		enterDouble();
		weeklyTemp();
	}

}
