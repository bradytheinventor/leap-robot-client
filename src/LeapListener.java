import com.leapmotion.leap.*;

public class LeapListener extends Listener {
	//declare constants
	private final double RAD_TO_DEG = 180 / Math.PI;
	
	private final double MIN_PITCH = 10.0;
	private final double MAX_PITCH = 30.0;
	
	@Override
	public void onConnect(Controller controller) {
		System.out.println("connected");
	}
	
	@Override
	public void onFrame(Controller controller) {
		System.out.println("frame");
		Frame frame = controller.frame();
		double[] output = {0.0, 0.0};
		
		if(frame.hands().count() < 1) {
			return;
		}
		
		for(int h = 0; h < frame.hands().count(); h++) {
			String s = frame.hands().get(h).isLeft() ? "left" : "right";
			Vector d = frame.hands().get(h).direction();
			
			double pitch = d.pitch() * RAD_TO_DEG;
			//double roll = d.roll() * RAD_TO_DEG;
			//double yaw = d.yaw() * RAD_TO_DEG;
			
			double sign = Math.signum(pitch);
			output[h] = (pitch - (sign * MIN_PITCH)) / (MAX_PITCH - MIN_PITCH);
			output[h] *= -1.0;
			
			output[h] = Math.abs(pitch) > MIN_PITCH ? output[h] : 0.0;
			output[h] = Math.abs(pitch) > MAX_PITCH ? 1.0 : output[h];
			
			System.out.println("hand " + s + ", p: " + pitch);
		}
		
		System.out.println(output[0] + " " + output[1]);
	}
}
