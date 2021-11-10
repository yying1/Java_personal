//Frank Yue Ying (yying2)
package lab7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/** GrammyAnalyst takes Grammys.txt to provide two reports and one search functionality
 */
public class GrammyAnalyst {
	/**initialize these member variables with appropriate data structures **/
	List<Nomination> nominations;  
	Map<String, List<Nomination>> grammyMap;  
	List<Artist> artists;
	
	public static void main(String[] args) {
		GrammyAnalyst ga = new GrammyAnalyst();
		ga.loadNominations();
		ga.loadGrammyMap();
		System.out.println("*********** Grammy Report ****************");
		ga.printGrammyReport();
		System.out.println("*********** Search Artist ****************");
		System.out.println("Enter artist name");
		Scanner input = new Scanner(System.in);
		String artist = input.nextLine();
		ga.searchGrammys(artist);
		ga.loadArtists();
		System.out.println("*********** Artists Report ****************");
		ga.printArtistsReport();
		input.close();
	}
	
	/**loadNominations() reads data from Grammys.txt and 
	 * populates the nominations list, where each element is a Nomination object
	 */
	void loadNominations() {
		//write your code here
		try {
			Scanner readfile = new Scanner(new File("Grammys.txt"));
			nominations = new ArrayList<>();
			while(readfile.hasNext()) {
				String[] raw = readfile.nextLine().split(";");
				Nomination n = new Nomination(raw[0].trim(), raw[1].trim(), raw[2].trim());
				nominations.add(n);
			}
			readfile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**loadGrammyMap uses artist name in lower case as the key, and a list of 
	 * all nominations for that artist as its value. Hint: use 'nominations' list 
	 * created in previous method to populate this map.
	 */
	void loadGrammyMap() {
		//write your code here
		List<Nomination> artist_nominations; 
		Set<String> artist_names = new HashSet<>();
		grammyMap = new HashMap<>();
		for (Nomination n:nominations) {
			artist_names.add(n.artist);
		}
		for (String a:artist_names) {
			artist_nominations = new ArrayList<>();
			for (Nomination n1:nominations) {
				if (a.equals(n1.artist)) {
					artist_nominations.add(n1);
				}
			}
			grammyMap.put(a.toLowerCase(),artist_nominations);
		}
	} 
	
	/**loadArtists loads the artists array List. Each Artist object in it should have
	 * artist's name in proper case, i.e., as read from data file, and 
	 * a list of nominations for that artist. Hint: use 'grammyMap' created in 
	 * previous method to populate this list
	 */
	void loadArtists() {
		//write your code here
		artists = new ArrayList<>();
		for (Map.Entry<String, List<Nomination>> entry :grammyMap.entrySet()) {
			Artist a = new Artist(entry.getValue().get(0).artist,entry.getValue());
			artists.add(a);
		}
	}
	
	/**printGrammyReport prints report as shown in the handout */
	void printGrammyReport() {
		//write your code here
		Collections.sort(nominations);
		int n = 1;
		for (Nomination n2:nominations) {
			System.out.println(n+". "+n2.toString());
			n++;
		}
	}
	
	/**printArtistReport prints report as shown in the handout */
	void printArtistsReport() {
		//write your code here
		Collections.sort(artists);
		int n = 1;
		for (Artist a2:artists) {
			System.out.println(n+". "+a2.toString());
			n++;
		}
		
	}
	
	/**searchGrammys takes a string as input and makes a case-insensitive
	 * search on grammyMap. If found, it prints data about all nominations
	 * as shown in the handout.
	 */
	void searchGrammys(String artist) {
		//write your code here
		boolean result = false;
		for (Map.Entry<String, List<Nomination>> entry:grammyMap.entrySet()) {
			if (entry.getKey().contains(artist.toLowerCase())) {
				for (Nomination n2: entry.getValue()) {
					result = true;
					System.out.println(n2.artist+": "+n2.category+": "+n2.title);
				}
			}
		}
		if (result == false) {
			System.out.println("Sorry! "+artist+" not found!");
		}
	}
}
