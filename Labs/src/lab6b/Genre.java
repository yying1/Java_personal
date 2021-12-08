package lab6b;

import java.util.List;

public class Genre implements Comparable<Genre> {
	String genreName;
	List<Movie> genreMovies;
	
	Genre(String genreName){
		this.genreName = genreName;
	}
	
	int hasCode() {
		
		
		return 0;
	}
	
	public boolean equals (Genre genre) {
		if (this.genreName.toLowerCase().trim().equals(genre.genreName.toLowerCase().trim()) ) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int compareTo(Genre genre) {
		return genre.genreMovies.size() - this.genreMovies.size();
	}
}
