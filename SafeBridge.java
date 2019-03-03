import java.util.concurrent.Semaphore;

public class SafeBridge extends Bridge {
	
private static SafeBridge bridgeInstance = new SafeBridge();
	
	private int crossingTime;
	private int numBlue , numRed;
	private Semaphore lock;
	
	//Private constructor to ensure that only one instance of bridge will exist
	private SafeBridge() {
		this.numBlue = 0;
		this.numRed = 0;
		this.lock = new Semaphore(1);
		
	}
	
	//Method to access Bridge instance.
	public static SafeBridge getInstace() {
		return bridgeInstance;
	}
	
	public void setMaxNoOfCrossingCar(int max) {
		//lockTurn = new Semaphore(max);
	}
	
	public void setCrossingTime(int crossingTime) {
		this.crossingTime = crossingTime;
	}
	
	public int getCrossingTime() {
		return crossingTime;
	}
		
	
	public void redCarEnter(){
		try {
			System.out.println("\t\t\t\t\t\tRed Waiting");
			lock.acquire();
			System.out.println("\t\t\t\t\t\tRed Stopped Waiting");
		} catch (InterruptedException e) {}
	}
	
	public void redCarExit(){
		System.out.println("\t\t\t\t\t\tRed Releasing");
		lock.release();
		--numRed;		
	}
	
	public void blueCarEnter(){
		try {
			System.out.println("Blue Waiting");
			lock.acquire();
			System.out.println("Blue stop Waiting");
		} catch (InterruptedException e) {}
		numBlue++;
	}
	
	public void blueCarExit(){
		System.out.println("Blue Releasing");
		lock.release();
		numBlue--;
	}	

}
