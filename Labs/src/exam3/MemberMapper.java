//Frank Yue Ying (yying2)
package exam3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class MemberMapper {

	String[] members = {"Alice", "Bob", "Charles", "David", "Frank"};
	String[][] memberFriends = 
		{{"Bob", "Joe", "Sara", "Amy", "Frank", "Nancy"}, 						//Alice's friends
				{"Alice", "Nancy", "Peter", "Steve", "Frank", "Tim", "Amy"}, 	//Bob's friends
				{"Sara", "Kevin", "Peter", "Steve"}, 							//Charles friends
				{"Steve", "Amy"}, 												//David's friends
				{"Alice", "Bob", "Mary"}};										//Frank's friends

	//do not change this method
	public static void main(String[] args) {
		MemberMapper mapper = new MemberMapper();
		List<Member> memberList = mapper.setupMemberList();

		System.out.println("\n------ Member Popularity ------");
		mapper.printPopularityList(mapper.getMemberPopularityList(memberList));

		Map<String, List<String>> memberMap = mapper.setupMemberMap(memberList);
		System.out.println("\n------ Member Map ------");
		mapper.printMap(memberMap);

		System.out.println("\n------ Reversed Map ------");
		Map<String, List<String>> reversedMap = mapper.setupReversedMap(memberList, memberMap);
		mapper.printMap(reversedMap);

		System.out.println("\n----- Find Common Friends -----");
		System.out.println("Enter first name");
		Scanner input = new Scanner(System.in);
		String firstName = input.nextLine();
		System.out.println("Enter second name");
		String secondName = input.nextLine();
		List<String> commonFriends = mapper.findCommonFriends(memberMap, reversedMap, firstName, secondName);
		if (commonFriends != null && commonFriends.size() >0) {
			System.out.println("The common friends are: ");
			for (String s : commonFriends) System.out.println(s);
		} else {
			System.out.println("Sorry! No common friends found!");
		}
		System.out.println("\n------ Thank you! ------");
		input.close();
	}

	/****************************************** printing ******************************************/
	//do not change this method
	void printPopularityList(List<Member> memberList) {
		if (memberList != null)
			for (Member c : memberList) {
				System.out.printf("Member: %s has %d friends%n", c.name, c.friends.size());
			}
	}

	//do not change this method
	void printMap(Map<String, List<String>> map) {
		if (map != null)
			for (Map.Entry<String, List<String>> entry : map.entrySet()) {
				System.out.print(entry.getKey() + ": ");
				for (String c: entry.getValue()) {
					System.out.print(c + " ");
				}
				System.out.println();
			}
	}

	/**************************Write your code from this line onwards **********************/

	/** setupMemberList() uses the data in the two arrays - members and memberFriends - 
	 * given above to create a memberList that has Member objects. 
	 * Each member object has a name and a list of friends
	 * In terms of indexing, member[i]'s friends are in memberFriends[i] array. 
	 */
	List<Member> setupMemberList() {
		//write your code here
		List<Member> memberList = new ArrayList<>();
		for (int i = 0; i <members.length; i++) {
			String Member_name = members[i];
			String [] friends = memberFriends[i];
			Member m = new Member(Member_name);
			m.friends = new ArrayList<>();
			for (String f:friends) {
				m.friends.add(f);
			}
			memberList.add(m);
 		}
		return memberList;
	}

	/**getMemberPopularityList() sorts the membersList in the order of 
	 * member's number of friends in descending order 
	 */
	List<Member> getMemberPopularityList(List<Member> memberList) {
		//write your code here
		Collections.sort(memberList);
		return memberList;
	}

	/**setupMemberMap() takes memberList as a parameter
	 * and converts it into a map. It returns the map.
	 * The map has member's name as the Key and the list of his/her friends's names as the value.
	 * There is no case-conversion, i.e. 'Alice' is stored as a key and not 'alice'. 
	 * Note that the keys as well as the friends' names list should be
	 * alphabetically sorted
	 */
	Map<String, List<String>> setupMemberMap(List<Member> memberList) {
		//write your code here
		Map<String, List<String>> memberMap = new TreeMap<>();
		Arrays.sort(members);
		for (String name:members) {
			for (Member m:memberList) {
				if(m.name.equals(name)) {
					List<String> friend_list = m.friends;
					Collections.sort(friend_list);
					memberMap.put(name,friend_list);
				}
			}
		}
		return memberMap;
	}

	/**setReversedMap() uses the memberList and memberMap to create a reversedMap
	 * You may or may not use both memberList and memberMap depending on your approach.
	 * The method should return reversed map with non-member names as the keys 
	 * and the list of their member-friends as their values.
	 * Note that the keys as well as the friends' names list should be
	 * alphabetically sorted
	 */
	Map<String, List<String>> setupReversedMap(List<Member> memberList, Map<String, List<String>> memberMap) {
		//write your code here
		Map<String, List<String>> rmap = new TreeMap<>(); 
		List<String> non_m = new ArrayList<>();
		for (Member m1:memberList) {
			List<String> friend_list = m1.friends;
			for (String f:friend_list) {
				if (!memberMap.containsKey(f)) {
					if(!non_m.contains(f)) {
						non_m.add(f);
					}
				}
			}
		}
		Collections.sort(non_m);
		for (String f1:non_m) {
			List<String> member_friends = new ArrayList<>();
			for (Member m2:memberList) {
				if (m2.friends.contains(f1)) {
					member_friends.add(m2.name);
				}
			}
			Collections.sort(member_friends);
			rmap.put(f1, member_friends);
		}
		return rmap;
		
	}

	/**findCommonFriends() takes two names: name1 and name2, and finds common friends, if any, using the two maps - memberMap and reversedMap. 
	 * Depending on your approach, you may or may not use both maps to find common friends. 
	 * The method returns a list of common friends, if found. Else it returns an empty list. 
	 */
	List<String> findCommonFriends(Map<String, List<String>> memberMap, Map<String, List<String>> reversedMap, String name1, String name2) {
		//write your code here
		List<String> commonf = new ArrayList<>();
		List<String> friends1 = new ArrayList<>();
		List<String> friends2 = new ArrayList<>();
		if (memberMap.containsKey(name1)) {
			// name1 is a member
			if(memberMap.containsKey(name2)) {
				// name2 is a member
				friends1 = memberMap.get(name1);
				friends2 = memberMap.get(name2);
			}
			else {
				friends1 = memberMap.get(name1);
				try {friends2 = reversedMap.get(name2);}
				catch(Exception e) {
					System.out.println(e);
				}
			}
		}
		else {
			// name1 is not member
			if(memberMap.containsKey(name2)) {
				// name2 is a member
				try {friends1 = reversedMap.get(name1);}
				catch(Exception e) {
					System.out.println(e);
				}
				friends2 = memberMap.get(name2);
			}
			else {
				try {
				friends1 = reversedMap.get(name1);
				friends2 = reversedMap.get(name2);}
				catch (Exception e) {
					System.out.println(e);
				}
			}
		}
		if(friends1 != null && friends2 != null) {
			for (String friend:friends1) {
				if (friends2.contains(friend)) {
					commonf.add(friend);
				}
			}
		}
		return commonf;
	}
}
