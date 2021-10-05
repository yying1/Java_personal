import others.SavingAccount;
//This is a supplement file supporting the main Class10 demo

public class Class10_20211004_SA extends SavingAccount {
	double fakebalance3;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Class10_20211004_SA SA2 = new Class10_20211004_SA();
		System.out.println(SA2.showbalance()); // child class can access protected methods from parent class from a different pacakge
	}
	
	public double printbalance() {
		Class10_20211004_SA SA2 = new Class10_20211004_SA();
		return SA2.showbalance();
	}

}