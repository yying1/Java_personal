import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 2021-12-19 Body Mass Index
// Time: 25 min


public class codeabbey28 {
	
	public static void main(String[] args) {
		Scanner readLine;
		List<String> result = new ArrayList<>();
		List<Float> BMIs = new ArrayList<>();
		try {
			readLine = new Scanner(new File("data/28.txt"));
			int cases = Integer.parseInt(readLine.nextLine());
			for (int i = 0; i<cases; i++) {
				String[] raw_line = readLine.nextLine().trim().split(" ");
				float BMI = (float) (Float.valueOf(raw_line[0])/Math.pow(Float.valueOf(raw_line[1]),2));
				BMIs.add(BMI);
			}
			readLine.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (float f: BMIs) {
			if(f < 18.5) {
				result.add("under");
			} else if (18.5<= f && f< 25.0) {
				result.add("normal");
			} else if (25.0<= f && f< 30.0) {
				result.add("over");
			} else if (30.0<= f) {
				result.add("obese");
			} 
		}
		
		for(String s: result) {
			System.out.print(s+" ");
		}
	}
	
}
/** under, normal, over, obese
Underweight     -           BMI < 18.5
Normal weight   -   18.5 <= BMI < 25.0
Overweight      -   25.0 <= BMI < 30.0
Obesity         -   30.0 <= BMI
**/