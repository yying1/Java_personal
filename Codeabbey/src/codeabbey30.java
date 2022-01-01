// 2022-01-01 Reverse String
// time: 11 mins

import java.util.Scanner;

public class codeabbey30 {

	public static void main(String[] args) {
		Scanner readLine = new Scanner(System.in);
		char[] raw_str = readLine.nextLine().toCharArray();
		String reverse_str = "";
		for (int i = raw_str.length-1; i>=0;i--){
			reverse_str = reverse_str+raw_str[i];
		}
		System.out.println(reverse_str);
		readLine.close();
	}

}
