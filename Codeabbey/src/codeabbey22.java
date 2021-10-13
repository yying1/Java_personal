//2021-10-12, Time: 120 mins, having trouble with get reminder pages calculated

import java.io.File;
//import java.math.BigDecimal;
import java.util.Scanner;

public class codeabbey22 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String result ="";
		try {
			Scanner readFile = new Scanner (new File("22.txt"));
			int num = Integer.parseInt(readFile.nextLine());
			for (int i = 0; i<num; i++) {
				String[] raw = readFile.nextLine().split(" ");
				double x,y;
				int n;
				x = Double.parseDouble(raw[0]); 
				y = Double.parseDouble(raw[1]);
				n = Integer.parseInt(raw[2]);
				double time1 = n/(1/x+1/y);
				//System.out.println((int)time1);
				//System.out.println(new BigDecimal(time1).toPlainString());
				int page1 = (int)(n - (int)((int)time1/x) - (int)((int)time1/y));
				double fast = Math.min(x, y);
				double slow = Math.max(x, y);
				if (page1 != 0) {
					double time2 = page1*fast;
					if(time1%fast!=0) {
						time2 = time2-time1%fast;
					}
					double time3 = page1*slow;
					if (time1%slow!=0) {
						time3 = time3-time1%slow;
					}
					time1 = time1 + Math.min(time2, time3)+0.5;
				} else {
					time1 = time1+0.5;
				}
				result = result + String.valueOf((int)time1)+" ";
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		System.out.println(result);
		
	}

}
