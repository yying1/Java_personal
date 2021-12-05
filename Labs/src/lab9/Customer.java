//yying2
package lab9;

public class Customer implements Comparable<Customer>{
	static int customerCount;
	int id;
	int numberOfTickets;
	Customer (){
		this.numberOfTickets = MovieHall.MIN_TICKETS + (int)(Math.random() * ((MovieHall.MAX_TICKETS - MovieHall.MIN_TICKETS) + 1));
	}
	
	boolean joinQueue () {
		MovieHall.customerQueue.offer(this);
		if (Customer.customerCount>0) {
			this.id = Customer.customerCount + 1;
			Customer.customerCount +=1;
		} else {
			this.id = 1;
			Customer.customerCount =1;
		}
		System.out.println("Customer "+this.id+" joined Q.");
		return true;
	}

	@Override
	public int compareTo(Customer o) {
		// TODO Auto-generated method stub
		return o.numberOfTickets - this.numberOfTickets;
	}
	
	
}
