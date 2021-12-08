package lab4b;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Dictionary extends WordReference{
	Dictionary (String filename){
		int count_row = 0;
		String raw_lines = "";
		try {
			Scanner readfile = new Scanner(new File (filename));
 			while (readfile.hasNext()) {
 				String line = readfile.nextLine();
 				raw_lines = raw_lines.trim()+"\n"+line;
 				count_row++;
			}
			readfile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wordData = new String [count_row][2];
		String [] line_array = raw_lines.split("\n");
		int counter = 0;
		for(String [] row: wordData) {
			String[] line_words = line_array[counter].split("[)]");
			row[1] = line_words[1];
			row[0] = line_words[0].split("[(]")[0].toLowerCase().trim();
			counter++;
		}
	}
	
	String[] lookup (String word) {
		word = word.toLowerCase().trim();
		String [] result;
		String result_raw = "";
		for (String[] row: wordData) {
			if (word.equals(row[0])) {
				result_raw= result_raw.trim()+"\n"+row[1];
			}
		}
		if (result_raw.length()>0) {
			result = result_raw.split("\n");
			return result;
		}
		else {
			return null;
		}
	}
}
