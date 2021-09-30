//Name: Frank Yue Ying (yying2@)

package exam1;

import java.util.Arrays;
import java.io.File;
import java.util.*;

public class Starbucks {

	public static void main(String[] args) {
		Starbucks starbucks = new Starbucks();
		String[] fileData = starbucks.loadData("breakfast.txt");
		String[] items = starbucks.getItems(fileData);
		String[][] customerItems = starbucks.getCustomerItems(fileData);
		String[][] itemCustomers = starbucks.getItemCustomers(items, customerItems);

		starbucks.printItems(items);
		starbucks.printCustomerItems(customerItems);
		starbucks.printItemCustomers(itemCustomers);
	}

	/** loadData reads the data from filename
	 * and return a String array, say fileData, that has one element
	 * for each row in the file.
	 *  
	 * @param filename
	 * @return
	 */
	String[] loadData(String filename) {
		//write your code here
		String [] filedata_loaddata;
		String loadData ="";
		try {
		Scanner readFile = new Scanner(new File(filename));
		while (readFile.hasNext()) {
			loadData = loadData + readFile.nextLine() + "\n";
		}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		filedata_loaddata = loadData.split("\n");
		
		return filedata_loaddata;
	}

	/**getCustomerItems() reads the fileData array created in 
	 * loadData() method and populates a 2D array, say customerItems array.
	 * Each row in the fileData array represents one row in customerItems array
	 * First element in each row is customer name, followed by each element
	 * having one item ordered by that customer. 
	 * @param fileData
	 * @return
	 */
	String[][] getCustomerItems(String[] fileData) {
		//write your code here
		String[][] customerItems_get;
		int order_count = fileData.length;
		customerItems_get = new String[order_count][]; 
		int counter = 0;
		for (String s:fileData) {
			customerItems_get[counter] = s.split(",");
			counter++;
		}
		for (int r = 0; r< order_count; r++) {
			for (int i = 0; i<customerItems_get[r].length;i++) {
				customerItems_get[r][i] = customerItems_get[r][i].trim();
			}
		}
		return customerItems_get;
	}

	/**getItems() takes the fileData array created in loadData() method 
	 * and returns a 1D array, say items.
	 * The items array must have all the items ordered
	 * by all the customers without repeating any item
	 * @param fileData
	 * @return
	 */
	String[] getItems(String[] fileData) {
		//write your code here
		String[] items_get;
		String items_get_s = "";
		String [] items_row;
		for (String a:fileData) {
			items_row = a.split(",");
			int starter = 1;
			for (starter = 1;starter < items_row.length; starter++) {
				if(!items_get_s.contains(items_row[starter].trim())) {
					items_get_s = items_get_s + items_row[starter].trim() + ",";
				}
			}
		}
		items_get = items_get_s.split(",");
		return items_get;
	}

	/** getItemCustomers() uses items and customerItems arrays
	 * to create a reversed 2D array that has one row for each item
	 * and names of customers who ordered that item as the columns. 
	 * @param items
	 * @param customerItems
	 * @return
	 */
	String[][] getItemCustomers(String[] items, String[][] customerItems) {
		//write your code here
		String[][] getItemCustomers = new String[items.length][];
		String customers;
		for (int c = 0; c<items.length;c++) {
			customers="";
			for (String[] d:customerItems) {
				for (String f:d) {
					if (items[c].trim().equals(f.trim())) {
						customers = customers.trim() + d[0].trim()+",";
						break;
					}
				}
			}
			customers = items[c].trim() + ","+customers;
			getItemCustomers[c] = customers.split(",");
		}
		
		return getItemCustomers;
	}

	//-------------------------------------------------------//
	void printItems(String[] items) {
		System.out.println("*** Starbucks Item List ***");
		System.out.println(Arrays.toString(items));
		System.out.println();
	}

	void printCustomerItems(String[][] customerItems) {
		System.out.println("*** Customer Order List ***");
		for (int i = 0; i < customerItems.length; i++) {
			System.out.printf("%s ordered: ", customerItems[i][0]);
			for (int j = 1; j < customerItems[i].length; j++) {
				System.out.printf(" %s", customerItems[i][j]);
				if (j < customerItems[i].length - 1) {  // no comma in the end
					System.out.print(", ");
				}
			}
			System.out.println();
		} 
		System.out.println();
	}

	void printItemCustomers(String[][] itemCustomers) {
		System.out.println("*** Items Order List ***");
		for (int i = 0; i < itemCustomers.length; i++) {
			System.out.printf("%s ordered by: ", itemCustomers[i][0]);
			for (int j = 1; j < itemCustomers[i].length; j++) {
				System.out.printf(" %s", itemCustomers[i][j]);
				if (j < itemCustomers[i].length - 1) {  // no comma in the end
					System.out.print(", ");
				}
			}
			System.out.println();
		} 
	}
}
