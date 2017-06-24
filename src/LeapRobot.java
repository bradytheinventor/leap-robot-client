import com.leapmotion.leap.*;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class LeapRobot {
	NetworkTable SmartDashboard;

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
