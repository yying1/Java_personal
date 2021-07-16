import java.util.InputMismatchException;
import java.util.Scanner;

public class hw_8 {
	int weeklyTemp[] = { 69, 70, 71, 68, 66, 71, 70 };
	int i, max = 0, min = 0;
	Boolean reEnter;
	Scanner readInput = new Scanner(System.in);	
	
	int x, y;
	char hChar1, vChar1;
	int ht1, wd1;
	Scanner input1 = new Scanner(System.in);
	
	public void getTemperatures() {
		String temperature_input;
		int temperature = 0;
		for (i = 0; i <weeklyTemp.length; i++) {
			do {
				try {
					System.out.printf("Please enter temperature for day %d : \n", i+1);
					temperature_input = readInput.next();
					temperature = Integer.valueOf(temperature_input);
					reEnter = false;
				}
				catch (InputMismatchException e) {
					System.out.println("Error!");
					reEnter = true;
				}
				catch (NumberFormatException e) { 
					System.out.println("Error!");
					reEnter = true;
				}	
				} while (reEnter == true);
			weeklyTemp[i] = temperature;
		}
	}
	
	public void printTemperatures() {
		for (i = 0; i < weeklyTemp.length; i++) {
			System.out .printf("\nThe temperature on day %d " +
			"was %d: ", i + 1,
			weeklyTemp[i]);
			}
			System.out .printf("\n\n");
	}
	
	public void getMax() {
		for (i = 0; i < 7; i++) {
			if (i == 0)
			max = weeklyTemp[i];
			if (weeklyTemp[i] > max)
			max = weeklyTemp[i];
			}
			System.out .printf("The Maximum temperature is: %d\n", max);
	}
	
	public void getMin() {
		for (i = 0; i < 7; i++) {
			if (i == 0)
			min = weeklyTemp[i];
			if (weeklyTemp[i] < min)
			min = weeklyTemp[i];
			}
			System.out .printf("The Minimum temperature is: %d\n", min);
		}
	
	public void getAverage() {
		float total = 0, average;
		for (i = 0; i < 7; i++)
			total += weeklyTemp[i];
		average = total / weeklyTemp.length;
		System.out .println("The average temperage for the week is:" + average);
	}
	
	public void printStatistics() {
		getMax();
		getMin();
		getAverage();
		
	}
	
	public void askBox() {
		System.out .print("\nPlease enter width of a box:");
		wd1 = input1.nextInt();
		input1.nextLine(); //clean the buffer
		System.out .print("\nPlease enter the horizontal charcters to draw box: ");
		hChar1 = input1.nextLine().charAt(0);
		input1.nextLine();
		System.out .print("\nPlease enter height of a box: ");
		ht1 = input1.nextInt();
		input1.nextLine(); 
		System.out .print("\nPlease enter the vertical charcters to draw box: ");
		vChar1 = input1.nextLine().charAt(0);
	}
	
	public void drawHorizontalLine() {	
		for (x=1; x<= wd1;x++)
			{
			System.out .print("" + hChar1);
			}
		System.out .print("\n");
	}
	
	public void drawVerticalLine() {
		for(x=1;x<= ht1-2;x++)
		{
			System.out .print(""+ vChar1);
			for (y=1;y <= wd1-2;y++)
				System.out .print(" ");
				System.out .print("" + vChar1 + "\n");
		}
	}
	
	public void drawBox() {
		askBox();
		drawHorizontalLine();
		drawVerticalLine();
		drawHorizontalLine();
	}
	
	public static void main(String[] args) {
		hw_8 temperature = new hw_8();
		
		//8.1
		temperature.getTemperatures();
		temperature.printTemperatures();
		temperature.printStatistics();
		
		//8.2
		hw_8 Box = new hw_8();
		Box.drawBox();
	}
	
}
