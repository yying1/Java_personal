//2021-10-31
//Time 11 mins

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class codeabbey25 {
	
	int gen_next(int A, int C, int M, int X) {
		return (A*X+C)%M;
	}
	
	public static void main(String[] args) {
		codeabbey25 ca = new codeabbey25();
		String Result="";
		try {
			Scanner readFile = new Scanner(new File("25.txt"));
			readFile.nextLine();
			while (readFile.hasNext()) {
				String[] raw = readFile.nextLine().split(" ");
				int x = Integer.parseInt(raw[3].trim());
				int result = 0;
				for (int i = 0; i<Integer.parseInt(raw[4].trim()); i++) {
					result = ca.gen_next(Integer.parseInt(raw[0].trim()), Integer.parseInt(raw[1].trim()), Integer.parseInt(raw[2].trim()), x);
					x = result;
				}
				Result = Result+String.valueOf(result)+" ";
			}
			readFile.close();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		System.out.println(Result.trim());
		
	}

}
