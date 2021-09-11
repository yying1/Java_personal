package practice;
import java.util.Scanner;

public class P03 {
	public static void judgetype(String input) {
		long start;
		float duration;
		String output;
		Scanner readInput = new Scanner(System.in);
		start = System.currentTimeMillis();
		output = readInput.nextLine();
		start = (System.currentTimeMillis()-start)/1000;
		if (input.equals(output) == true) {
			System.out.println("Your typing is accurate!");
		}
		else if (input.equals(output) != true && input.equalsIgnoreCase(output) == true) {
			System.out.println("Your typing is good but ou made some case-errors!");
		}
		else if (input.equals(output) != true && input.equalsIgnoreCase(output) != true) {
			System.out.println("Your typing has errors!");
		}
		duration = (float)(output.split(" ").length/((float)start/60));
		System.out.println("Your speed is "+String.format("%,.6f", duration)+" words per min");
		readInput.close();
		
	}
	
	
	
	public static void main(String[] args) {
		String input;
		input = "This is a test sentence to test your typing proficiency. Type this as fast as you can";
		System.out.println(input);
		judgetype(input);

	}

}
