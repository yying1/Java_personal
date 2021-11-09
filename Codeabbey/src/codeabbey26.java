//2021-11-08
//Greatest Common Divisor

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class codeabbey26 {
	
	public int gcd(int a, int b) {
		while (a != b) {
			if (a > b) {
				a = a - b;
			}
			else {
				b = b-a;
			}
		}
		return a;
	}
	
	public int lcm (int a,int b) {
		int gcd = gcd(a,b);
		return a*b/gcd;
		
	}
	
	public static void main(String[] args) {
		codeabbey26 c26 = new codeabbey26();
		String result = "";
		try {
			Scanner readfile = new Scanner (new File("26.txt"));
			readfile.nextLine();
			while(readfile.hasNext()) {
				String raw_line = readfile.nextLine();
				int a = Integer.parseInt(raw_line.split(" ")[0]);
				int b = Integer.parseInt(raw_line.split(" ")[1]);
				String gcd = String.valueOf(c26.gcd(a, b));
				String lcm = String.valueOf(c26.lcm(a, b));
				result = result +"("+gcd+" "+lcm+")"+" ";
			}
			readfile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
	}

}
