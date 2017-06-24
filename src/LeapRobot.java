import java.util.Scanner;
import com.leapmotion.leap.*;

public class LeapRobot {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Press ENTER to start Leap Motion and connect to robot");
		
		try {
			scan.nextLine();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		LeapListener listener = new LeapListener();
		Controller controller = new Controller();
		
		controller.addListener(listener);
		
		System.out.print("Press ENTER to quit");
		
		try {
			scan.nextLine();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		controller.removeListener(listener);
		scan.close();
	}
}
