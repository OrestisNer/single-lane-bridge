
public class BlueCar extends Thread  {
	
	private long id;
	private Bridge bridge;
	
	public BlueCar(long id) {
		this.id = id;
		this.bridge = Bridge.getInstace();
	}
	
	public long getId() {
		return id;
	}
	
	public void run() {		
		System.out.println("Blue Car "+id+": arrived at: "+ bridge.getTime());
		bridge.blueCarEnter();
		System.out.println("Blue Car "+id+": entered at: "+ bridge.getTime());
		
		try {
			System.out.println("Blue Car "+id+": crossing at: "+ bridge.getTime());
			sleep(bridge.getCrossingTime());
		} catch (InterruptedException e1) {}
		Bridge.getInstace().increaseTime();
		bridge.blueCarExit();
		
		System.out.println("Blue Car "+id+": exited at: "+ bridge.getTime());
    }
}
