package exam2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestGameParlor {

	static GameParlor gameParlor = new GameParlor(); 
	
	@BeforeAll
	public static void setupGame() {
		gameParlor.words = gameParlor.readWords("miniwords.txt");
		gameParlor.wordGame = new CountGame(gameParlor.words);
	}
	@Test
	void test1_wordLength() {
		assertEquals(5, gameParlor.words.length);  //check data
	}
	@Test
	void test2_clueLength() {
		gameParlor.wordGame = new CountGame(gameParlor.words); //reset game
		assertTrue(gameParlor.wordGame.puzzleWord.length() == gameParlor.wordGame.clue.length()); //cluelength should be same as puzzleword length
	}
	
	@Test
	void test3_clueContent() {
		gameParlor.wordGame = new CountGame(gameParlor.words); //reset game
		assertTrue(gameParlor.wordGame.clue.matches("-".repeat(gameParlor.wordGame.puzzleWord.length()))); //clue must have all dashes
	}
	
	@Test
	void test4_getNextClueCorrectGuess() {
		gameParlor.wordGame.puzzleWord = "hello"; 
		String clue = gameParlor.wordGame.getNextClue("-----", 'l'); 
		assertEquals("--ll-", clue); 
	}
	
	@Test
	void test5_getNextClueIncorrectGuess() {
		gameParlor.wordGame.puzzleWord = "hello";
		String clue = gameParlor.wordGame.getNextClue("-----", 'x');
		assertEquals("-----", clue);
	}
	
	@Test
	void test6_isGameOver() {
		gameParlor.wordGame.puzzleWord = "hello";
		gameParlor.wordGame.getNextClue("hell-", 'o');
		assertTrue(gameParlor.wordGame.isGameOver);
	}
}
