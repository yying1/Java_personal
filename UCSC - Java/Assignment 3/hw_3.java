import java.util.Scanner;

public class hw_3 {

	public static void exercise_3_1() {
		int yourAge;
		Scanner readInput = new Scanner(System.in);
		System.out.printf("How old are you?: ");
		yourAge= readInput.nextInt();
		if (yourAge >19 )
		System.out.printf("You are an adult\n");
		else if (yourAge <= 19 && yourAge >= 13 )
			System.out.printf("You are a teenager\n");
		else
		System.out.printf("You are a kid\n");
		readInput.close();
		
	}
	
	public static void exercise_3_2() {
		double firstN;
		double secondN;
		char operator;
		char response;
		Scanner readInput = new Scanner(System.in);
		
		do {
		System.out.printf("Type a number, operator, number --" + "separated by a space: ");
		firstN = readInput.nextDouble();
		operator = readInput.next().charAt(0);
		secondN = readInput.nextDouble();
		if (operator == '+')
			System.out.printf("%1.2f + %1.2f = %1.2f",
			firstN, secondN, firstN + secondN);
		else if (operator == '-')
			System.out.printf("%1.2f - %1.2f = %1.2f",
			firstN, secondN, firstN - secondN);
		else if (operator == '*')
			System.out.printf("%1.2f * %1.2f = %1.2f",
			firstN, secondN, firstN * secondN);
		else if (operator == '/')
			System.out.printf("%1.2f / %1.2f = %1.2f",
			firstN, secondN, firstN / secondN);
		else if (operator == '%')
			System.out.printf("%1.2f %% %1.2f = %1.2f",
			firstN, secondN,firstN % secondN);
		else
			System.out.printf("Unknown operator");
		
		System.out.printf("\n\n");
		System.out.printf("Do you want to continue? y/n");
		response = readInput.next().charAt(0);
		}
		while (response != 'n');
		readInput.close();
		
	}
	
	public static void exercise_3_3() {
		int num1;
		int num2;
		int num3;
		int max;
		char response;
		Scanner readInput = new Scanner(System.in);
		
		do {
		System.out.printf("Enter three integers to find max of them --" + "separated by a space: ");
		num1 = readInput.nextInt();
		num2 = readInput.nextInt();
		num3 = readInput.nextInt();
		max = (num1>num2)?  ((num1>num3)? num1: ((num3>num2)? num3:num2)):((num2>num3)? num2: ((num3>num1)? num3:num1));
		System.out.print("The Max is:" + max);
		System.out.printf("\n\n");
		System.out.printf("Do you want to continue? y/n");
		response = readInput.next().charAt(0);
		}
		while (response != 'n');
		System.out.printf("Thank you\n");
		readInput.close();
	}	
		
	public static void main(String[] args) {
		//exercise_3_1();
		//exercise_3_2();
		exercise_3_3();

	}

}
