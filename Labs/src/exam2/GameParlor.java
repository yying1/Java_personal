//Name:Frank Yue Ying
//yying2
package exam2;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class GameParlor {
	
	WordGame wordGame;  //this must hold the instance of WordGame to be played (CountGame or TimeGame). 
	String[] words;		//will contain words read from the data file
	String filename = "miniwords.txt"; //word file to be used for the sames
	Scanner input = new Scanner(System.in);
	
	
	//do not change this method
	public static void main(String[] args) {
		GameParlor game = new GameParlor();
		game.words = game.readWords(game.filename);
		game.runGame();
	}
	
	
	/** readWords() reads words from filename and loads into words[] array */
	String[] readWords(String filename) {
		String words_str="";
		try {
			Scanner readFile = new Scanner (new File (filename));
			words_str = readFile.useDelimiter("\\Z").next();
			readFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return words_str.split("\\r\\n");
	}
	
	/**runGame() prints menu options, takes user choice, 
	 * initializes wordGame with appropriate WordGame instance, and then
	 * invokes play() on it polymorphically.
	 */
	void runGame() {
		System.out.println("*** Enter your choice ***");
		System.out.println("1. Guess the Word in 10 counts");
		System.out.println("2. Guess the Word in 10 seconds");
		int choice = input.nextInt();
		if (choice == 1) {
			wordGame = new CountGame(words);
		}
		else if (choice == 2) {
			wordGame = new TimeGame(words);
		}
		wordGame.play();
		
	}
}
