// 2021-10-23 
// Time: 20 mins

import java.util.Scanner;
import java.util.ArrayList;

public class codeabbey24 {
	
	int gen_random (int a) {
		return a*a/100%10000;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner readLine = new Scanner (System.in);
		Integer.parseInt(readLine.nextLine().trim());
		String[] raw_list = readLine.nextLine().trim().split(" ");
		codeabbey24 ca = new codeabbey24();
		ArrayList<Integer> result_list = new ArrayList<>();
		for (String i:raw_list) {
			int count = 0;
			ArrayList<Integer> result = new ArrayList<>();
			int next_i = Integer.parseInt(i);
			do{
				result.add(next_i);
				next_i = ca.gen_random(next_i);
				count ++;
			} while (!result.contains(next_i));
			result_list.add(count);	
			}
		readLine.close();
		for(int a:result_list) {
			System.out.print(a+" ");
		}
	}

}
