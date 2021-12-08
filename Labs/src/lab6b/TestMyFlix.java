package lab6b;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestMyFlix {
	static MyFlix myFlix;
	
	@BeforeClass
	public static void setupClass() {
		myFlix = new MyFlix();
		myFlix.movieDBStrings = myFlix.readMovieDB("MoviesDB.tsv");
		myFlix.loadMovies();
		myFlix.loadGenres();
	}

	@Test
	public void test1_readDBLength() {
		assertEquals(20, myFlix.movieDBStrings.length);
	}
	
	@Test
	public void test2_moviesLength() {
		assertEquals(20, myFlix.moviesList.size());
	}
	
	@Test
	public void test3_moviesContent() {
		boolean found = false;
		for (Movie movie : myFlix.moviesList) {
			if (movie.equals(new Movie("back to the future", "1985"))) {
				assertEquals(2, movie.movieGenres.size());
				assertEquals(true, movie.movieGenres.contains("adventure") && movie.movieGenres.contains("comedy"));
				found = true;
				break;
			}
		}
		assertTrue(found);
	}
	
	@Test
	public void test4_genresLength() {
		assertEquals(10, myFlix.genresList.size());
	}
	
	@Test
	public void test5_genresSort() {
		Collections.sort(myFlix.genresList);
		assertEquals("action", myFlix.genresList.get(0).genreName);
		assertEquals("romance", myFlix.genresList.get(9).genreName);
	}
	
	@Test
	public void test6_genresContentMovieCount() {
		boolean found = false;
		for (Genre genre: myFlix.genresList) {
			if (genre.genreName.equals("comedy")) {
				assertEquals(9, genre.genreMovies.size());
				found = true;
			}
		}
		assertTrue(found);
	}
	
	@Test
	public void test7_genresContentMovieName() {
		Movie movieToCheck = new Movie("Back to the future", "1985");
		System.out.println(movieToCheck.movieName);
		boolean found = false;
		for (Genre genre: myFlix.genresList) {
			if (genre.genreName.equals("comedy")) {
				for (Movie m:genre.genreMovies) {
					System.out.println(m.movieName);
					System.out.println(m.movieYear);
				}
				
				assertEquals(true, genre.genreMovies.contains(movieToCheck));  //uses Movie's equals()
				found = true;
				break;
			}
		}
		assertTrue(found);
	}
	
	@Test
	public void test8_searchMovieName() {
		List<Movie> searchResults = myFlix.findMovies("the");
		assertEquals(5, searchResults.size());
	}
	

	@Test
	public void test9_genreEquals() {
		Genre genre1 = new Genre("drama");
		Genre genre2 = new Genre("drama");
		assertEquals(true, genre1.equals(genre2));  
				
	}
}
