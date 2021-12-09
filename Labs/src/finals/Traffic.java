//Frank Yue Ying; yying2
package finals;

public class Traffic implements Runnable{
	static int MIN_VEHICLE_DELAY = 5;
	static int MAX_VEHICLE_DELAY = 10;
	static int maxQLength;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(Vehicle.vehicleCount < Road.maxVehicles) {
			try {
				synchronized(Road.vehicleQ) {
						int vehicle_type = 1+(int)(Math.random()*4);
						Vehicle v;
						if (vehicle_type == 4 & Road.problemPart == 2) {
							v = new ImpatientVehicle();
						}else {
							v = new Vehicle();
						}
						if(v.joinVehicleQ() == true) {
							if(TrafficLight.isGreen == false) {
								if (v.impatient == true) {
									System.out.printf("\tRED: ImpatientVehicle %d in Q. Q length %d",v.id,Road.vehicleQ.size());
								} else {
									System.out.printf("\tRED: Vehicle %d in Q. Q length %d",v.id,Road.vehicleQ.size());
								}
								System.out.println();
								Traffic.maxQLength = Math.max(Traffic.maxQLength,Road.vehicleQ.size());
							}
						} else {
							if(TrafficLight.isGreen == false) {
								System.out.printf("*********RED: ImpatientVehicle %d exiting. Q length %d",v.id,Road.vehicleQ.size());
								System.out.println();
								Road.vehiclesExited++;
							}
						}
					}
				Thread.sleep(Traffic.MIN_VEHICLE_DELAY + (int)(Math.random() * (Traffic.MAX_VEHICLE_DELAY + 1)));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
	}

}
