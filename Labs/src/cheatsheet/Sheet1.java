package cheatsheet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;



public class Sheet1 implements Comparable<Sheet1>{
	
	static String Name;
	static Map<String, List<Sheet1>> sheetMap;  
	static List<String> sheets;
	static int thread;
	static boolean window;
	
	public static void main(String[] args) {
		//Reading a file
			//Need to import at least 3 packages: java.io.File, java.io.FileNotFoundException, java.util.Scanner
			String raw_lines = "";
			String filename = "";
			int count_row = 0;
			try {
				Scanner readfile = new Scanner(new File (filename));
	 			while (readfile.hasNext()) {
	 				String line = readfile.nextLine();
	 				raw_lines = raw_lines.trim()+"\n"+line;
	 				count_row++;
				}
	 			//Use this to read the entire text file instead of line by line
	 			raw_lines = readfile.useDelimiter("\\Z").next();
				readfile.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		//Reading multiple inputs from Scanner
			Scanner input = new Scanner(System.in);
			input.nextLine(); //add this to clear for the next nextLine reading
			input.close();
		
		//String Manipulation
			String[] line_words = raw_lines.split("[)]"); //Use [)] when spiting by ()
			//pay attention to the line breaks in String
			String[] words = raw_lines.split("\\r\\n");
			//String to char array
			char[] clue_array = Name.toCharArray();
			//Char Array to String
			Name = String.valueOf(clue_array);
		
		//Generating random number and getting time
			int rand09 = 0+(int)(Math.random()*9); //getting a number from 0 to 9 (max-min+1)
			int randindex = 0 + (int)(Math.random()*line_words.length);
			long start = System.currentTimeMillis();
			int max_time = 10;
			long time_left = max_time-(Math.round((System.currentTimeMillis()-start)/1000.0));
			
		
		//Print with formatting
			int count = 0;
			System.out.printf("%3d. %-15s Number of movies: %,6d%n", ++count, raw_lines, filename);
		
		//iterating hash map and set
			for (Map.Entry<String, List<Sheet1>> entry :sheetMap.entrySet()) {
				entry.getValue();
				entry.getKey();
				if (entry.getKey().contains("test")) {}
			}
			
		//Create Thread and run those
			Thread t1 = new Thread(new QueueManager(0));
			Thread t2 = new Thread(new QueueManager(0));
			t1.setName("TicketWindow");
			t2.setName("QueueManager");
			t1.start();
			t2.start();
			try {
				t1.join();
				t2.join();
			} catch (Exception e) {
				System.out.println(e);
			}
	}
	//Creating Methods 
	
		//Hashing and equals methods to compare objects 
			public int hashCode() {
				return Objects.hash(this.Name);
			}
			
			@Override
			public boolean equals(Object o) {
				if (o == null) return false;
				if (this == o) return true;//check memory location
				if (getClass() != o.getClass()) return false;
				Sheet1 s = (Sheet1) o;
				return this.Name.equals(s.Name);
			}
	
		
		//Comparable Methods to be used with Collections.sort(List)
			@Override
			public int compareTo(Sheet1 o) {
				return this.Name.compareTo(o.Name);
			}
			
}
		
		//Create Comparator Class to handle custom comparison with Collections.sort(List, new SheetComparator())
			class SheetComparator implements Comparator<Sheet1> {
			
				@Override
				public int compare(Sheet1 s1, Sheet1 s2) {
					int c = 0;
				    c = s2.sheets.size() - s1.sheets.size(); //Larger one goes first
				    if (c == 0) {
				    	c = s1.Name.compareTo(s2.Name); //alphabetically 
				    }
				    return c;
				}
				
			}
			
			
		//Create Runnable Class to handle multi-threading
			class QueueManager implements Runnable{
				int customerDelay;
				int balkCount;
				QueueManager(int customerdelay){
					this.customerDelay = customerdelay;
				}
				@Override
				public void run() {
					QueueManager qm = new QueueManager(Sheet1.thread);
						while (Sheet1.window == true) {
							try {
							synchronized(Sheet1.sheets) {
									//take action here
								}
							Thread.sleep(0 + (int)(Math.random() * (qm.customerDelay + 1)));
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
				}
			}

