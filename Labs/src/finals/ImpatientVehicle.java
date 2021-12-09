//Frank Yue Ying; yying2
package finals;

public class ImpatientVehicle extends Vehicle{
	static int Q_TOO_LONG_LENGTH = 5;
	ImpatientVehicle(){
		super();
		this.impatient = true;
	}

	@Override
	boolean joinVehicleQ() {
		if (Road.vehicleQ.size()>=ImpatientVehicle.Q_TOO_LONG_LENGTH) {
			return false;
		} else {
			return Road.vehicleQ.offer(this);
		}
		
	}
}
