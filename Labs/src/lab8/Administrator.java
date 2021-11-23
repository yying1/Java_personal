//Frank Yue Ying
//yying2
package lab8;

public class Administrator implements Runnable{
	static int totalWelcomeCount;  //total students welcomed by both admins
	int adminWelcomeCount; //students welcomed by individual admin
	int adminTalkTime; //cumulative talk time by each admin


	/**run() simulates the event 
	 * in which the admin polls the studentsQ, 
	 * and then talks to the student for student.studentTalkTime.
	 * It adds this time to adminTalkTime
	 * and also increments adminWelcomeCount and totalWelcomeCount
	 * For each student, it prints the output as shown.  
	 */
	@Override
	public void run() {
		//write your code here
		CMUStudent s;
		while (Administrator.totalWelcomeCount <= Mixer.MAX_STUDENTS ) {
			synchronized(Mixer.studentsQ) {
				s = Mixer.studentsQ.poll();
				if (s!=null) {
					if (Thread.currentThread().getName().equals("Andy")) {
						System.out.println("    "+Thread.currentThread().getName()+" talking to Student "+s.id+" for "+s.studentTalkTime+" ms");
					}else {
						System.out.println(Thread.currentThread().getName()+" talking to Student "+s.id+" for "+s.studentTalkTime+" ms");
					}
				}
				else {
					break;
				}
			}
			if (s!=null) {
				try {
					Thread.sleep(s.studentTalkTime);
					this.adminTalkTime += s.studentTalkTime;
					this.adminWelcomeCount +=1;
					Administrator.totalWelcomeCount+=1;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
