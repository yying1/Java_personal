//yying2
package lab9;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Scanner;


public class MovieHall {
	static int MAX_TICKETS = 10;
	static int MIN_TICKETS = 1;
	static Queue <Customer> customerQueue = new LinkedList<>();
	static int examPart;
	static int balkQueueLength;
	static int maxSeats;
	static int ticketProcessingTime;
	static int customerDelay;
	long startTime;
	long endTime;
	QueueManager queueManager;
	TicketWindow ticketWindow;
	
	public static void main(String[] args) {

		MovieHall movieHall = new MovieHall();
		movieHall.getInputs();
		movieHall.startTime = System.currentTimeMillis();
		movieHall.startThreads();
		movieHall.endTime = System.currentTimeMillis();
		movieHall.printReport();
		movieHall.testResults();

		}
	
	void getInputs() {
		Scanner readOption = new Scanner(System.in);
		System.out.println("Part 1 or Part 2?");
		examPart = readOption.nextInt();
		System.out.println("Enter single ticket processing time (ms):");
		ticketProcessingTime = readOption.nextInt();
		System.out.println("Enter max tickets to be sold:");
		maxSeats = readOption.nextInt();
		System.out.println("Enter max customer delay(ms):");
		customerDelay = readOption.nextInt();
		if (MovieHall.examPart == 2) {
			System.out.println("Enter impatient customer's balk-queue-length");
			MovieHall.balkQueueLength = readOption.nextInt();
		}
		readOption.close();
		queueManager = new QueueManager(MovieHall.customerDelay);
		ticketWindow = new TicketWindow(MovieHall.ticketProcessingTime);
		System.out.println("------------Detailed Customer Report------------");
	}
	
	public void startThreads() {
		Thread tw = new Thread(ticketWindow);
		Thread qm = new Thread(queueManager);
		tw.setName("TicketWindow");
		qm.setName("QueueManager");
		qm.start();
		tw.start();
		try {
			qm.join();
			tw.join();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	void printReport() {
		if (MovieHall.examPart == 1) {
			System.out.println("------------Part 1 Report------------");
			System.out.println("Ticket window open duration           : "+ (this.endTime - this.startTime)+"ms");
			System.out.println("Total Customers                       : "+ Customer.customerCount);
			System.out.println("Customers who bought tickets          : "+ ticketWindow.customerList.size());
			System.out.println("Customers in queue when window closed : "+ (Customer.customerCount - ticketWindow.customerList.size()));
			System.out.println();
			System.out.println("Total Tickets sold                    : "+TicketWindow.ticketSoldCount);
		} else {
			
		}
		System.out.println("------------Customer Summary Report------------");
		Collections.sort(ticketWindow.customerList);
		int n = 1;
		int ctotal = 0;
		for(Customer c:ticketWindow.customerList) {
			ctotal += c.numberOfTickets;
			System.out.printf("%d.     Customer %d bought: %d tickets.    Cumulative total: %d%n",n,c.id,c.numberOfTickets,ctotal);
			n++;
		}
	}
	
	
	//DO NOT change this method

	void testResults() {

	int ticketsSold = 0;  //total tickets sold

	int minTickets = MAX_TICKETS, maxTickets = MIN_TICKETS;

	//find the min, max, and total tickets sold from the customerList

	for (Customer c : ticketWindow.customerList) {

	ticketsSold += c.numberOfTickets;
	if (minTickets > c.numberOfTickets) minTickets = c.numberOfTickets;
	if (maxTickets < c.numberOfTickets) maxTickets = c.numberOfTickets;

	}

	//test whether total customerCount matches the sum of customers in customerList, //customers in customerQueue, and customers who balked

	assertEquals("Total customers", Customer.customerCount,

	ticketWindow.customerList.size() + customerQueue.size() + queueManager.balkCount);

	//test total tickets sold calculated above matches the total count at TicketWindow

	assertEquals("Total tickets sold", ticketsSold, TicketWindow.ticketSoldCount );

	//test that total tickets sold is equal to or more than maxSeats 

	assertTrue(ticketsSold >= maxSeats);

	//test the minTickets calculated above is greater than or equal to MIN_TICKETS

	assertTrue("Min tickets", minTickets >= MIN_TICKETS);

	//test the maxTickets calculated above is less than or equal to MIN_TICKETS

	assertTrue("Max tickets", maxTickets <= MAX_TICKETS);

	}
}
