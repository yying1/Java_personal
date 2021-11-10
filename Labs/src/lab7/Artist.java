//Frank Yue Ying (yying2)
package lab7;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Artist implements Comparable<Artist> {
	String name;
	List<Nomination> nominations = new ArrayList<>();
	
	Artist(String name,List<Nomination> nominations){
		this.name = name;
		this.nominations = nominations;
	}
	
	public int hashCode() {
		return Objects.hash(name);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if (this == o) return true;//check memory location
		if (getClass() != o.getClass()) return false;
		Artist s = (Artist) o;
		return name.equals(s.name);
	}
	public String toString(){
		String result =name+": "+nominations.size();
		return result;
	}

	@Override
	public int compareTo(Artist o) {
		int c = 0;
	    c = o.nominations.size() - nominations.size();
	    if (c == 0) {
	    	c = name.compareTo(o.name);
	    }
	    return c;
	}
}
