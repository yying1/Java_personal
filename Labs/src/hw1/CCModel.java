//Name: Frank Yue Ying, ID: yying2
package hw1;

//import Scanner and File packages to read file content
import java.util.Scanner;
import java.io.File;

public class CCModel {
	Case[] cases;
	String[] fileData;

	/**loadData() takes filename as a parameter,
	 * reads the file and loads all 
	 * data as a String for each row in 
	 * fileData[] array
	 * @param filename
	 */
	void loadData(String filename) {
		//write your code here
		//Create raw_file variable to store content from file as string
		String raw_file = "";
		
		//Read file content in to raw_file
		try {
		Scanner readFile = new Scanner(new File(filename));
		while (readFile.hasNext()){
			raw_file = raw_file.trim() + "\r\n" + readFile.nextLine();
		}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		//Split raw_file content into fileData Array
		fileData = raw_file.split("[\\r\\n]+");
	}

	/**loadCases() uses the data stored in fileData array
	 * and creates Case objects for each row.
	 * These cases are loaded into the cases array.
	 * Note that you may have to traverse the fileData array twice
	 * to be able to initialize the cases array's size.
	 */
	void loadCases() {
		//write your code here
		
		//Create variable to track case count in fileData
		int case_count = 0;
		
		//iterate fileData array to get case count
		for (@SuppressWarnings("unused") String s:fileData) {
			case_count++;
		}
		
		//initialize Case Array length
		cases = new Case[case_count];
		
		//iterate fileData array again to read through its content, and add to cases Array
		for (int index = 0;index < fileData.length;index++) {
			String[] CaseString = fileData[index].split("\\t");
			String casetype ="";
			if (CaseString[1].trim().contains("(Administrative)") | CaseString[1].trim().contains("(Federal)")) {
				casetype = CaseString[1].trim().substring(CaseString[1].trim().lastIndexOf("(")+1,CaseString[1].trim().length()-1);
			} else {
				casetype = "";
			}
			if (CaseString.length == 3) {
				cases[index] = new Case(CaseString[0], CaseString[1],casetype,CaseString[2]);
			} else {
				cases[index] = new Case(CaseString[0], CaseString[1],casetype,"");
			}
			
		}
		
	}
}
