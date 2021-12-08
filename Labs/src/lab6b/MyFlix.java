package lab6b;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MyFlix {
	List<Movie> moviesList = new ArrayList<>();
	List<Genre> genresList = new ArrayList<>();
	String[] movieDBStrings;

	//do not change this method
	public static void main(String[] args) {
		MyFlix myFlix = new MyFlix();
		myFlix.movieDBStrings = myFlix.readMovieDB("MoviesDB.tsv");
		myFlix.loadMovies();
		myFlix.loadGenres();
		Collections.sort(myFlix.moviesList);
		Collections.sort(myFlix.genresList);
		myFlix.showMenu();
	}

	//do not change this method
	//showMenu() displays the menu for the user to choose from,
	//takes required inputs, and invokes related methods
	void showMenu() {
		Scanner input = new Scanner(System.in);
		int choice = 0;
		System.out.println("*** Welcome to MyFlix ***"); 
		System.out.println("1. Search for a movie");
		System.out.println("2. List of genres");
		System.out.println("3. Exit");
		choice = input.nextInt();
		input.nextLine();
		switch (choice) {
		case 1: {
			System.out.println("Enter the string to search in movie names");
			String searchString = input.nextLine();
			printSearchResults(findMovies(searchString));
			break;
		}
		case 2: {
			printGenreReport();
			break;
		}
		case 3: System.out.println("Bye Bye!"); break;
		default: break;
		}
		input.close();
	}

	//do not change this method
	//readMovieDB reads all data from the MovieDB file
	//and loads each row as a string in movieDBStrings
	String[] readMovieDB(String filename) {
		StringBuilder movies = new StringBuilder();
		try {
			Scanner input = new Scanner(new File(filename));
			while (input.hasNextLine()) {
				movies.append(input.nextLine() + "\n");
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return movies.toString().split("\n");
	}

	//loadMovies use data in movieDBStrings to create Movie objects 
	//and add them to moviesList.
	void loadMovies() {
		for (String movie: this.movieDBStrings) {
			Movie m = new Movie(movie.split("\t")[0],movie.split("\t")[1]);
			String[] genres = Arrays.copyOfRange(movie.split("\t"), 2,movie.split("\t").length);
			for (String genre:genres) {genre= genre.toLowerCase().trim();}
			m.movieGenres = Arrays.asList(genres);
			this.moviesList.add(m);
		}
	}

	//loadGenres uses data in moviesList to create Genre objects 
	//and add them to genresList
	void loadGenres() {
		//write your code here
		List<String> genreslist = new ArrayList<>();
		for (Movie m:this.moviesList) {
			for (String genre: m.movieGenres) {
				if (!genreslist.contains(genre.toLowerCase().trim())) {
					genreslist.add(genre.toLowerCase().trim());
				}
			}
		}
		for (String genre:genreslist) {
			Genre g = new Genre(genre);
			g.genreMovies = new ArrayList<>();
			for (Movie m:this.moviesList) {
				if(m.movieGenres.contains(genre)) {
					g.genreMovies.add(m);
				}
			}
			this.genresList.add(g);
		}
	}

	//findMovies() returns a list of Movie objects 
	//that have the searchString in their names
	List<Movie> findMovies(String searchString) {
		searchString = searchString.toLowerCase().trim();
		List<Movie> searchresult = new ArrayList<>();
		for (Movie m:this.moviesList) {
			if (m.movieName.contains(searchString) |  searchString.equals("")) {
				searchresult.add(m);
			}
		}
		return searchresult;
	}

	//print the search output. 
	//You may use this printf statement:System.out.printf("%3d. %-50s\tYear: %s\n", ++count, movie.movieName, movie.movieYear);
	void printSearchResults(List<Movie> searchResults) {
		if (searchResults.size() == 0) {
			System.out.println("Sorry! No movie found!");
		} else {
			Collections.sort(searchResults);
			int count = 0;
			for (Movie m: searchResults) {
				System.out.printf("%3d. %-50s\tYear: %s\n", ++count, m.movieName, m.movieYear);
			}
		}
	}

	//print the genre summary report. 
	//You may use this printf statement:System.out.printf("%3d. %-15s Number of movies: %,6d%n", ++count, genre.genreName, genre.genreMovies.size());
	void printGenreReport() {
		Collections.sort(this.genresList);
		int count = 0;
		for (Genre g: this.genresList) {
			System.out.printf("%3d. %-15s Number of movies: %,6d%n", ++count, g.genreName, g.genreMovies.size());
		}
	}
}
