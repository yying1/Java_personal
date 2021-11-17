package exam3;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;


public class TestMemberMapper {
	
	MemberMapper mapper;
	List<Member> memberList;
	Map<String, List<String>> memberMap, reversedMap;;
	
	@Before
	public void setup() {
		mapper  = new MemberMapper();
		memberList = mapper.setupMemberList();
		memberMap = mapper.setupMemberMap(memberList);
		reversedMap = mapper.setupReversedMap(memberList, memberMap);
	}
	
	/****************************** test memberList ******************/
	@Test
	public void test1_setupMemberListSize() {
		assertEquals(5, memberList.size());
	}
	
	@Test
	public void test2_setupMembersListFindMember() {
		boolean found = false;
		for (Member c : memberList) {
			if (c.name.equalsIgnoreCase("Alice")) {
				found = true;
			}
		}
		assertEquals(true, found);
	}

	@Test
	public void test3_setupMembersFriendListSize() {
		int size = 0;
		for (Member c : memberList) {
			if (c.name.equalsIgnoreCase("Alice")) {
				size = c.friends.size();
			}
		}
		assertEquals(6, size);
	}

	@Test
	public void test4_getMemberPopularityTop() {
		List<Member> popularityList = mapper.getMemberPopularityList(memberList);
		assertEquals("Bob", popularityList.get(0).name);
	}

	/********************** test memberMap *****************************/
	@Test
	public void test5_setupMemberMapSize() {
		assertEquals(5, memberMap.size());
	}

	@Test
	public void test6_setupMemberMapContainsKey() {
		assertEquals(true, memberMap.containsKey("Bob"));
	}

	@Test
	public void test7_setupMemberMapValueSize() {
		assertEquals(7, memberMap.get("Bob").size());
	}
	
	/********************** test reversedMap *****************************/
	@Test
	public void test8_setupReversedMapSize() {
		assertEquals(9, reversedMap.size());
	}
	
	@Test
	public void test9_setupReversedMapContainsKey() {
		assertEquals(true, reversedMap.containsKey("Joe"));
	}
	
	@Test
	public void test10_setupReversedMapValueSize() {
		assertEquals(2, reversedMap.get("Sara").size());
	}
	
	/********************** test common friends *****************************/
	@Test
	public void test11_findCommonFriendsSize() {
		assertEquals(1, mapper.findCommonFriends(memberMap, reversedMap, "Alice", "Charles").size());
		assertEquals(2, mapper.findCommonFriends(memberMap, reversedMap, "Amy", "Steve").size());
	}
	
	@Test
	public void test12_findCommonFriendsNames() {
		assertEquals("Sara", mapper.findCommonFriends(memberMap, reversedMap, "Alice", "Charles").get(0));
	}
	
}
