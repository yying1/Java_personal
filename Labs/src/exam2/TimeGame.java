//Name:Frank Yue Ying
//yying2
package exam2;

import java.util.Arrays;
import java.util.Scanner;

public class TimeGame extends WordGame{
	

	public static int MAX_TIME = 10;
	public TimeGame(String[] words) {
		super(words);
	} 
	
	public void play() {
		System.out.println("Enter your guess!");
		long start = System.currentTimeMillis();
		Scanner readinput = new Scanner(System.in);
		long time_left = MAX_TIME-(Math.round((System.currentTimeMillis()-start)/1000.0));
		while(isGameOver == false) {
			if (time_left <= 0) {
				System.out.println("Sorry! The word is "+puzzleWord);
				break;
			}
			String input = readinput.next().trim();
			clue = this.getNextClue(clue, input.charAt(0));
			time_left = MAX_TIME-(Math.round((System.currentTimeMillis()-start)/1000.0));
			char[] clue_array = clue.toCharArray();
			System.out.println(String.valueOf(Arrays.toString(clue_array)));
			System.out.println(String.valueOf(Math.round((System.currentTimeMillis()-start)/1000.0))+" seconds");
			if (isGameOver == true) {
				System.out.println("Great! You got it right in "+String.valueOf(Math.round((System.currentTimeMillis()-start)/1000.0))+" seconds");
			}
		}
		readinput.close();
	}
}
