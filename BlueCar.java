
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
		  
    }
}
