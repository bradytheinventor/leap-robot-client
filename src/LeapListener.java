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
		Frame frame = controller.frame();
		double[] output = {0.0, 0.0};
		
		if(frame.hands().count() < 1) {
			return;
		}
		
		for(Hand h : frame.hands()) {
			String s = h.isLeft() ? "left" : "right";
			Vector d = h.direction();
			Vector p = h.palmPosition();
			
			double pitch = d.pitch() * RAD_TO_DEG;
			//double roll = d.roll() * RAD_TO_DEG;
			//double yaw = d.yaw() * RAD_TO_DEG;
			
			double x = p.getX();
			double y = p.getY();
			double z = p.getZ();
			
			double sign = Math.signum(z);
			double power = (z - (sign * MIN_Z)) / (MAX_Z - MIN_Z);
			power *= -1.0;
			
			power = Math.abs(pitch) > MIN_PITCH ? power : 0.0;
			power = Math.abs(z) > MIN_Z ? power : 0.0;
			power = Math.abs(z) > MAX_Z ? 1.0 : power;
			
			if(h.isLeft()) {
				output[0] = power;
			} else {
				output[1] = power;
			}
			
			//System.out.println("hand " + s + ", p: " + pitch);
			System.out.println(" z: " + p.getZ() + " out: " + power);
		}
		
		System.out.println(output[0] + " " + output[1]);
	}
}
