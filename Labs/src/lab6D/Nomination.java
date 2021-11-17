package lab6D;


public class Nomination implements Comparable<Nomination>{
	String year;
	String type;
	String actor;
	String movie;
	String role;
	
	Nomination(String year, String type,String actor, String movie,String role){
		this.year = year;
		this.type = type;
		this.actor = actor;
		this.movie = movie;
		this.role = role;
	}
	@Override
	public int compareTo(Nomination n) {
		return this.year.compareTo(n.year);
	}
	
}
