//Name: Frank Yue Ying, ID: yying2
package hw1;

public class Case {
	String caseDate; //date in YYYY-mm-dd format
	String caseTitle;
	String caseType;
	String caseNumber;

	Case(String caseDate, String caseTitle, String caseType, String caseNumber) {
		//write your code here
		// assign member variables with parameter values 
		this.caseDate = caseDate;
		this.caseTitle = caseTitle.replace("("+caseType+")", "").trim();
		this.caseType = caseType;
		this.caseNumber = caseNumber;
	}

	/** getYear() is an optional method to extract year
	 * from the caseDate. It can be useful 
	 * for printing yearWise summary. 
	 * @return
	 */
	
	int getYear() {
		//write your code here
		//Split caseDate by - to return the year
		return Integer.parseInt(caseDate.split("-")[0]);
	}
}