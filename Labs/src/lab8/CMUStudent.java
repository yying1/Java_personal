//Frank Yue Ying
//yying2
package lab8;

public class CMUStudent {
	static final int MIN_TALK_TIME = 5;
	static final int MAX_TALK_TIME = 15;
	static int cmuStudentCount;  //total number of students created in the event
	
	int id;  //unique id indicating position in the queue
	int studentTalkTime;

	/** CMUStudent() increments cmuStudentCount, 
	 * initializes id, and
	 * initializes studentTalkTime with a random number 
	 * between MIN_TALK_TIME and MAX_TALK_TIME 
	 */
	CMUStudent(){
		//write your code here
		if (CMUStudent.cmuStudentCount>0) {
			this.id = CMUStudent.cmuStudentCount + 1;
			CMUStudent.cmuStudentCount +=1;
		} else {
			this.id = 1;
			CMUStudent.cmuStudentCount =1;
		}
		this.studentTalkTime = MIN_TALK_TIME + (int)(Math.random() * ((MAX_TALK_TIME - MIN_TALK_TIME) + 1));
	}

}
