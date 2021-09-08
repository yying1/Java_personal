package prereq;

/** Name: Frank Yue Ying
 *  AndrewID: yying2
 */


import java.util.Scanner;

public class Divider {
	
	//do not change the main method
	public static void main(String[] args) {
		Divider divider = new Divider();
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a divisor greater than 1");
		int divisor = input.nextInt();
		System.out.println("Enter a dividend greater than divisor");
		int dividend = input.nextInt();
		divider.divide(dividend, divisor);
		input.close();
	}
	
	private void divide(int x, int y) {
		//write your code here
		int quotient;
		int remainder;
		do {
			quotient = x / y;
			remainder = x % y;
			System.out.println(String.valueOf(x)+" = "+String.valueOf(quotient)+" * "+String.valueOf(y)+" + "+String.valueOf(remainder));
			if (remainder != 0) {
				y = remainder;
				x = quotient;
			}
		} while (remainder !=0);
	}
}




