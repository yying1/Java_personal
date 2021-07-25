import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

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
		// for scanner read file throws filenot found exception is a must: https://stackoverflow.com/questions/19307622/java-says-filenotfoundexception-but-file-exists 
		 File f = new File("3.txt");
		 System.out.println(f.exists());
		 System.out.println(new File("3.txt").getAbsoluteFile());
		 System.out.println(new File("3.txt").canRead());
		 Scanner readFile = new Scanner(f);
		 String line;
		 line = readFile.nextLine();
		 
		 System.out.println(line);
		 readFile.close();
	}
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		p_3();
	}

}
