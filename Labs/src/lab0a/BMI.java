//20210902: yying2@ created this for the BMI calculator homework. 55 mins
package lab0a;
import java.util.*;
//import java.math.*;

public class BMI {
	
	public static float calculateBMI(float weight, float height) {
		float BMI = 0.00000f;
		BMI = (float)(weight/Math.pow(height,2))*703;
		System.out.printf("Your BMI is: "+BMI+"\n");
		return BMI;
	}
	
	public static int calculateRoundedBMI(float weight, float height) {
		int iBMI;
		iBMI = Math.round((float)(weight/Math.pow(height,2))*703);
		System.out.printf("Your rounded BMI is: "+iBMI+"\n");
		return iBMI;
	}
	
	public static String findBMIStatus(float BMI) {
		String status= "";
		if (BMI < 18.5f)
			status = "Underweight";
		else if (18.5f <= BMI && BMI < 25f)
			status = "Normal";
		else if (25f <= BMI && BMI < 30f)
			status = "Overweight";
		else if (30f <= BMI)
			status = "Obese";
		System.out.printf("Your weight status is: "+status+"\n");
		return status;
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String input;
		float weight;
		float height;
		Scanner readInput = new Scanner(System.in);
		System.out.println("Welcome to BMI Calculator!");
		System.out.println("Please enter your weight in pounds");
		input = readInput.nextLine();
		weight = Float.parseFloat(input);
		System.out.println("Please enter your height in inches");
		input = readInput.nextLine();
		height = Float.parseFloat(input);
		float fBMI = BMI.calculateBMI(weight, height);
		int rBMI = BMI.calculateRoundedBMI(weight, height);
		String status = BMI.findBMIStatus(fBMI);
		readInput.close();
	}

}
