//2021-10-21, Time: 31 mins

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;


public class codeabbey23 {
	int swap_count = 0;
	
	public ArrayList<Integer> swap(ArrayList<Integer> result, int i){
		if (result.get(i) > result.get(i+1)) {
			swap_count++;
			Collections.swap(result, i, i+1);
		}
		return result;
	}
	public int checksum(ArrayList<Integer> result) {
		int checksum_result = 0;
		for (int a:result) {
			checksum_result = (checksum_result+a)*113%10000007;
		}
		return checksum_result;
	}
	
	public static void main(String[] args) {
		Scanner readInput = new Scanner (System.in);
		String[] original = readInput.nextLine().split(" ");
		ArrayList<Integer>result = new ArrayList<>();
		for (String x:original) result.add(Integer.parseInt(x));
		result.remove(result.size()-1);
		codeabbey23 ca23 = new codeabbey23();
		
		for (int i = 0; i<result.size()-1;i++) {
			result = ca23.swap(result,i);
		}
		System.out.println(ca23.swap_count+" "+ca23.checksum(result));
		readInput.close();
		
	}

}
