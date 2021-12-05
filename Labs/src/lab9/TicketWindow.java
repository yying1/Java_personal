//yying2
package lab9;

import java.util.ArrayList;
import java.util.List;


public class TicketWindow implements Runnable{
	static int ticketSoldCount = 0;
	static boolean isWindowOpen = true;
	int ticketProcessingTime;
	List<Customer> customerList = new ArrayList<>();
	TicketWindow(int ticketProcessingtime){
		this.ticketProcessingTime = ticketProcessingtime;
	}
	@Override
	public void run() {
		TicketWindow tw = new TicketWindow(MovieHall.ticketProcessingTime);
		Customer c;
		while (TicketWindow.isWindowOpen == true) {
				synchronized(MovieHall.customerQueue) {
					c = MovieHall.customerQueue.poll();
				}
			if (c != null) {
				try {
					Thread.sleep(tw.ticketProcessingTime*c.numberOfTickets);
					System.out.println("    "+"Customer "+c.id+" bought "+c.numberOfTickets +" tickets.");
					TicketWindow.ticketSoldCount+=c.numberOfTickets;
					tw.customerList.add(c);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (TicketWindow.ticketSoldCount >= MovieHall.maxSeats) {
					TicketWindow.isWindowOpen = false;
					this.customerList = tw.customerList;
					break;
				}
			}
		}
		
	}
}
