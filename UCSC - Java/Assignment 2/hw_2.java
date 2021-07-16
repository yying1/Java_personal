import java.util.Scanner;

public class hw_2 {
	
	public static void exercise_2_1() {
		int radius = 2;
		double area;
		final double pi = 3.142;
		area= pi * radius * radius;
		System.out.printf("The area is: %1.2f \n",area);
	}
	
	public static void exercise_2_2() {
		int radius;
		double pi;
		double area;
		Scanner readInput = new Scanner(System.in);
		//extra line feed
		System.out.println();
		System.out.print("Enter the radius and pi: ");
		radius = readInput.nextInt();
		pi = readInput.nextDouble();
		area= pi * radius * radius;
		System.out.print("The area is: ");
		System.out.println(area);
	}
	
	public static void exercise_2_3() {
		String First_Name;
		String Last_Name;
		String City;
		int Zip; 
		
		Scanner readInput = new Scanner(System.in);
		
		System.out.print("What is your first name?: ");
		char yourInitial = readInput.next().charAt(0);
		System.out.println("Hello Mr. " + yourInitial + ".");
		readInput.nextLine();
		
		System.out.println();
		System.out.println("5185 is a fun course");
		System.out.println();
		
		System.out.printf("First Name \tLast Name \tCity\n");
		System.out.printf("----------- \t---------\t---\n");
		System.out.printf("Bill \tClinton \tHarlem\n");
		System.out.printf("\n");
		
		System.out.println();
		System.out.printf("How do you print double quotes?\n");
		System.out.printf("Who said\"Test Scores Can Be Used ....\"\n");
		System.out.println();

		System.out.print("Please enter your first name:");
		First_Name = readInput.next().toString();
		System.out.print("Please enter your last name:");
		Last_Name = readInput.next().toString();
		System.out.print("Please enter your city:");
		City = readInput.next().toString();
		System.out.print("Please enter your zip code:");
		Zip = readInput.nextInt();
		System.out.println();

		System.out.printf("First Name \tLast Name \tCity    \tZip Code\n");
		System.out.printf("----------- \t---------\t--------   \t--------\n");
		System.out.printf(First_Name+"      \t"+Last_Name+"      \t"+City+" \t"+Zip+"\n");
		System.out.printf("\n");
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		exercise_2_3();
		//exercise_2_2();
	}

}
