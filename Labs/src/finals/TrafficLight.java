//Frank Yue Ying; yying2
package finals;

public class TrafficLight implements Runnable {
	static int TRAFFIC_LIGHT_DELAY = 100;
	static boolean isGreen;
	
	@Override
	public void run() {
		while(Vehicle.vehicleCount < Road.maxVehicles) {
			try {
				synchronized(Road.vehicleQ) {
						if (TrafficLight.isGreen == true) {
							TrafficLight.isGreen = false;
						}else {
							TrafficLight.isGreen = true;
						}
					}
				Thread.sleep(TrafficLight.TRAFFIC_LIGHT_DELAY);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}	
	}

}
