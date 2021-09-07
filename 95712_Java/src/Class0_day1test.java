import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import java.util.Arrays;
//import java.util.List;

public class Class0_day1test {
	
	public static void first100prime() {
		int n = 0;
		int start = 1;
		boolean status;
		ArrayList <Integer> primenums = new ArrayList<>();
		do {
			do {
				start +=1;
				status = false;
//				System.out.print(start);
				for (int a = 2;a<start;a++) {
					if (start % a == 0) {
						status = true;
						break;
					}
				}
				
			} while (status == true);
			n+=1;
			primenums.add(start);
		} while (n < 100);
		
		for (int item:primenums)
		{
		 System.out.printf(item+" ");
		}
		
	}

	public static void printsortedint() {
		Scanner readInput = new Scanner(System.in);
		System.out.print("Please input your list of integers seperated by spaces:");
		String [] raw_list = readInput.nextLine().split(" ");
		ArrayList <Integer> nums = new ArrayList<>();
		for (String s:raw_list) nums.add(Integer.parseInt(s));
		Collections.sort(nums);
		for (int item:nums) System.out.printf(item+" ");
		readInput.close();
	}
	
	public static void detectprime() {
		Scanner readInput = new Scanner(System.in);
		int num;
		int a = 0;
		boolean status = false;
		String keepgoing = "y";
		do {
		System.out.print("Please give me one number (must be greater than 2):");
		num = readInput.nextInt();
		for (a = 2;a<num;a++) {
			if (num % a == 0) {
				status = true;
				break;
			}
		}
		if (status == true) System.out.println(num+" is not a prime number: "+a);
		else {System.out.println(num+" is a prime number");}
		System.out.println("Continue ? (y/n)");
		keepgoing = String.valueOf(readInput.next().trim().charAt(0));
//		keepgoing = String.valueOf(keepgoing.trim().toLowerCase());
		} while (keepgoing.equals("y"));
		readInput.close();
	}
	
	public static void palindrometest() { 
		Scanner readInput = new Scanner(System.in);
		char[] word;
		int len;
		word = readInput.nextLine().trim().toCharArray();
		len = word.length;
		boolean status = true;
		for (int i = 0;i<len/2;i++) {
			if (word[i] != word[len-1-i]) {
				status = false;
				break;
			}
		}
		System.out.print(status);
		readInput.close();
	}
	
	public static void countword() throws FileNotFoundException {
		File f = new File("test.txt");
		String raw;
		Scanner readfile = new Scanner(f);
		Scanner readInput = new Scanner(System.in);
		long count;
		raw = readfile.useDelimiter("\\A").next();
		
		System.out.println("Please input your search word:");
		String word = readInput.nextLine().trim();
		Pattern pattern = Pattern.compile(word, Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(raw);
	    count = matcher.results().count();
		System.out.print(count);
		readfile.close();
		readInput.close();
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		countword();

	}

}
