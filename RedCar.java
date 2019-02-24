
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
		System.out.println("\t\t\t\t\t\tRed Car "+id+": arrived");
		try {
			bridge.redCarEnter();
		} catch (InterruptedException e) {System.out.println("----------------------");}
		System.out.println("\t\t\t\t\t\tRed Car "+id+": entered");
		
		try {
			System.out.println("\t\t\t\t\t\tRed Car "+id+": crossing");
			sleep(bridge.getCrossingTime());
		} catch (InterruptedException e1) {}
		
		try {
			bridge.redCarExit();
		} catch (InterruptedException e) {}
		
		System.out.println("\t\t\t\t\t\tRed Car "+id+": exited");
    }
	
	
	
}
