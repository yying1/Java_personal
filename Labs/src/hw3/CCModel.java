//Frank Yue Ying (yying2)
package hw3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import java.io.FileWriter;
import java.io.IOException;

public class CCModel {
	ObservableList<Case> caseList = FXCollections.observableArrayList(); 			//a list of case objects
	ObservableMap<String, Case> caseMap = FXCollections.observableHashMap();		//map with caseNumber as key and Case as value
	ObservableMap<String, List<Case>> yearMap = FXCollections.observableHashMap();	//map with each year as a key and a list of all cases dated in that year as value. 
	ObservableList<String> yearList = FXCollections.observableArrayList();			//list of years to populate the yearComboBox in ccView

	/** readCases() performs the following functions:
	 * It creates an instance of CaseReaderFactory, 
	 * invokes its createReader() method by passing the filename to it, 
	 * and invokes the caseReader's readCases() method. 
	 * The caseList returned by readCases() is sorted 
	 * in the order of caseDate for initial display in caseTableView. 
	 * Finally, it loads caseMap with cases in caseList. 
	 * This caseMap will be used to make sure that no duplicate cases are added to data
	 * @param filename
	 */
	void readCases(String filename) {
		//write your code here
		CaseReaderFactory crf = new CaseReaderFactory();
		List <Case> cases = crf.createReader(filename).readCases();
		Collections.sort(cases);
		for (Case c:cases) {
			caseList.add(c);
			caseMap.put(c.getCaseNumber(),c);
		}
	}

	/** buildYearMapAndList() performs the following functions:
	 * 1. It builds yearMap that will be used for analysis purposes in Cyber Cop 3.0
	 * 2. It creates yearList which will be used to populate yearComboBox in ccView
	 * Note that yearList can be created simply by using the keySet of yearMap.
	 */
	void buildYearMapAndList() {
		//write your code here
		//yearList.add("");
		for (Case c:caseMap.values()) {
			String year= c.getCaseDate().split("-")[0].trim();
			if (!yearList.contains(year)) {
				yearList.add(year);
				List <Case> year_cases = new ArrayList<>();
				year_cases.add(c);
				yearMap.put(year,year_cases);
			} else {
				List <Case> year_cases= yearMap.get(year);
				year_cases.add(c);
				yearMap.put(year,year_cases);
			}
		}
		Collections.sort(yearList);
	}

	/**searchCases() takes search criteria and 
	 * iterates through the caseList to find the matching cases. 
	 * It returns a list of matching cases.
	 */
	List<Case> searchCases(String title, String caseType, String year, String caseNumber) {
		//write your code here
		List<Case> search_result = new ArrayList<>();
		for (Case c:caseList) {
			if (!(title == null || title.equals("")) && !c.getCaseTitle().toLowerCase().contains(title.toLowerCase())) {continue;}
			else if (!(caseType== null || caseType.equals("")) && !c.getCaseType().toLowerCase().contains(caseType.toLowerCase())) {continue;}
			else if (!(year == null || year.equals("")) && !c.getCaseDate().toLowerCase().contains(year.toLowerCase())) {continue;}
			else if (!(caseNumber== null || caseNumber.equals("")) && !c.getCaseNumber().toLowerCase().contains(caseNumber.toLowerCase())) {continue;}
			else {
				search_result.add(c);
			}
		}
		return search_result;
	}
	
	boolean writeCases(String filepath){
		try {
			FileWriter out = new FileWriter(filepath);
			for(Case c: caseList) {
				String CaseString = c.getCaseDate()+" \t"+c.getCaseTitle()+" \t"+c.getCaseType()+" \t"+c.getCaseNumber()+" \t"+c.getCaseLink()+" \t"+c.getCaseCategory()+" \t "+c.getCaseNotes();
				out.write(CaseString + System.lineSeparator());
				}
			out.close();
			} catch (IOException e) {return false;}
		return true;
		
	}
}
