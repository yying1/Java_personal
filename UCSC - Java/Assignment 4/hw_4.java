import java.util.Scanner;

class DrawBox{
	static void drawhline(int hsize, String hstr) {
		int count = 0;
		while (count++ < hsize)
			System.out.print(hstr);
	}

	static void drawvline(int hsize, int vsize, String hstr, String vstr) {
		int count2 = 0;
		while (count2 ++ < vsize-2) {
			System.out.print(vstr);
			drawhline(hsize - 2, hstr);
			System.out.println(vstr);
		}
	}
	
	static void drawbox(int h, int v, String hstr, String vstr) { //a viod method can be used without calling the class
		drawhline(h,hstr);
		System.out.printf("\n");
		drawvline(h, v," ",vstr);
		drawhline(h,hstr);
		System.out.printf("\n");
	}
}

public class hw_4 {

	public static void countLoop(){
		int i = 0;
		while (i++ < 10) {
		System.out .println("Hello World: " + i);
		}
		System.out .println("Question 4.1.a loops executed 10 times");
		
		i = 0;
		while (++i < 10) {
		System.out .println("Hello World: " + i);
		}
		System.out .println("Question 4.1.b loops executed 9 times");
		
		while (++i < 10) {
			System.out .println("Hello World: " + i);
			}
		System.out .println("Question 4.1.c loops executed 0 times");
	}
	
	public static void drawBoxWhileLoop() {
		int hsize = 20;
		int vsize = 10;

		int count = 0;
		while (count++ < hsize)
			System.out.print("-");
		System.out.printf("\n");
		
		int count2 = 0;
		while (count2 ++ < vsize-2) {
			System.out.print("|");
			count = 0;
			while (count++ < hsize-2)
				System.out.print(" ");
			System.out.println("|");
		}
		
		count = 0;
		while (count++ < hsize)
			System.out.print("-");
		System.out.printf("\n");
		
	}
	
	public static void drawBoxDoWhileLoop() {
		int hsize = 20;
		int vsize = 10;

		int count = 0;
		do {
			System.out.print("-");
		}while (count++ < hsize-1);
		System.out.printf("\n");
		
		int count2 = 0;
		do {
			System.out.print("|");
			count = 0;
			do {
				System.out.print(" ");
			} while (count++ < hsize-2-1);
			System.out.println("|");
		} while (count2 ++ < vsize-2-1);
		
		count = 0;
		do {
			System.out.print("-");
		}while (count++ < hsize-1);
		System.out.printf("\n");
		
	}
	
	public static void drawBoxForLoop() {
		int hsize = 20;
		int vsize = 10;

		int count = 0;
		for (;count++ < hsize;)
		{
			System.out.print("-");
		}
		System.out.printf("\n");
		
		int count2 = 0;
		for (;count2 ++ < vsize-2;)
		{
			System.out.print("|");
			count = 0;
			for (;count++ < hsize-2;)
			{
				System.out.print(" ");
			}
			System.out.println("|");
		}
		
		count = 0;
		for (;count++ < hsize;)
		{
			System.out.print("-");
		}
		System.out.printf("\n");
		
	}
	
	public static void drawBoxAskVariables() {
		int hsize;
		int vsize;
		String hstr;
		String vstr;
		
		Scanner readInput = new Scanner(System.in);
		
		System.out.printf("Type Horizontal size, vertical size, horizontal string, and vertical string seperated by space ");
		hsize = readInput.nextInt();
		vsize = readInput.nextInt();
		hstr = readInput.next();
		vstr = readInput.next();
		
		DrawBox.drawbox(hsize, vsize, hstr, vstr);
		//readInput.close();

	}
	
	public static void drawBoxAskVariablesAndContinue() {
		int hsize;
		int vsize;
		String hstr;
		String vstr;
		char response;
		Scanner readInput = new Scanner(System.in);
		
		do {
			System.out.printf("Type Horizontal size, vertical size, horizontal string, and vertical string seperated by space ");
			hsize = readInput.nextInt();
			vsize = readInput.nextInt();
			hstr = readInput.next();
			vstr = readInput.next();
			
			DrawBox.drawbox(hsize, vsize, hstr, vstr);
			System.out.printf("Do you want to continue? y/n");
			response = readInput.next().charAt(0);
		} while (response != 'n');
		readInput.close();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		countLoop(); //4.1
		drawBoxWhileLoop(); //4.2
		drawBoxDoWhileLoop(); //4.3
		drawBoxForLoop(); //4.4
		drawBoxAskVariables(); //4.5
		drawBoxAskVariablesAndContinue(); //4.6
	}

}
