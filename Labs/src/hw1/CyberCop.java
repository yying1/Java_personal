//Name: Frank Yue Ying, ID: yying2
package hw1;

import java.util.Arrays;
import java.util.Scanner;

public class CyberCop {

	public static final String DATAFILE = "data/FTC-cases-TSV.txt";
	CCModel ccModel = new CCModel();
	SearchEngine searchEngine = new SearchEngine();

	Scanner input = new Scanner(System.in);

	/**main() instantiates CyberCop and then invokes dataManager's loadData
	 * and loadCases() methods
	 * It then invokes showMenu to get user input
	 * @param args
	 */
	//Do not change this method
	public static void main(String[] args) {
		CyberCop cyberCop = new CyberCop();
		cyberCop.ccModel.loadData(DATAFILE);
		cyberCop.ccModel.loadCases();
		cyberCop.showMenu();
	}

	/**showMenu() shows the menu. 
	 * Based on the user choice, it invokes one of the methods:
	 * printSearchResults(), printCaseTypeSummary(), or printYearwiseSummary()
	 * The program exits when user selects Exit option. 
	 * See the hand-out for the expected layout of menu-UI
	 */
	void showMenu() {
		//write your code here
		//Produce Menu options
		System.out.println("*** Welcome to CyberCop! ***");
		System.out.println("1. Search cases for a company");
		System.out.println("2. Search cases in a year");
		System.out.println("3. Search case number");
		System.out.println("4. Print Case-type Summary");
		System.out.println("5. Print Year-wise Summary");
		System.out.println("6. Exit");
		
		//get user option from input 
		String option = input.nextLine().trim();
		
		//switch to different methods based on the option given
		switch (option) {
		case "1": 
			System.out.println("--------------------------------------------------------------------");
			System.out.println("Enter search string");
			printSearchResults(input.nextLine().trim(),ccModel.cases);
			break;
		case "2": 
			System.out.println("--------------------------------------------------------------------");
			System.out.println("Enter search year as YYYY");
			printSearchResults(input.nextLine().trim(),ccModel.cases);
			break;
		case "3": 
			System.out.println("--------------------------------------------------------------------");
			System.out.println("Enter case number");
			printSearchResults(input.nextLine().trim(),ccModel.cases);
			break;
		case "4":
			printCaseTypeSummary();
		break;
		case "5":
			printYearwiseSummary();
		break;
		case "6":break;
		//added default option in case there is an error in input
		default: System.out.println("Enter errored. Exiting...");
		break;
		}
		
	}

	/**printSearcjResults() takes the searchString and array of cases as input
	 * and prints them out as per the format provided in the handout
	 * @param searchString
	 * @param cases
	 */
	void printSearchResults(String searchString, Case[] cases) {
		//write your code here
		Case[] result_cases;
		
		//Try cast searchString into number to determine search type 
		try {
			Integer.parseInt(searchString.trim());
			if (searchString.trim().length() == 4) {
				result_cases = searchEngine.searchYear(searchString,cases);
			} else {
				result_cases = searchEngine.searchCaseNumber(searchString,cases);
			}
		} catch (Exception e) {
			try {
			Integer.parseInt(searchString.trim().replaceAll("\\s", ""));
			result_cases = searchEngine.searchCaseNumber(searchString,cases);
			} catch (Exception e2) {
				result_cases = searchEngine.searchTitle(searchString,cases);
			}
		}
		
		// print result_cases generated from search engine methods
		if (result_cases == null) {
			System.out.println("--------------------------------------------------------------------");
			System.out.println("Sorry, no search results found for " + searchString);
			System.out.println("--------------------------------------------------------------------");
		} else {
			System.out.println(result_cases.length + " case(s) found for "+ searchString);
			System.out.println("--------------------------------------------------------------------");
			System.out.printf("%2s. %10s %50s     %15s     %15s%n", "#", "Last update","Case Title","Case Type","Case/File Number");
			System.out.println("--------------------------------------------------------------------");
			int count = 1;
			for (Case i:result_cases) {
				System.out.printf("%2d. %10s %50s     %15s     %15s%n", count, i.caseDate,i.caseTitle,i.caseType,i.caseNumber);
				count++;
			}
			System.out.println("--------------------------------------------------------------------");
		}
		
		
	}

	/**printCaseTypeSummary() prints a summary of
	 * number of cases of different types as per the 
	 * format given in the handout.
	 */
	void printCaseTypeSummary() {
		//write your code here
		//Create variables to store case type and their counts
		String[] CaseTypeSummary;
		String CaseType = "";
		String[] CaseTypeArray;
		
		//Identify distinct case types
		for (Case c:ccModel.cases) {
			if (c.caseType == "") {
				if (!CaseType.contains("unknown")) {
					CaseType = CaseType.trim()+" "+"unknown";
				}
			}
			if (!CaseType.contains(c.caseType)) {
				CaseType = CaseType.trim()+" "+c.caseType;
			}
		}
		CaseTypeSummary = new String[CaseType.split(" ").length];
		CaseTypeArray = CaseType.split(" ");
		
		//sort case types for ordered result
		Arrays.sort(CaseTypeArray);
		
		//iterate distinct case type to collect counts
		int counter = 0;
		for (String d:CaseTypeArray) {
			String search;
			if (d.trim().equals("unknown")) {
				search = "";
			} else {
				search = d;
			}
			int count = 0;
			for (Case e:ccModel.cases) {
				if (e.caseType.equals(search)) {
					count++;
				}
			}
			CaseTypeSummary[counter] = String.valueOf(count);
			counter++;
		}
		
		//Print results
		System.out.println("--------------------------------------------------------------------");
		System.out.println("*** Case Type Summary Report ***");
		int caseindex = 0;
		for (String d:CaseTypeArray) {
			System.out.println("No. of "+d+" cases: "+CaseTypeSummary[caseindex]);
			caseindex++;
		}
		System.out.println("--------------------------------------------------------------------");
	}
	
	/**printYearWiseSummary() prints number of cases in each year
	 * as per the format given in the handout
	 */
	void printYearwiseSummary() {
		//write your code here
		
		//create variables to store years and counts 
		String [] years;
		int[] year_counts;
		String years_raw = "";
		int count_years=0;
		
		//Determine distinct years 
		for (Case e:ccModel.cases) {
			if (!years_raw.contains(String.valueOf(e.getYear()))) {
				years_raw = years_raw.trim()+" "+String.valueOf(e.getYear());
				count_years++;
			}
		}
		years = years_raw.split(" ");
		Arrays.sort(years);
		year_counts = new int[count_years];
		
		//count the cases per distinct year
		int year_index = 0;
		for (String y:years) {
			count_years = 0; 
			for (Case f:ccModel.cases) {
				if (y.equals(String.valueOf(f.getYear()))) {
					count_years++;
				}
			}
			year_counts[year_index] = count_years;
			year_index++;
		}
		
		//Produce a string variable to store the year summary in desired format
		int row_count = 0;
		String Year_summary="";
		for (year_index = 0;year_index<years.length;year_index++) {
			String counts;
			if (year_counts[year_index] < 10) {
				counts = " "+String.valueOf(year_counts[year_index]);
			} else {
				counts = String.valueOf(year_counts[year_index]);
			}
			
			if (row_count % 5 != 0) {
				Year_summary = years[year_index]+": "+counts+"\t"+Year_summary;
			} else {
				Year_summary = years[year_index]+": "+counts+"\r\n"+Year_summary;
			}
			row_count++;
			
		}
		
		//Print the years summary
		System.out.println("--------------------------------------------------------------------");
		System.out.println("\t\t\t" + "*** Year-wise Summary Report ***");
		System.out.println("\t\t\t" + "*** Number of FTC cases per year ***");
		System.out.println();
		for (String l:Year_summary.split("[\r\n]+")) {
			System.out.println("\t"+l);
		}
		System.out.println("--------------------------------------------------------------------");
	}
	
	
}
