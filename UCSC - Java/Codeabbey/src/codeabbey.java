import java.util.Scanner;

public class codeabbey {
	public static void p_1() {
		int a;
		int b;
		int sum_ab;
		Scanner readInput = new Scanner(System.in);
		a = readInput.nextInt();
		b = readInput.nextInt();
		sum_ab = a+b;
		System.out.printf("The sum is:\n"+ sum_ab);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		p_1();
	}

}
