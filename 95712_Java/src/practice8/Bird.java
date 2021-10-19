package practice8;

public class Bird extends Pet{
	static int birdCount = 0;
	
	@Override
	String talk() {
		birdCount++;
		petCount++;
		return "Tweet...";
	}

}
