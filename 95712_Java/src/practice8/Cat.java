package practice8;

public class Cat extends Pet{
	
	static int catCount = 0;
	@Override
	String talk() {
		catCount++;
		petCount++;
		return "Meow...";
	}
	

}
