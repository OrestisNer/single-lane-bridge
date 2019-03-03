
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
	
	public void run() {		
		System.out.println("Blue Car "+id+": arrived.");
		bridge.blueCarEnter();
		System.out.println("Blue Car "+id+": entered.");
		try {
			System.out.println("Blue Car "+id+": crossing.");
			sleep(bridge.getCrossingTime());
		} catch (InterruptedException e1) {}
		//Bridge.getInstace().increaseTime();
		System.out.println("Blue Car "+id+": exited.");
		bridge.blueCarExit();
    }
}
