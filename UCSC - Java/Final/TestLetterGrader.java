import java.util.*;
import java.io.*;

//use this to sort by names
class sortByNames implements Comparator { 
    public int compare(Object s1, Object s2) {//must implement method
    	String String1 = String.valueOf(s1);
    	String String2 = String.valueOf(s2);
       return (String1.compareTo(String2)) ;
    }
}

class LetterGrader{
	private String Input_file;
	private String Output_file;
	private boolean reEnter;
	String Score ="";
	String Grade ="";
	private ArrayList<Integer> quiz1List = new ArrayList();
	private ArrayList<Integer> quiz2List = new ArrayList();
	private ArrayList<Integer> quiz3List = new ArrayList();
	private ArrayList<Integer> quiz4List = new ArrayList();
	private ArrayList<Integer> midterm1List = new ArrayList();
	private ArrayList<Integer> midterm2List = new ArrayList();
	private ArrayList<Integer> finalList = new ArrayList();
	ArrayList<String> NameWithGrade = new ArrayList();

	public LetterGrader(String input_file, String output_file) { // use this to read argument file names
		do {
			try {
			Input_file = input_file;
			Output_file = output_file;
			reEnter = false;
			}
			catch (InputMismatchException e) {
				System.out.println("Error: make sure you input two file names seperated with space");
				reEnter = true;
			}
			catch (Exception e) { 
				System.out.println("Error: make sure you input two file names seperated with space");
				reEnter = true;
			}
		} while (reEnter == true);
		
	}
	
	public void readScore() { //use this to read score from input file, line by line
		try {
			FileReader filereader = new FileReader(Input_file);
			BufferedReader finalStream = new BufferedReader (filereader); 
			String s;
			while ((s = finalStream.readLine()) != null) {
				Score = Score + "\n"+s;
				}
			finalStream.close();
		} catch (Exception e) {
			System.out.printf("Error: failed to read file %s",Input_file);
		}
	}

	public void calcLetterGrade() {// use this to process read score raw string into student, grades and score summary
		BufferedReader bufReader = new BufferedReader(new StringReader(Score.trim()));
		String line;
		String Name = "";
		String LetterGrade = "";
		//read raw score string line by line, uses processGrade method to do the calculations
		try {
			while( (line=bufReader.readLine()) != null )
			{
				Name = line.substring(0, line.indexOf(','));
				LetterGrade = processGrade(line);
				NameWithGrade.add(Name+":     "+LetterGrade);
			}
		} catch (IOException e) {
			System.out.printf("Error: failed to split data from %s",Input_file);
		}
	}
	
	private String processGrade(String line) {// this is used to calculate grades, group grades by tests, and return letter grade
		String LetterGrade = ""; // this will be used to store letter grade from the line
		double finalScore; // this will be the % based score of the student
		// use these to record all grades
		int quiz1;
		int quiz2;
		int quiz3;
		int quiz4;
		int midterm1;
		int midterm2;
		int finalgrade;
		//convert line to arraylist
		List<String> GradeList = new ArrayList (Arrays.asList(line.split(",")));
		//assign array items to each grade
		quiz1 = Integer.valueOf(GradeList.get(1).trim());
		quiz2 = Integer.valueOf(GradeList.get(2).trim());
		quiz3 = Integer.valueOf(GradeList.get(3).trim());
		quiz4 = Integer.valueOf(GradeList.get(4).trim());	
		midterm1 = Integer.valueOf(GradeList.get(5).trim());
		midterm2 = Integer.valueOf(GradeList.get(6).trim());
		finalgrade = Integer.valueOf(GradeList.get(7).trim());
		//add grades to each test arraylist
		quiz1List.add(quiz1);
		quiz2List.add(quiz2);
		quiz3List.add(quiz3);
		quiz4List.add(quiz4);
		midterm1List.add(midterm1);
		midterm2List.add(midterm2);
		finalList.add(finalgrade);
		//calculate letter grade
		finalScore = quiz1 * .10 + quiz2 * .10 + quiz3 * .10 + quiz4 * .10 + midterm1 * .20 + midterm2 * .15 + finalgrade * .25;
		if (finalScore >= 90)
			LetterGrade = "A";
		else if (finalScore >= 80)
			LetterGrade = "B";
		else if (finalScore >= 70)
			LetterGrade = "C";
		else if (finalScore >= 60)
			LetterGrade = "D";
		else if (finalScore <= 59)
			LetterGrade = "F";	
	return LetterGrade;		
	}

	public void printGrade() {// used to write into output data file
		Collections.sort(NameWithGrade, new sortByNames());
		PrintWriter textprintstream = null;
		try {
			textprintstream = new PrintWriter(new FileOutputStream(Output_file,true));
			textprintstream.write("Letter grade for "+NameWithGrade.size()+" students given in "+Input_file+" file is:"+"\n");
			for (int i =0; i<NameWithGrade.size();i++ )
				textprintstream.write(NameWithGrade.get(i).toString()+"\n");
			textprintstream.close();
		} catch (FileNotFoundException e) {
			System.out.printf("Error: failed to write file %s",Output_file);
		} 
	}
	
	private double calculateAverage(ArrayList<Integer> inputArray) {// built this method to calculate average grade for each test
		double averageValue = 0;
		int total = 0;
		for(int i = 0; i < inputArray.size(); i++)	    
	        total += inputArray.get(i);
		averageValue = total/inputArray.size();
		return averageValue;
	}
	
	private int calculateMin(ArrayList<Integer> inputArray) {// built this method to calculate minimum grade for each test
		int minValue = 100;
		for(int i = 0; i < inputArray.size(); i++)	    
	        if (inputArray.get(i)<minValue) {
	        	minValue = inputArray.get(i);
	        }
		return minValue;
	}
	
	private int calculateMax(ArrayList<Integer> inputArray) {// built this method to calculate maximum grade for each test
		int maxValue = 0;
		for(int i = 0; i < inputArray.size(); i++)	    
	        if (inputArray.get(i)>maxValue) {
	        	maxValue = inputArray.get(i);
	        }
		return maxValue;
	}
	
	public void displayAverages() {// this is used to display console results 
		
		System.out.println("Letter grade has been calculated for students listed in input file "+Input_file+" and written to "+Output_file);
		System.out.println("Here is the class averages");
		System.out.printf("          \tQ1\tQ2\tQ3\tQ4\tMidI\tMidII\tFinal\n");
		System.out.printf("Average:  \t"+calculateAverage(quiz1List)+"\t"+calculateAverage(quiz2List)+"\t"+calculateAverage(quiz3List)+"\t"+calculateAverage(quiz4List)+"\t"+calculateAverage(midterm1List)+"\t"+calculateAverage(midterm2List)+"\t"+calculateAverage(finalList)+"\n");
		System.out.printf("Minimum:  \t"+calculateMin(quiz1List)+"\t"+calculateMin(quiz2List)+"\t"+calculateMin(quiz3List)+"\t"+calculateMin(quiz4List)+"\t"+calculateMin(midterm1List)+"\t"+calculateMin(midterm2List)+"\t"+calculateMin(finalList)+"\n");
		System.out.printf("Maximum:  \t"+calculateMax(quiz1List)+"\t"+calculateMax(quiz2List)+"\t"+calculateMax(quiz3List)+"\t"+calculateMax(quiz4List)+"\t"+calculateMax(midterm1List)+"\t"+calculateMax(midterm2List)+"\t"+calculateMax(finalList)+"\n");
	}

	public void doCleanup() {//clean up the program
		System.out.println("Press Enter key to continue...");
		try {
			System.in.read();
		}
		catch (Exception e) { 
			}	
		//nothing here since all file and resource close are handled else where
	}
}

public class TestLetterGrader {
	
	public static void main(String[] args) {
		LetterGrader letterGrader = new LetterGrader(args[0], args[1]);//get input arguments
		letterGrader.readScore(); //reads score and stores the data in member variables
		letterGrader.calcLetterGrade(); //determines letter grade and stores information
		letterGrader.printGrade(); //writes the grade in output file
		letterGrader.displayAverages(); //displays the averages in console
		letterGrader.doCleanup(); //use it to close files and other resources
	}

}
