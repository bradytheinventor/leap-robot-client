import java.util.Scanner;
import com.leapmotion.leap.*;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class LeapRobot {
	NetworkTable SmartDashboard;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		LeapListener listener = new LeapListener();
		Controller controller = new Controller();
		
		controller.addListener(listener);
		
		System.out.print("Press ENTER to connect to robot");
		
		try {
			scan.nextLine();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//set computer as NetworkTables client
		System.out.println("[NetworkTables] Setting Client mode");
		NetworkTable.setClientMode();
		
		//set IP address to mDNS address
		System.out.println("[NetworkTables] Setting IP address");
		NetworkTable.setIPAddress("roborio-2823-frc.local");
		
		//try to initialize NetworkTable while ignoring any errors
		System.out.println("[NetworkTables] Initializing...");
		NetworkTable.initialize();
		
		//attempt to connect to SmartDashboard NetworkTable
		System.out.println("[NetworkTables] Getting SmartDashboard table...");
		NetworkTable SmartDashboard = NetworkTable.getTable("SmartDashboard");
		
		//print out status of SmartDashboard connection
		System.out.println("[SmartDashboard] Connected: " + SmartDashboard.isConnected());
		
		System.out.println("[NetworkTables] Setup complete (but SmartDashboard may be unavailable)");
		
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
