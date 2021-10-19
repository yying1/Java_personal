package practice8;

public class Dog extends Pet{
	
	static int dogCount = 0;
	@Override
	String talk() {
		dogCount++;
		petCount++;
		return "Bark...";
	}

}
