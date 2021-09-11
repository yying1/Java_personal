package practice;
import java.util.Scanner;


public class P02 {
	public static double genresult() {
		Scanner readInput = new Scanner(System.in);
		String numbers;
		double sum = 0.00d;
		numbers = readInput.nextLine();
		Scanner tokens = new Scanner(numbers);
		while (tokens.hasNext()) {
			sum += tokens.nextDouble();
		}
		
		tokens.close();
		readInput.close();
		return sum;
	}
	
	public static void main(String[] args) {
		System.out.println("Enter the numbers seperated by a space:");
		double sum;
		sum = genresult();
		System.out.println("The sum is "+String.format("%,.2f", sum));
	}

}
