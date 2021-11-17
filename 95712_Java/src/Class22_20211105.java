import java.util.LinkedList;
import java.util.Queue;

// Multi-Threading
// 2021-11-15

public class Class22_20211105 {
	//Extends Thread Vs. Implement Runnable
		//prefer implement runnable because you can only have one inheritance, semantic sense
		//Runnable also run on thread
	//start() Vs. Run()
		//start will fork lane from main()
		//JVM switching lanes to process alternatively at fast space, seems concurrent
		//run() invokes on thread or runnable 
	//Producer Vs. Consumer
		//utilizing shared resources
		// for testing purposes, manually creating delay to show multi theading behavior
		//identifying parties and what should be synchronized (shared resources)
		// adding thread.join() to main class to let thread send signal when done
		//Identifying synchronized block, only lock the minimum shared part
		//Queue: offer and pull
	//Priority Queue
		//must implement comparable to sort the queue
		//then pull will get the first item after sorting
	
	public static void main(String[] args) {
	//start() Vs. Run()
		multithread mt = new multithread();
		mt.run();// this will run method for main
		//child thread is not created yet, so with run first, main will execute first
		mt.start();//start new thread (thread 0) and run new thread
		//With start first to create child thread, we can see alternating behavior 
		
	//Producer Vs. Consumer
		
	}

}

class multithread extends Thread{
	public void run() {
		for (int i = 0; i<100; i++) {
			System.out.println(Thread.currentThread().getName() + " running.");
			//smaller i will let JVM to execute thread entirely
		}
	}
}

class passenger {
	int ticketnum;
	static int passengercount;
	passenger(){
		this.ticketnum = passengercount+1;
		passengercount++;
	}
}

class boardinggate {
	terminal t;
	aircraft a;
	static Queue<passenger> queue = new LinkedList<>();
	//why static? single resource, shared by other classes without instantiation
	//declare volatile variable to avoid create local copies on static variable in memory
	void board() {
		
	}
}

class terminal{
	
}

class aircraft{
	
}