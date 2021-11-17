package lab6D;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class OscarAnalyst {
	
	List<Nomination> nominations;
	List<Actor> actors;
	
	Scanner input = new Scanner(System.in);
	
	//do not change this method
	public static void main(String[] args) {
		OscarAnalyst oscarAnalyst = new OscarAnalyst();
		oscarAnalyst.loadNominations("Oscar.txt");
		oscarAnalyst.loadActors();
		oscarAnalyst.analyze();
	}
	
	//complete this method to handle input/output
	void analyze() {
		int choice = 0;
		
		System.out.println("*** Welcome to Oscar Analyzer ***");
		System.out.println("1. Print Actor Nomination Counts");
		System.out.println("2. Search Movie Nominations");
		System.out.println("3. Exit");
		
		System.out.println("Enter your choice: ");
		choice = input.nextInt();
		input.nextLine(); //clear buffer
		switch (choice) {
		case 1: printActorsReport(); break;
		case 2: 
			System.out.println("Enter search string: ");
			String search = input.nextLine();
			List<Nomination> searchresult = searchMovies(search);
			if (searchresult.size() == 0) {
				System.out.println("O movies found");
			} else {
				int i = 1;
				for (Nomination n3: searchresult) {
					System.out.printf("%3d. %4s: %-20s nominated for %-10s role by %10s %n", 
							i++, n3.year,n3.movie, n3.type,n3.actor);
				}
			}
			break;
		case 3: 
			
			break;
		default: break;
		}
	}
	
	/**loadNominations() reads data from file, creates Nomination instances 
	 * for each row of data, and then populates the nominations ArrayList
	 * @param filename
	 */
	void loadNominations(String filename) {
		//write your code here
		nominations = new ArrayList<>();
		try {
			Scanner readFile = new Scanner(new File(filename));
			while (readFile.hasNext()) {
				String [] raw_string = readFile.nextLine().split(",");
				Nomination n = new Nomination(raw_string[0].trim(), raw_string[1].trim(), 
						raw_string[2].trim(), raw_string[3].trim(), raw_string[4].trim());
				nominations.add(n);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**loadActors()  iterates over nominations list, creates Actor instances for 
	 * the actor in a particular Nomination instance, and then populates that Actor's
	 * nominations with the Nomination instances. So for every actor that was nominated, there is 
	 * one Actor instance in actors list. This instance has the list of nominations for that actor 
	 */
	void loadActors() {
		//write your code here
		actors = new ArrayList<>();
		List<String> actor_names = new ArrayList<>();
		for (Nomination n:nominations) {
			if (!actor_names.contains(n.actor)) {
				actor_names.add(n.actor);
			}
		}
		for (String a: actor_names) {
			List<Nomination> nominations_new = new ArrayList<>() ;
			for (Nomination n1:nominations) {
				if (a.equals(n1.actor)) {
					nominations_new.add(n1);
				}
			}
			Actor A = new Actor(a);
			A.awards = nominations_new;
			actors.add(A);
		}
	}
	
	/**printActorsReport() prints list of Actors sorted in the decreasing order
	 * of number of awards. For actors that got the same number of awards, 
	 * they are sorted by name
	 */
	void printActorsReport() {
		//write your code here
		Collections.sort(actors, new ActorComparator());
		int i = 1;
		for (Actor a: actors) {
			System.out.printf("%3d. %-20s: %3d %n", i++, a.name,a.awards.size());
		}
	}
	
	/**searchMovies() returns a list of Oscar instances whose
	 * movie names contain the searchString. 
	 * The search is case-insensitive
	 * @param searchString
	 * @return
	 */
	List<Nomination> searchMovies(String searchString) {
		//write your code here
		List<Nomination> searchresult = new ArrayList<>();
		for (Nomination n2: nominations) {
			if (n2.movie.toLowerCase().contains(searchString.toLowerCase().trim())) {
				searchresult.add(n2);
			}
		}
		Collections.sort(searchresult);
		return searchresult;
		
	}
}
