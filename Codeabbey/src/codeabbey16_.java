import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;

public class codeabbey16_ {
	
	public static void p_16() throws FileNotFoundException {
		int num;
		ArrayList <String> result_list = new ArrayList<>();
		Scanner readFile = new Scanner(new File("16.txt"));
		num = Integer.parseInt(readFile.nextLine());
		for (int a =0;a<num;a++) {
			String [] items = readFile.nextLine().split(" ");
			ArrayList <Integer> nums = new ArrayList<Integer>();
			for (int b = 0;b<items.length-1;b++) nums.add(Integer.valueOf(items[b]));
			int sum = 0;
			for (int d:nums) {
				sum+=d;
			}
			double avg = sum*1.00/nums.size();
			int round = (int)(avg +0.5);
			//System.out.println(avg);
			//System.out.println(round);
			result_list.add(String.valueOf(round)); 
		}
		for (String item:result_list)
		{
		 System.out.printf(item+" ");
		}
	 readFile.close();
	}
	
	public static void p_17() {
		long result = 0;
		Scanner readInput = new Scanner (System.in);
		ArrayList <Integer> numbers = new ArrayList<>();
		int size = Integer.parseInt(readInput.nextLine());
		String [] raw = readInput.nextLine().split(" ");
		for (String r:raw) numbers.add(Integer.parseInt(r));
		for (int i:numbers) result = (i + result)*113%10000007;
		System.out.print(result);
		readInput.close();
	}
	
	public static void p_18() throws FileNotFoundException{
		Scanner readFile = new Scanner (new File("18.txt"));
		int num;
		ArrayList <Float> result = new ArrayList<>();
		String[] raw = new String[2];
		num = Integer.parseInt(readFile.nextLine().trim());
		for (int i = 0;i<num;i++) {
			raw = readFile.nextLine().split("\\s");
			ArrayList <Integer> nums = new ArrayList<>();
			for(String s:raw) nums.add(Integer.parseInt(s));
			float root = 1.00000000f;
			for (int ii = 0; ii<nums.get(1);ii++) {
				root = (root+(float)nums.get(0)/root)/2;
			}
			result.add(root);
		}
		for (Float in:result) {
			System.out.print(String.format("%.7f", in)+" ");
		}
		
		readFile.close();
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		p_18();
		

	}

}
 