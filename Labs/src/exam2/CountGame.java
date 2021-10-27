//Name:Frank Yue Ying
//yying2
package exam2;

import java.util.Scanner;
import java.util.Arrays;

public class CountGame extends WordGame{
	

	public static int MAX_COUNT = 10;
	
	public CountGame(String[] words) {
		super(words);
	}
	
	public void play() {
		System.out.println("Enter your guess!");
		Scanner readinput = new Scanner(System.in);
		int try_left = MAX_COUNT;
		while(isGameOver == false) {
			if (try_left == 0) {
				System.out.println("Sorry! The word is "+puzzleWord);
				break;
			}
			String input = readinput.next().trim();
			clue = this.getNextClue(clue, input.charAt(0));
			try_left--;
			char[] clue_array = clue.toCharArray();
			System.out.println(String.valueOf(MAX_COUNT-try_left)+". "+Arrays.toString(clue_array));
			if (isGameOver == true) {
				System.out.println("Great! You got it right in "+String.valueOf(MAX_COUNT-try_left)+" guesses");
			}
		}
		readinput.close();
	}
}
