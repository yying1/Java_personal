//Name: Frank Yue Ying (yying2)
package lab5;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Anagrammar {
	String[] words;		//stores all words read from words.txt
	boolean isInDictionary; //true if the clue word exists in words.txt
	boolean hasAnagrams;	//true if the clue word has anagrams
	String[] anagramArray;	//stores all anagrams of clue-word, if found
	
	/**loadWords method reads the file and loads all words 
	 * into the words[] array */
	void loadWords(String filename) {
		String words_str = "";
		try {
			Scanner readFile = new Scanner(new File(filename));
			//Use this to read the entire text file instead of line by line
			words_str = readFile.useDelimiter("\\Z").next();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//pay attention to the line breaks in String
		words = words_str.split("\\r\\n");
		isInDictionary = false;
		hasAnagrams = false;
	}
	
	/** findAnagrams method traverses the words array and looks 
	 * for anagrams of the clue. While doing so, if the clue-word itself is found in the 
	 * words array, it sets the isInDictionary to true.
	 * If it finds any anagram of the clue, it sets the hasAnagram to true. 
	 * It loads the anagram into the anagramArray. 
	 * If no anagrams found, then anagramArray remains an array with size 0. 
	 * */
	void findAnagrams(String clue) {
		//Convert String into array of characters
		char[] clue_a = clue.toLowerCase().toCharArray();
		Arrays.sort(clue_a);
		String anagrams = "";
		for(String w:words) {
			char[] clue_w = w.toLowerCase().toCharArray();
			Arrays.sort(clue_w);
			if (clue.toLowerCase().equals(w.toLowerCase())) {
				isInDictionary = true;
			}
			else if (Arrays.equals(clue_a, clue_w) == true) {
				hasAnagrams = true;
				anagrams = anagrams + w +",";
			}
		}
		if (anagrams.equals("")) {
			anagramArray = new String[0];
			hasAnagrams = false;
		} else {
			anagramArray = anagrams.split(",");
		}
		
	}

}
