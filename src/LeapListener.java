import com.leapmotion.leap.*;

public class LeapListener extends Listener {
	//declare constants
	private final double RAD_TO_DEG = 180 / Math.PI;
	
	private final double MIN_Z = 15.0;
	private final double MAX_Z = 125.0;
	private final double MIN_PITCH = 10.0;
	
	@Override
	public void onConnect(Controller controller) {
		System.out.println("connected");
	}
	
	@Override
	public void onFrame(Controller controller) {
		//capture frame
		Frame frame = controller.frame();
		
		//reset motor outputs to 0.0
		double[] output = {0.0, 0.0};
		
		//perform calculation if at least one hand, and no more than two hands, is visible
		if(frame.hands().count() > 0 && frame.hands().count() < 3) {
			for(Hand h : frame.hands()) {
				//capture hand position
				Vector d = h.direction();
				Vector p = h.palmPosition();
				
				//calculate hand position
				double pitch = d.pitch() * RAD_TO_DEG;
				double z = p.getZ();
				
				//if the hand is level, leave output power at 0.0
				if(Math.abs(pitch) < MIN_PITCH) {
					continue;
				}
				
				//if the hand is within the deadband zone, leave output power at 0.0
				if(Math.abs(z) < MIN_Z) {
					continue;
				}
				
				//calculate output power based on scaled position
				double sign = Math.signum(z);
				double power = -((z - (sign * MIN_Z)) / (MAX_Z - MIN_Z));
				power = Math.abs(z) > MAX_Z ? 1.0 : power;
				
				//determine which side to command
				if(h.isLeft()) {
					output[0] = power;
				} else {
					output[1] = power;
				}
			}
		}
		
		System.out.println(output[0] + " " + output[1]);
	}
}
