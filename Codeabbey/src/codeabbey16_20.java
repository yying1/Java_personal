import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.*;
import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class codeabbey16_20 {
	
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
		Integer.parseInt(readInput.nextLine());
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
	
	public static void p_19() {
		File f = new File("19.txt");
		int cases;
		String raw;
		Pattern pattern = Pattern.compile("[\\[\\]{}()<>]", Pattern.CASE_INSENSITIVE);
		String result ="";
		String result_c;
		Hashtable<String, String> my_dict = new Hashtable<String, String>();
		my_dict.put("}", "{");
		my_dict.put("]", "[");
		my_dict.put(">", "<");
		my_dict.put(")", "(");
		try {
			Scanner readFile = new Scanner(f);
			cases = Integer.parseInt(readFile.nextLine());
			for (int c = 0;c<cases;c++) {
				raw = readFile.nextLine();
				Matcher matcher = pattern.matcher(raw);
				List<String> allMatches = new ArrayList<String>();
				while (matcher.find()){
					allMatches.add(matcher.group());
			      }
				String[] left = {"[","{","<","("};
				List<String> temp = new ArrayList<String>();
				result_c = "1";
				for (String i:allMatches) {
					if (Arrays.asList(left).contains(i)) {
						temp.add(i);
					}
					else {
						if (temp.isEmpty() || !temp.get(temp.size()-1).trim().equals(my_dict.get(i)) ) {
//							System.out.println(temp.isEmpty());
//							System.out.println(temp.get(temp.size()-1).trim().equals(my_dict.get(i)));
//							System.out.println(temp.get(temp.size()-1).trim());
//							System.out.println(my_dict.get(i));
							result_c = "0 ";
							break;
						}
						else if (temp.isEmpty() && !Arrays.asList(left).contains(i)) {
							result_c = "0 ";
							break;
						}
						else if (temp.get(temp.size()-1).equals(my_dict.get(i)))  {
							temp.remove(temp.size()-1);
						}
					}
				}
				if (temp.size() != 0) {
					result_c = "0";
				}
				result = result +result_c +" ";
			}
		System.out.println(result);
		readFile.close();
		} catch (Exception e) {
		      System.out.println("Something went wrong.");
	    };
	}
	
	public static void p_20() {
		char[] raw;
		char[] vowels = {'a', 'o', 'u', 'i', 'e', 'y'};
		int number;
		int count;
		String result= "";
		try {
			Scanner readFile = new Scanner(new File("20.txt"));
			number = Integer.parseInt(readFile.nextLine());
			for (int i = 0; i<number; i++) {
				raw = readFile.nextLine().toCharArray();
				count = 0;
				for (char a:raw) {
					for (char b:vowels) {
						if (a == b) {
							count ++;
							break;
						}
					}
				}
				result = result.trim() + " " + count;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println(result);	
	}
	
	
	public static void main(String[] args) throws FileNotFoundException {
		p_20();
	}

}
 