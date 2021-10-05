//2021-10-05 Problem #21 Array Counters
import java.util.Scanner;
import java.util.ArrayList;

public class codeabbey21 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner readline = new Scanner (System.in);
		String [] summary_line = readline.nextLine().split(" ");
		ArrayList <Integer> result = new ArrayList<>();
		for (int i = 0; i < Integer.parseInt(summary_line[1]); i++) {
			result.add(0);
			}
		String [] numbers = readline.nextLine().split(" ");
		for (String i:numbers) {
			result.set(Integer.parseInt(i)-1,result.get(Integer.parseInt(i)-1)+1);
		}
		for (int a:result) {
			System.out.print(a+" ");
		}
		readline.close();
	}

}
