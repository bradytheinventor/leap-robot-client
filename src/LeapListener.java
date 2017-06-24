import com.leapmotion.leap.*;

public class LeapListener extends Listener {
	
	@Override
	public void onConnect(Controller controller) {
		System.out.println("connected");
	}
	
	@Override
	public void onFrame(Controller controller) {
		System.out.println("frame");
	}
}
