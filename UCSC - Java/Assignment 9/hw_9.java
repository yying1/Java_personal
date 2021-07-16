
import java.util.*;
import java.io.*;

class DiskIO { //9.1
	public static void main(String[] args) { // this is main
		Boolean reEnter;
		String Input_file;
		String Output_file;
		do {
		try {
			DiskIO.processCLArguments(args);
			Input_file = args[0];
			Output_file = args[1];
			System.out.println("Input will be read from: "+ Input_file);
			System.out.println("Output will be written into:  "+ Output_file);
			reEnter = false;
		}
		catch (InputMismatchException e) {
			System.out.println("Usage: java DiskIO inputFile outputFile");
			reEnter = true;
		}
		catch (IOException e) { 
			System.out.println("Usage: java DiskIO inputFile outputFile");
			reEnter = true;
		}
		} while (reEnter == true);
		DiskIO processfile = new DiskIO();
		processfile.processInputOutputFiles(args);
	}
	
	public static void processCLArguments(String[] args) throws IOException { //determine if argument has two strings
			
		if (args.length != 2) {
			throw new InputMismatchException();
			}
		}
	
	public String processStringName(String inputLine) { //find the student name using tokenizer
		StringTokenizer myTokenizer;
		myTokenizer = new StringTokenizer(inputLine,",\n");
		String Name = "";
		Name = myTokenizer.nextToken();
		return Name;
	}
	
	public String processStringGrade(String inputLine) { //use student name to get grade
		StringTokenizer myTokenizer;
		myTokenizer = new StringTokenizer(inputLine,",\n");
		String Grade ="";
		String Name = "";
		Name = myTokenizer.nextToken();
		Grade = inputLine.replace(Name+",","");
		Grade = Grade.replace(",",":");
		return Grade;
	}
	
	public void processInputOutputFiles(String[] args) {//read input files and write file
		int student_number = 0;
		String Name;
		String Grade;
		try {
			FileReader filereader = new FileReader(args[0]);
			PrintWriter textprintstream = null;
			textprintstream = new PrintWriter(new FileOutputStream(args[1],true));
			BufferedReader finalStream = new BufferedReader (filereader); 
			String s;
			while ((s = finalStream.readLine()) != null) {
				System.out.println("Student #: "+ ++student_number +" "+s);
				Grade = processStringGrade(s);
				Name = processStringName(s);
				textprintstream.write("Student # "+ student_number +" is: "+"\"" +Name+"\""+"whose raw scores are: "+Grade+"\n");
				}
			textprintstream.close();
			finalStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

public class hw_9 {//this only serves as the header
	}

}
