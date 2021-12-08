package lab6b;

import java.util.List;

public class Movie implements Comparable<Movie>{
	String movieName;
	String movieYear;
	List<String> movieGenres;
	
	Movie(String movieName, String movieYear){
		this.movieName = movieName.trim().toLowerCase();
		this.movieYear = movieYear;
	}
	
	int hasCode() {
		
		
		return 0;
	}
	
	public boolean equals (Movie movie) {
		if (this.movieName.toLowerCase().trim().equalsIgnoreCase(movie.movieName.toLowerCase().trim()) & this.movieYear.trim().equalsIgnoreCase(movie.movieYear.trim()) ) {
			return true;
		}
		else {
			return false;
		}
	}
	@Override
	public int compareTo(Movie movie) {
		return this.movieName.compareTo(movie.movieName);
	}
	
	
}
