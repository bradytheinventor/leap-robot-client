import java.util.Scanner;
import com.leapmotion.leap.*;

public class LeapRobot {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		//wait for confirmation before starting program
		System.out.print("Press ENTER to start Leap Motion and connect to robot");
		try {
			scan.nextLine();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//create Leap Motion controller and event listener objects
		LeapListener listener = new LeapListener();
		Controller controller = new Controller();
		
		controller.addListener(listener);
		
		//wait for confirmation before ending program
		System.out.print("Press ENTER to quit");
		try {
			scan.nextLine();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//stop event capture and close input stream
		controller.removeListener(listener);
		scan.close();
	}
}
