//Frank Yue Ying; yying2
package finals;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Road implements Runnable{
	 static Queue <Vehicle> vehicleQ;
	 static int maxVehicles;
	 static int vehiclesPassed;
	 static int vehiclesExited;
	 static int problemPart;
	 long startTime;
	 long endTime;
	
	void startRoad() {
		System.out.println("Part 1 or 2?");
		Scanner readInput = new Scanner(System.in);
		Road.problemPart = Integer.valueOf(readInput.nextLine());
		System.out.println("How many vehicles?");
		Road.maxVehicles = Integer.valueOf(readInput.nextLine());
		this.startTime = System.currentTimeMillis();
		this.startThreads();
		this.endTime = System.currentTimeMillis();
		readInput.close();
	}
	
	public void startThreads() {
		TrafficLight tl = new TrafficLight();
		Road.vehicleQ = new LinkedList<>();
		Traffic t = new Traffic();
		Thread t1 = new Thread(tl);
		Thread t2 = new Thread(t);
		Thread t3 = new Thread(this);
		t1.setName("TrafficLight");
		t2.setName("Traffic");
		t3.setName("Road");
		t1.start();
		t2.start();
		t3.start();
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void run() {
			while(Vehicle.vehicleCount < Road.maxVehicles) {
				//try {
					synchronized(Road.vehicleQ) {
						if(TrafficLight.isGreen) {
							Vehicle v;
							v = Road.vehicleQ.poll();
							if (v != null) {
								if (v.impatient == true) {
									System.out.printf("Green: ImpatientVehicle %d passed. Q length: %d",v.id,Road.vehicleQ.size());
								} else {
									System.out.printf("Green: Vehicle %d passed. Q length: %d",v.id,Road.vehicleQ.size());
								}
								System.out.println();
								Road.vehiclesPassed++;
							}
						}
					}
				//} catch (InterruptedException e) {
					//e.printStackTrace();
				//}	
			}
	}
	
	void printReport() {
		System.out.println("-----TRAFFIC REPORT-----");
		System.out.println("The program run for "+String.valueOf(this.endTime-this.startTime)+" ms");
		System.out.println("Max Q length at traffic light was "+Traffic.maxQLength);
		System.out.println("Final Q length at traffic light was "+Road.vehicleQ.size());
		if (Road.problemPart == 2) {
			System.out.println("Vehicles passed: "+Road.vehiclesPassed);
			System.out.println("Vehicles exited: "+Road.vehiclesExited);
		}
	}
	 
	void checkAssertions() {
        assertEquals(maxVehicles, vehiclesPassed + vehiclesExited + vehicleQ.size());
        assertTrue(Traffic.maxQLength >= vehicleQ.size());
        assertTrue(Vehicle.vehicleCount == maxVehicles );
    }
	
	public static void main(String[] args) {
	        Road road = new Road();
	        road.startRoad();
	        road.printReport();
	        road.checkAssertions();
	    }
	 
	 
}
