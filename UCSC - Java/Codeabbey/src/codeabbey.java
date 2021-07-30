import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class codeabbey {
	public static void p_1() {
		int a;
		int b;
		int sum_ab;
		Scanner readInput = new Scanner(System.in);
		a = readInput.nextInt();
		b = readInput.nextInt();
		sum_ab = a+b;
		System.out.printf("The sum is:\n"+ sum_ab);
		readInput.close();
	}
	
	public static void p_2() {
		int a;
		int sum = 0;
		Scanner readInput = new Scanner(System.in);
		int [] raw_list = new int [readInput.nextInt()];
		readInput.nextLine();
		for (a = 0;a<raw_list.length;a++)
		{
			raw_list[a] = readInput.nextInt();
		}
		for (int num:raw_list)
		{
			sum = sum +num;
		}
		System.out.print(sum);
		readInput.close();
	}
	
	public static void p_3() throws FileNotFoundException {
		// for scanner read file throws filenotfound exception is a must: https://stackoverflow.com/questions/19307622/java-says-filenotfoundexception-but-file-exists 
		int number; 
		int sum = 0;
		ArrayList <Integer> result = new ArrayList<>();
		String line;
		File f = new File("3.txt");
		 //System.out.println(f.exists());
		 //System.out.println(new File("3.txt").getAbsoluteFile());
		 //System.out.println(new File("3.txt").canRead());
		 Scanner readFile = new Scanner(f);
		 number = Integer.parseInt(readFile.nextLine());
		 
		 for (int a = 0; a < number; a++)
		 {
			 line = readFile.nextLine();
			 String[] items = line.split(" ");
			 sum = 0;
			 for (int b = 0;b<items.length;b++)
				{
				 sum += Integer.parseInt(items[b]) ;
				}
			 result.add(sum);
		 }
		 
		 for (int item:result)
			{
			 System.out.printf(item+" ");
			}
		 readFile.close();
	}
	
	public static void p_4() throws FileNotFoundException {
		// if else statement: https://www.w3cschool.cn/java/java-if.html 
		int number; 
		int min = 0;
		ArrayList <Integer> result = new ArrayList<>();
		String line;
		File f = new File("4.txt");
		Scanner readFile = new Scanner(f);
		number = Integer.parseInt(readFile.nextLine());
		for (int a =0 ; a<number; a++)
		{
			line = readFile.nextLine();
			String[] items = line.split(" ");
			if (Integer.parseInt(items[0]) > Integer.parseInt(items[1]))
			{
				min = Integer.parseInt(items[1]);
			} else
			{
				min = Integer.parseInt(items[0]);
			}
			result.add(min);
		}
		
		for (int item:result)
		{
		 System.out.printf(item+" ");
		}
	 readFile.close();
	}
	
	public static void p_5() throws FileNotFoundException {
		int number; 
		int min = 0;
		ArrayList <Integer> result = new ArrayList<>();
		String line;
		File f = new File("5.txt");
		Scanner readFile = new Scanner(f);
		number = Integer.parseInt(readFile.nextLine());
		for (int a =0 ; a<number; a++)
		{
			line = readFile.nextLine();
			String[] items = line.split(" ");
			if (Integer.parseInt(items[0]) > Integer.parseInt(items[1]))
			{
				if (Integer.parseInt(items[1]) > Integer.parseInt(items[2]))
				{
					min = Integer.parseInt(items[2]);
				} else
				{
				min = Integer.parseInt(items[1]);
				}
			} else
			{
				if (Integer.parseInt(items[0]) > Integer.parseInt(items[2]))
				{
					min = Integer.parseInt(items[2]);
				} else
				{
					min = Integer.parseInt(items[0]);
				}
			}
			result.add(min);
		}
		
		for (int item:result)
		{
		 System.out.printf(item+" ");
		}
	 readFile.close();
		
	}
	
	public static void p_6() throws FileNotFoundException {
		int number; 
		int round = 0;
		ArrayList <Integer> result = new ArrayList<>();
		String line;
		File f = new File("6.txt");
		Scanner readFile = new Scanner(f);
		number = Integer.parseInt(readFile.nextLine());
		for (int a =0 ; a<number; a++)
		{
			line = readFile.nextLine();
			String[] items = line.split(" ");
			double raw = Double.parseDouble(items[0])/Double.parseDouble(items[1]);
			if (raw > 0)
			{
				raw += 0.5;
			} else
			{
				raw -= 0.5;
			}
			//System.out.printf(raw+" | ");
			round = (int)raw;
			result.add(round);
		}
		
		for (int item:result)
		{
		 System.out.printf(item+" ");
		}
	 readFile.close();
	}
	
	public static void p_7() throws FileNotFoundException {
		long temp;
		ArrayList <Long> result = new ArrayList<>();
		Scanner readInput = new Scanner(System.in);
		String line = readInput.nextLine();
		String [] raw_list = line.split(" ");
		for (int a = 1; a< raw_list.length; a++)
		{
			temp= Math.round((Double.parseDouble(raw_list[a])-32)/1.8);
			result.add(temp);
		}
		for (long item:result)
		{
		 System.out.printf(item+" ");
		}
		readInput.close();
	}
	
	public static void p_8() throws FileNotFoundException {
		int number;
		int sum;
		ArrayList <Integer> result = new ArrayList<>();
		Scanner readFile = new Scanner(new File("8.txt"));
		number = Integer.parseInt(readFile.nextLine());
		for (int a = 0; a<number; a++) 
		{
			String [] items = readFile.nextLine().split(" ");
			sum = (Integer.parseInt(items[0]) + Integer.parseInt(items[0])+ Integer.parseInt(items[1])*(Integer.parseInt(items[2])-1))*Integer.parseInt(items[2])/2;
			result.add(sum);
		}
				
		for (int a : result)
		{
			System.out.printf(a+" ");
		}
		readFile.close();
	}
	
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		p_8();
	}

}
