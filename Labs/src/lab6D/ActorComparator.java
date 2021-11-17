package lab6D;

import java.util.Comparator;

public class ActorComparator implements Comparator<Actor> {

	@Override
	public int compare(Actor a1, Actor a2) {
		// TODO Auto-generated method stub
		int c = 0;
	    c = a2.awards.size() - a1.awards.size();
	    if (c == 0) {
	    	c = a1.name.compareTo(a2.name);
	    }
	    return c;
	}
	
}