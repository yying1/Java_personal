// 2022-01-09 Rotate String
// time:  mins

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class codeabbey31 {
	
	public static void main(String[] args) {
		Scanner readLine;
		String[] result;
		try {
			readLine = new Scanner(new File("data/31.txt"));
			int cases = Integer.parseInt(readLine.nextLine());
			result=  new String[cases];
			for(int i = 0; i<cases;i++) {
				String[] raw_line = readLine.nextLine().split(" ");
				int move = Integer.parseInt(raw_line[0]);
				if (move >0) {
					result[i] = raw_line[1].substring(move) + raw_line[1].substring(0,move);
				} else if(move < 0){
					result[i] = raw_line[1].substring(raw_line[1].length() +move)+ raw_line[1].substring(0,raw_line[1].length() +move);
				}
			}
			for (String r:result) {
				System.out.print(r+" ");
			}
			readLine.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
