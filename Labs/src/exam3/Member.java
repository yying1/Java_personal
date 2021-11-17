//Frank Yue Ying (yying2)
package exam3;

import java.util.List;

public class Member implements Comparable<Member>{
	String name;
	List<String> friends;
	
	Member(String name){
		this.name = name;
	}

	@Override
	public int compareTo(Member o) {
		return o.friends.size() - this.friends.size();
	}
}
