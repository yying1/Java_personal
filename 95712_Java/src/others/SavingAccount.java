package others;

public class SavingAccount {
	
	private double interest = 0.03, balance;
	double fakebalance;
	protected double fakebalance2;
	public void changeinterest(double newinterest) {
		interest = newinterest;
	}
	public double showinterest() {
		return this.interest;
	}
	
	protected double showbalance() {
		return this.balance;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
