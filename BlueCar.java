
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
		System.out.println("Blue Car "+id+": arrived");
		try {
			bridge.blueCarEnter();
		} catch (InterruptedException e) {}
		System.out.println("Blue Car "+id+": entered");
		
		try {
			System.out.println("Blue Car "+id+": crossing");
			sleep(bridge.getCrossingTime());
		} catch (InterruptedException e1) {}
		
		try {
			bridge.blueCarExit();
		} catch (InterruptedException e) {}
		
		System.out.println("Blue Car "+id+": exited");
    }
}
