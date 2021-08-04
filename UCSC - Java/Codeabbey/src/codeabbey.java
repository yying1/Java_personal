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
	
	public static void p_9() throws FileNotFoundException {
		ArrayList <Integer> result = new ArrayList<>();
		Scanner readFile = new Scanner(new File("9.txt"));
		int number = Integer.parseInt(readFile.nextLine());
		for ( int a = 0; a<number; a++)
		{
			String [] items = readFile.nextLine().split(" ");
			int t1 = Integer.parseInt(items[0]);
			int t2 = Integer.parseInt(items[1]);
			int t3 = Integer.parseInt(items[2]);
			ArrayList <Integer> ints = new ArrayList<Integer>();
			for (String s : items) ints.add(Integer.valueOf(s));
			if ((t1+t2)>=t3 && (t1+t3)>=t2 && (t2+t3)>=t1)
			{
				result.add(1);
			} else 
			{
				result.add(0);
			}
		}
		readFile.close();
		for (int b:result)
		{
			System.out.print(b+" ");
		}
	}
	
	public static void p_10() throws FileNotFoundException{
		ArrayList <String> result = new ArrayList<>();
		Scanner readFile = new Scanner(new File("10.txt"));
		int number = Integer.parseInt(readFile.nextLine());
		for ( int a = 0; a<number; a++)
		{
			String [] items = readFile.nextLine().split(" ");
			int n1 = Integer.parseInt(items[0]);
			int n2 = Integer.parseInt(items[1]);
			int n3 = Integer.parseInt(items[2]);
			int n4 = Integer.parseInt(items[3]);
			// ArrayList <Integer> ints = new ArrayList<Integer>();
			//for (String s : items) ints.add(Integer.valueOf(s));
			int value_a = (n4-n2)/(n3-n1);
			int value_b = n2 - value_a*n1;
			result.add("("+value_a);
			result.add(value_b+")");
		}
		readFile.close();
		for (String b:result)
		{
			System.out.print(b+" ");
		}
		
	}
	
	public static void p_11() throws FileNotFoundException{
		ArrayList <Integer> result = new ArrayList<>();
		Scanner readFile = new Scanner(new File("11.txt"));
		int number = Integer.parseInt(readFile.nextLine());
		for ( int a = 0; a<number; a++)
		{
			String [] items = readFile.nextLine().split(" ");
			int n1 = Integer.parseInt(items[0]);
			int n2 = Integer.parseInt(items[1]);
			int n3 = Integer.parseInt(items[2]);

			
			int sum_value = n1*n2+n3;
			items = String.valueOf(sum_value).split("");
			ArrayList <Integer> ints = new ArrayList<Integer>();
			for (String s : items) ints.add(Integer.valueOf(s));
			int digit_sum = 0;
			for (Integer i : ints) digit_sum+= i;
			result.add(digit_sum);
		}
		readFile.close();
		for (Integer b:result)
		{
			System.out.print(b+" ");
		}
		
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		p_11();
	}

}
