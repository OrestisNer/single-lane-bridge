
public class RedCar extends Thread{
	
	private long id;
	private Bridge bridge;
	
	public RedCar(long id) {
		this.id = id;
		this.bridge = Bridge.getInstace();
	}
	
	public long getId() {
		return id;
	}
	
	public void run() {		
		System.out.println("\t\t\t\t\t\tRed Car "+id+": arrived at: "+ bridge.getTime());
		bridge.redCarEnter();
		
		System.out.println("\t\t\t\t\t\tRed Car "+id+": entered at: "+ bridge.getTime());
		
		try {
			System.out.println("\t\t\t\t\t\tRed Car "+id+": crossing at: "+ bridge.getTime());
			sleep(bridge.getCrossingTime());
		} catch (InterruptedException e1) {}
		Bridge.getInstace().increaseTime();
		bridge.redCarExit();
		
		System.out.println("\t\t\t\t\t\tRed Car "+id+": exited at: "+ bridge.getTime());
    }
	
	
	
}
