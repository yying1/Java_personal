//Name: Frank Yue Ying, ID: yying2
package hw1;

public class SearchEngine {
	
	/**searchTitle() takes a searchString and array of cases,
	 * searches for cases with searchString in their title,
	 * and if found, returns them in another array of cases.
	 * If no match is found, it returns null.
	 * Search is case-insensitive
	 * @param searchString
	 * @param cases
	 * @return
	 */
	Case[] searchTitle(String searchString, Case[] cases) {
		//write your code here
		//Create Case [] variable and result_count to store result and count
		Case[] searchTitle_result;
		int result_count = 0;
		
		//iterate case and look for a match in title and increment result_count
		for (Case a:cases) {
			if (a.caseTitle.toLowerCase().contains(searchString.toLowerCase())) {
				result_count++;
			}
		}
		//if result count is 0, then return null, else the produce search result
		if (result_count == 0) {
			return null;
		}
		else {
			searchTitle_result = new Case[result_count];
			result_count = 0;
			for (Case b:cases) {
				if (b.caseTitle.toLowerCase().contains(searchString.toLowerCase())) {
					searchTitle_result[result_count] = b;
					result_count++;
				}
			}
			return searchTitle_result;
		}
		
	}
	
	/**searchYear() takes year in YYYY format as search string,
	 * searches for cases that have the same year in their date,
	 * and returns them in another array of cases.
	 * If not found, it returns null.
	 * @param year
	 * @param cases
	 * @return
	 */
	Case[] searchYear(String year, Case[] cases) {
		//write your code here
		//Create Case [] variable and result_count to store result and count
		Case[] searchYear_result;
		int result_count = 0;
		
		//iterate case and look for a match in year and increment result_count
		for (Case a:cases) {
			if (a.caseDate.contains(year)) {
				result_count++;
			}
		}
		//if result count is 0, then return null, else the produce search result
		if (result_count == 0) {
			return null;
		}
		else {
			searchYear_result = new Case[result_count];
			result_count = 0;
			for (Case b:cases) {
				if (b.caseDate.contains(year)) {
					searchYear_result[result_count] = b;
					result_count++;
				}
			}
			return searchYear_result;
		}
	}
	
	/**searchCaseNumber() takes a caseNumber,
	 * searches for those cases that contain that caseNumber, 
	 * and returns an array of cases that match the search.
	 * If not found, it returns null.
	 * Search is case-insensitive.
	 * @param caseNumber
	 * @param cases
	 * @return
	 */
	Case[] searchCaseNumber(String caseNumber, Case[] cases) {
		//write your code here
		
		//Create Case [] variable and result_count to store result and count
		Case[] searchCase_result;
		int result_count = 0;
		
		//iterate case and look for a match in case number and increment result_count
		for (Case a:cases) {
			if (a.caseNumber.contains(caseNumber)) {
				result_count++;
			}
		}
		//if result count is 0, then return null, else the produce search result
		if (result_count == 0) {
			return null;
		}
		else {
			searchCase_result = new Case[result_count];
			result_count = 0;
			for (Case b:cases) {
				if (b.caseNumber.contains(caseNumber)) {
					searchCase_result[result_count] = b;
					result_count++;
				}
			}
			return searchCase_result;
		}
	}
}
