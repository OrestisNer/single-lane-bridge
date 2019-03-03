
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
