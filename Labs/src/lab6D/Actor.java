package lab6D;

import java.util.ArrayList;
import java.util.List;

public class Actor implements Comparable<Actor>{
	String name;
	List<Nomination> awards;
	Actor(String name){
		this.name = name;
		awards = new ArrayList<>();
	}
	
	@Override
	public int compareTo(Actor a) {
		return this.name.compareTo(a.name);
	}
}
