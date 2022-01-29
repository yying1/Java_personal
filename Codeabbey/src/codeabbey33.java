// 2022-01-28 Parity Control
// time: 156 mins

import java.util.Scanner;

public class codeabbey33 {

	public static void main(String[] args) {
		
		String outputString = "";
	    
		Scanner readline = new Scanner(System.in);
		String [] raw_array = readline.nextLine().split(" ");
		
	    for ( String s: raw_array) {
	    	int item = Integer.parseInt(s);
			String s1 = String.format("%8s", Integer.toBinaryString((byte) item & 0xFF)).replace(' ', '0');
	    	int[] binary_array = new int[8];
	    	int detect = 0;
	    	for (int a = 0; a <8; a++) {
	    		binary_array[a] = Integer.parseInt(s1.substring(a,a+1));
	    		if (binary_array[a] == 1) {
	    			detect++;
	    		}
	    	}
	    	if(detect % 2 == 0) {
	    		s1 = "0"+s1.substring(1,8);
	    		outputString = outputString+(char)Integer.parseInt(s1, 2);//2 indicates binary
	    	}
	    }
	    System.out.println(outputString);
	    readline.close();
	}

}
