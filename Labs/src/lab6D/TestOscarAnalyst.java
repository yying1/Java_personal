package lab6D;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestOscarAnalyst {
	
	public static OscarAnalyst oscarAnalyst = new OscarAnalyst();
	
	@BeforeAll
	public static void setupOscarAnalyst() {
		oscarAnalyst.loadNominations("oscar.txt");
		oscarAnalyst.loadActors();
	}

	@Test
	void test1_NominationsLength() {
		assertEquals(100, oscarAnalyst.nominations.size());
	}
	
	@Test
	void test2_ActorsLength() {
		assertEquals(79, oscarAnalyst.actors.size());
	}
	
	@Test
	void test3_ActorsContent1() {
		Actor foundActor = null;
		for (Actor actor: oscarAnalyst.actors) {
			if (actor.name.equalsIgnoreCase("Sean Penn")) {
				foundActor = actor;
				break;
			} 
		}
		assertEquals(3, foundActor.awards.size()); //Sean Penn got 3 nominations
	}
	
	@Test
	void test4_ActorsContent2() {
		Actor foundActor = null;
		for (Actor actor: oscarAnalyst.actors) {
			if (actor.name.equalsIgnoreCase("Russell Crowe")) {
				foundActor = actor;
				break;
			} 
		}
		assertEquals(1, foundActor.awards.size()); //Russell Crowe got 1 nomination
		assertEquals("2001", foundActor.awards.get(0).year);
		assertEquals("Leading", foundActor.awards.get(0).type);
		assertEquals("A Beautiful Mind", foundActor.awards.get(0).movie);
		assertEquals("John Nash", foundActor.awards.get(0).role);
	}
	
	@Test
	void test5_ActorsSortingByName() {
		Collections.sort(oscarAnalyst.actors);
		assertEquals("Adrien Brody", oscarAnalyst.actors.get(0).name);
	}
	
	@Test
	void test6_ActorsSortingByAwards() {
		Collections.sort(oscarAnalyst.actors, new ActorComparator());
		assertEquals(3, oscarAnalyst.actors.get(0).awards.size());  //highest number of awards
		assertEquals("George Clooney", oscarAnalyst.actors.get(0).name); //sorted on name
		assertEquals(1, oscarAnalyst.actors.get(oscarAnalyst.actors.size()-1).awards.size()); //lowest number of awards
	}
	
	@Test
	void test7_searchMovieLength() {
		List<Nomination> searchResults = oscarAnalyst.searchMovies("mill");
		assertEquals(2, searchResults.size());
	}
	
	@Test
	void test8_searchMovieContent() {
		List<Nomination> searchResults = oscarAnalyst.searchMovies("mill");
		boolean error = false; 
		for (Nomination nomination : searchResults) {
			if (!nomination.movie.equalsIgnoreCase("Million Dollar Baby")) {  //if any movie other than this one
				error = true;
			}
		}
		assertFalse(error);
	}
}
