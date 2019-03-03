/**
 * @author Nerantzis R. Orestis
 * Class that implements Singleton Pattern
 * 
 * Safe Bridge will handle all the cars safe
 * but in an unfair way.
 * 
 * To ensure that bridge is safe a Semaphore is
 * used. When a car enters acquires the semaphore
 * and when leaving releases it.
 */
import java.util.concurrent.Semaphore;

public class SafeBridge extends Bridge {
	
private static SafeBridge bridgeInstance = new SafeBridge();
	
	private Semaphore lock;
	
	//Private constructor to ensure that only one instance of bridge will exist
	private SafeBridge() {
		this.numBlueCrossing = 0;
		this.numRedCrossing = 0;
		this.lock = new Semaphore(1);
		
	}
	
	//Method to access Bridge instance.
	public static SafeBridge getInstace() {
		return bridgeInstance;
	}
			
	/**
	 * {@inheritDoc}
	 */
	public void redCarEnter(){
		try {
			System.out.println("\t\t\t\t\t\tRed Waiting");
			lock.acquire();
			System.out.println("\t\t\t\t\t\tRed Stopped Waiting");
		} catch (InterruptedException e) {}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void redCarExit(){
		System.out.println("\t\t\t\t\t\tRed Releasing");
		lock.release();
		--numRedCrossing;		
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void blueCarEnter(){
		try {
			System.out.println("Blue Waiting");
			lock.acquire();
			System.out.println("Blue stop Waiting");
		} catch (InterruptedException e) {}
		numBlueCrossing++;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void blueCarExit(){
		System.out.println("Blue Releasing");
		lock.release();
		numBlueCrossing--;
	}	

}
