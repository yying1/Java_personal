//Frank Yue Ying; yying2
package finals;

public class Vehicle {
	static int vehicleCount=0;
	int id;
	boolean impatient;
	
	Vehicle(){
		this.id = Vehicle.vehicleCount+1;
		Vehicle.vehicleCount++;
		this.impatient = false;
	}
	
	boolean joinVehicleQ() {
		return Road.vehicleQ.offer(this);
	}
}
