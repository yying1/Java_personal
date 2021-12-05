//yying2
package lab9;


public class QueueManager implements Runnable{
	int customerDelay;
	int balkCount;
	QueueManager(int customerdelay){
		this.customerDelay = customerdelay;
	}
	@Override
	public void run() {
		QueueManager qm = new QueueManager(MovieHall.customerDelay);
		if (MovieHall.examPart == 1) {
			while (TicketWindow.isWindowOpen == true) {
				try {
				synchronized(MovieHall.customerQueue) {
					Customer c = new Customer();
					c.joinQueue();
					}
				Thread.sleep(0 + (int)(Math.random() * (qm.customerDelay + 1)));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			
		}
	}
}
