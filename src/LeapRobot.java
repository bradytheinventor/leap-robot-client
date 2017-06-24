import com.leapmotion.leap.*;

public class LeapRobot {

	public static void main(String[] args) {
		LeapListener listener = new LeapListener();
		Controller controller = new Controller();
		
		controller.addListener(listener);
		
		System.out.println("Press ENTER to quit");
		
		try {
			System.in.read();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		controller.removeListener(listener);
	}
}
