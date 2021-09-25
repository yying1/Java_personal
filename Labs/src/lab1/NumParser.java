//Frank Yue Ying, yying2@

package lab1;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class NumParser {
	double sum, max, min; //to store the results to be printed
	int count; //to count the valid numbers parsed
	String d ="";

	/** DO NOT change the main method **/
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the numbers separated by a space or a comma");
		String numbers = input.nextLine();
		NumParser np = new NumParser();
		np.parseCalculate(numbers);
		np.printResults();
		input.close();
	}

	/**The parseCalculate method takes a string as input parameter and parses
	 * out the valid numbers from it. While parsing, it also calculates sum, max, min, and count.
	 * If it finds any invalid token, it discards it and prints it out as a discarded token 
	 * @param numbers
	 */
	void parseCalculate(String numbers) {
		Scanner strings = new Scanner(numbers).useDelimiter("[\\s,]+");
		if (strings.toString().trim().equals("")) {
			sum = 0.0;
			max = 0.0;
			min = 0.0;
			count = 0;
		}
		String a;
		while (strings.hasNext()) {
			a = strings.next();
			if (a.trim().matches("[+-]?[0-9]+[.]?[0-9]*")) {
				count +=1;
				sum+= Double.parseDouble(a);
				if (count == 1) {
				max = Double.parseDouble(a);
				min = Double.parseDouble(a);
				}
				else {
					max = Math.max(max,Double.parseDouble(a));
					min = Math.min(min,Double.parseDouble(a));
				}
			}
			else d =d+ "Discarded token: "+a+"\n"; 
		}
	}

	/** The printResult method prints the output as shown 
	 * in the lab-handout
	 */
	private void printResults() {
		if (d != null) System.out.println(d.trim());
		double average;
		average = sum/count;
		// previously used Bigdecimal, but it cannot resolve /0 issue and cannot be set as NaN
		System.out.printf("Sum is %1.1f"+"\n",sum);
		System.out.printf("Average is %f"+"\n",average);
		System.out.printf("Max is %1.1f"+"\n",max);
		System.out.printf("Min is %1.1f"+"\n",min);
	}
}
