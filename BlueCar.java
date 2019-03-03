/**
 * @author Nerantzis R. Orestis
 * 
 * Class that represents Blue Cars.
 */
public class BlueCar extends Thread  {
	
	private long id;
	private Bridge bridge;
	
	public BlueCar(long id,Bridge bridge) {
		this.id = id;
		this.bridge = bridge;
	}
	
	public long getId() {
		return id;
	}
	
	/**
	 * Method that is triggering when a new blue Car (thread)
	 * is creating. 
	 * The car makes three actions
	 *     > Enter Bridge 
	 *     > Cross Bridge
	 *     > Exit Bridge
	 * 
	 * Bridge Implementation will handle the time of each action.
	 */
	public void run() {		
		System.out.println("Blue Car "+id+": arrived.");
		bridge.blueCarEnter();
		System.out.println("Blue Car "+id+": entered.");
		try {
			System.out.println("Blue Car "+id+": crossing.");
			sleep(bridge.getCrossingTime());
		} catch (InterruptedException e1) {}
		System.out.println("Blue Car "+id+": exited.");
		bridge.blueCarExit();
    }
}
