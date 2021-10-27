//Name:Frank Yue Ying
//yying2
package exam2;

import java.util.Random;


public abstract class WordGame {

	String puzzleWord; 	//holds the answer to the puzzle
	String clue;		// clue displayed to the player
	boolean isGameOver = false;  //will be set to true when user solves the puzzle

	
	/**WordGame() constructor that initializes puzzleWord 
	 * by finding a word randomly from the words[] array.
	 * It also initializes clue with as many dashes as the length of the puzzleWord.
	 * @param words
	 */
	WordGame(String[] words) {
		Random random = new Random();
		int find = random.nextInt(words.length);
		puzzleWord = words[find].trim();
		String[] clue_array = new String[puzzleWord.length()];
		clue = "";
		for (int i =0; i<clue_array.length;i++) {
			clue_array[i] = "-";
			clue = clue+"-";
		}
		//clue = Arrays.toString(clue_array);
	}

	/** getNextClue() takes current clue and the next guess
	 * made by player. If the guess exists in puzzleWord,
	 * it replaces the dashes in clue with guessed character 
	 * at correct positions as in puzzleWord. 
	 * For example, if puzzleWord is 'java', the clue is '----', 
	 * and guess is 'a', then the method should return '-a-a'
	 * 
	 * It there are no more dashes in the clue, 
	 * the isGameOver flag is set to true.   
	 * @param clue
	 * @param guess
	 * @return
	 */
	String getNextClue(String clue, char guess) {
		
		char[] clue_list = clue.toCharArray();
		
		for (int c = 0; c<clue_list.length;c++) {
			if (String.valueOf(clue_list[c]).trim().equals("-")) {
				if (puzzleWord.substring(c,c+1).equalsIgnoreCase(Character.toString(guess))) {
					clue_list[c] = puzzleWord.substring(c,c+1).trim().charAt(0);
				} else {
					clue_list[c] ='-';
				}
			} 
		}
		clue = String.valueOf(clue_list);
		if (!clue.contains("-")) {
			isGameOver = true;
		}
		return clue;
	}

	abstract void play(); //to be implemented by child classes

}
