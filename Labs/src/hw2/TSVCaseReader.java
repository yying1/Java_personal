//Frank Yue Ying (yying2)
package hw2;

import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class TSVCaseReader extends CaseReader {

	TSVCaseReader(String filename) {
		super(filename);
	}

	/**readCases uses CSVParser library to parse data file */
	@Override
	List<Case> readCases() {
		List<Case> caseList = new ArrayList<>();
		
		try {
			Scanner tsvParser = new Scanner(new File(filename));
			while (tsvParser.hasNext()) {
				String[] raw_array = tsvParser.nextLine().split("\t");
				Case c = new Case(raw_array[0].trim(), raw_array[1].trim(), raw_array[2].trim(), raw_array[3].trim(), raw_array[4].trim(), raw_array[5].trim(), raw_array[6].trim());
				caseList.add(c);
			}
			tsvParser.close();
		} catch (FileNotFoundException e1) { e1.printStackTrace();} 
		return caseList;
	}
}
