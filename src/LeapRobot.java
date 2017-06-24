import com.leapmotion.leap.*;

public class LeapRobot {

	public static void main(String[] args) {
		Controller controller = new Controller();
		
		System.out.println("Press ENTER to quit");
		
		try {
			System.in.read();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
