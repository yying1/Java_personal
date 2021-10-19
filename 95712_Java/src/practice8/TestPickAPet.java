package practice8;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestPickAPet {
	
	static Pet[] pets = new Pet[3];
	
	@BeforeClass
	public static void setupBeforeClass() {
		pets[0] = new Cat();
		pets[1] = new Dog();
		pets[2] = new Bird();
	}
	
	@Before
	public void setup() {
		Pet.petCount = 0;
		Cat.catCount = 0;
		Dog.dogCount = 0;
		Bird.birdCount = 0;
	}

	@Test
	public void testCatCount() {
		pets[0].talk();
		assertEquals("Test Cat count", 1, Cat.catCount);
	}
	
	@Test
	public void testDogCount() {
		pets[1].talk();
		assertEquals("Test Dog count", 1, Dog.dogCount);
	}
	
	@Test
	public void testBirdCount() {
		pets[2].talk();
		assertEquals("Test Bird count", 1, Bird.birdCount);
	}
	
	@Test
	public void testAllCounts() {
		for (int i = 0; i < pets.length; i++) pets[i].talk();
		assertEquals("Test Cat count", 1, Cat.catCount);
		assertEquals("Test Dog count", 1, Dog.dogCount);
		assertEquals("Test Bird count", 1, Bird.birdCount);
		assertEquals("Test Total count", 3, Pet.petCount);
	}

}
