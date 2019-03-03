/**
 * @author Nerantzis R. Orestis
 * 
 * Class that represents Red Cars.
 */
public class RedCar extends Thread{
	
	private long id;
	private Bridge bridge;
	
	public RedCar(long id,Bridge bridge) {
		this.id = id;
		this.bridge = bridge;
	}
	
	public long getId() {
		return id;
	}
	
	/**
	 * Method that is triggering when a new red Car (thread)
	 * is creating. 
	 * The car makes three actions
	 *     > Enter Bridge 
	 *     > Cross Bridge
	 *     > Exit Bridge
	 * 
	 * Bridge Implementation will handle the time of each action.
	 */
	public void run() {		
		System.out.println("\t\t\t\t\t\tRed Car "+id+": arrived.");
		bridge.redCarEnter();
		System.out.println("\t\t\t\t\t\tRed Car "+id+": entered.");
		try {
			System.out.println("\t\t\t\t\t\tRed Car "+id+": crossing.");
			sleep(bridge.getCrossingTime());
		} catch (InterruptedException e1) {}
		System.out.println("\t\t\t\t\t\tRed Car "+id+": exited.");
		bridge.redCarExit();
    }
	
	
	
}
