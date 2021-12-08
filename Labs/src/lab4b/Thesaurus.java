package lab4b;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Thesaurus extends WordReference {
	Thesaurus (String filename) {
		int count_row = 0;
		int max_col = 0;
		String raw_lines = "";
		try {
			Scanner readfile = new Scanner(new File (filename));
 			while (readfile.hasNext()) {
 				String line = readfile.nextLine();
 				raw_lines = raw_lines.trim()+"\n"+line;
 				if(line.split(",").length > max_col) {
 					max_col = line.split(",").length;
 				}
 				count_row++;
			}
			readfile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wordData = new String [count_row][max_col];
		String [] line_array = raw_lines.split("\n");
		int counter = 0;
		for(String [] row: wordData) {
			String[] line_words = line_array[counter].split(",");
			for(int i = 0; i < line_words.length; i++) {
				row[i] = line_words[i].toLowerCase().trim();
			}
			counter++;
		}
	}
	String[] lookup (String word) {
		word = word.toLowerCase().trim();
		String [] result;
		String result_raw = "";
		for (String[] row: wordData) {
			if (word.equals(row[0])) {
				for(int i =1; i<row.length;i++) {
					if (!(row[i] == null)) {
						result_raw = result_raw.trim() +"\n"+row[i];
					} else {
						break;
					}
				}
			} 
		}
		if (!result_raw.equals("")) {
			result = result_raw.split("\n");
			return result;
		}
		else {
			return null;
		}
	}
	
}
