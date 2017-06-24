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
		if(frame.hands().count() < 1) {
			return;
		}
		for(int h = 0; h < frame.hands().count(); h++) {
			String s = frame.hands().get(h).isLeft() ? "left" : "right";
			double pitch = d.pitch() * RAD_TO_DEG;
			//double roll = d.roll() * RAD_TO_DEG;
			//double yaw = d.yaw() * RAD_TO_DEG;
			System.out.println("hand " + s + ", p: " + pitch);
		}
	}
}
