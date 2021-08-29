//package lab0a;

import java.util.Scanner;

public class Calculator {
	
	int add(int x, int y) {
		//write your code here
		return 0;
	}
	
	int subtract(int x, int y) {
		//write your code here
		return 0;
	}
	
	int multiply (int x, int y) {
		//write your code here
		return 0;
	}
	
	int divide(int x, int y) {
		//write your code here
		return 0;
	}
	
	/**DO NOT CHANGE THIS METHOD **/
	public static void main(String[] args) {
		Calculator calc = new Calculator();
		Scanner input = new Scanner(System.in);
		System.out.println("Enter x");
		int x = input.nextInt();
		System.out.println("Enter y");
		int y = input.nextInt();
		System.out.printf("x + y = %d%n", calc.add(x, y));
		System.out.printf("x - y = %d%n", calc.subtract(x, y));
		System.out.printf("x * y = %d%n", calc.multiply(x, y));
		System.out.printf("x / y = %d%n", calc.divide(x, y));
		input.close();
	}
}
