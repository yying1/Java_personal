package lab0;
//package lab0a;

import java.util.Scanner;

public class Calculator {
	
	int add(int x, int y) {
		int sum = x + y;
		return sum;
	}
	
	int subtract(int x, int y) {
		int sub = x - y;
		return sub;
	}
	
	int multiply (int x, int y) {
		int mul = x * y;
		return mul;
	}
	
	int divide(int x, int y) {
		int div;
		if (y == 0) {div = 0;}
		else {div = x/y;}
		return div;
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
