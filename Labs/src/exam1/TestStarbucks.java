package exam1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestStarbucks {
	
	static Starbucks starbucks;
	static String[] fileData, items;
	static String[][] customerItems, itemCustomers;
	
	@BeforeAll
	public static void setupStarbucks() {
		starbucks = new Starbucks();
		fileData = starbucks.loadData("breakfast.txt");
		items = starbucks.getItems(fileData);
		customerItems = starbucks.getCustomerItems(fileData);
		itemCustomers = starbucks.getItemCustomers(items, customerItems);
	}

	@Test
	void test1_loadFileData() {
		assertEquals(7, fileData.length);
	}
	
	@Test
	void test2_getItemsLength() {
		assertEquals(9, items.length);
	}
	
	@Test
	void test3_getItemsContent() {
		boolean found = false;
		for (String s : items) {
			if (s.equalsIgnoreCase("Chai latte")) {
				found = true;
				break;
			}
		}
		assertTrue(found);
	}
	
	@Test
	void test4_getCustomerItemsLength() {
		assertEquals(7, customerItems.length);
	}
	
	@Test
	void test5_getCustomerItemsLengthForEachCustomer() {
		for (int i = 0; i < customerItems.length; i++) {
			switch (customerItems[i][0]) {
				case "Sam": assertEquals(3, customerItems[i].length); break;
				case "Lisa": assertEquals(3, customerItems[i].length); break;
				case "Joe": assertEquals(4, customerItems[i].length); break;
				case "Raj": assertEquals(3, customerItems[i].length); break;
				case "Cindy": assertEquals(4, customerItems[i].length); break;
				case "Rachel": assertEquals(3, customerItems[i].length); break;
				case "Seema": assertEquals(3, customerItems[i].length); break;
				default: break;
			}
		}
	}

	@Test
	void test6_getCustomerItemsContent() {
		boolean found = false;
		for (int i = 0; i < customerItems.length; i++) {
			if (customerItems[i][0].equalsIgnoreCase("Joe"))
			for (int j = 0; j < customerItems[i].length; j++) {
				if (customerItems[i][j].trim().equalsIgnoreCase("Egg Sandwich")) {
					found = true;
					break;
				}
			}
		}
		assertTrue(found);
	}
	
	@Test
	void test7_getItemCustomersLength() {
		assertEquals(9, itemCustomers.length);
	}
	
	@Test
	void test8_getItemCustomersForEachItem() {
		for (int i = 0; i < itemCustomers.length; i++) {
			switch (itemCustomers[i][0]) {
				case "Coffee": assertEquals(3, itemCustomers[i].length); break;
				case "Banana cake": assertEquals(2, itemCustomers[i].length); break;
				case "Chai latte": assertEquals(3, itemCustomers[i].length); break;
				case "Donut": assertEquals(3, itemCustomers[i].length); break;
				case "Egg sandwich": assertEquals(4, itemCustomers[i].length); break;
				case "Cafe latte": assertEquals(3, itemCustomers[i].length); break;
				case "French toast": assertEquals(3, itemCustomers[i].length); break;
				case "Iced tea": assertEquals(2, itemCustomers[i].length); break;
				case "Brownie": assertEquals(2, itemCustomers[i].length); break;
				default: break;
			}
		}
	}
}
