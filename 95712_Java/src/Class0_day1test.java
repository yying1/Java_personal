import java.util.*;
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
	
	public static void main(String[] args) {
		detectprime();

	}

}
