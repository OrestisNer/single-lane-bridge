/**
 *  @author Nerantzis R. Orestis
 * 	Class that implements Singleton Pattern
 * 	
 * 	Switch bridge will alternately let car crossing the bridge.
 *  To ensure about switching SwitchBridge has two semaphores.
 *  
 *  When a red car enter acquire red semaphore.
 *  When a red car exits releases blue semaphore.
 *  Blue cars follows same pattern.
 */
import java.util.concurrent.Semaphore;

public class SwitchBridge extends Bridge{
	
	private static SwitchBridge bridgeInstance = new SwitchBridge();
	
	private int numBlueWaiting, numRedWaiting;
	private Semaphore lockBlue ,lockRed;
	private int maxNumberOfCrossingCars;
	
	//Private constructor to ensure that only one instance of bridge will exist
	private SwitchBridge() {
		this.numBlueCrossing = 0;
		this.numRedCrossing = 0;
		this.numBlueWaiting = 0;
		this.numRedWaiting = 0;
		this.lockBlue = new Semaphore(0);
		this.lockRed = new Semaphore(0);
		
	}
	
	public static SwitchBridge getInstace() {
		return bridgeInstance;
	}
	
	public void setMaxNoOfCrossingCar(int max) {
		maxNumberOfCrossingCars = max;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void redCarEnter(){
		numRedWaiting++;
		//Red car will pass instantly if none blue car is waiting neither is on bridge. 
		if(numBlueWaiting > 0 || numBlueCrossing > 0) {
			try {
				System.out.println("\t\t\t\t\t\tRed Waiting");
				lockRed.acquire();
				System.out.println("\t\t\t\t\t\tRed Stopped Waiting");
			} catch (InterruptedException e) {}
		}
		numRedWaiting--;
		numRedCrossing++;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void redCarExit(){
		System.out.println("\t\t\t\t\t\tRed Releasing");
		if(numBlueWaiting == 0 && numBlueCrossing==0)
			lockRed.release();
		//When a red car exit the bridge release BLUE semaphore so a blue car can enter the bridge.
		--numRedCrossing;
		if(numRedCrossing == 0 && numBlueWaiting > 0) 
			// Let max num of blue cars pass or num of waiting.
			if(numBlueWaiting < maxNumberOfCrossingCars)
				lockBlue.release(numBlueWaiting);
			else
				lockBlue.release(maxNumberOfCrossingCars);
		
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void blueCarEnter(){
		numBlueWaiting++;
		//Blue car will pass instantly if none red car is waiting neither is on bridge. 
		if(numRedWaiting > 0 || numRedCrossing>0) {
			try {
				System.out.println("Blue Waiting");
				lockBlue.acquire();
				System.out.println("Blue stop Waiting");
			} catch (InterruptedException e) {}
		}
		numBlueWaiting--;
		numBlueCrossing++;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void blueCarExit(){
		System.out.println("Blue Releasing");
		if(numRedWaiting == 0 && numRedCrossing==0)
			lockBlue.release();
		//When a blue car exit the bridge release RED semaphore so a red car can enter the bridge.
		numBlueCrossing--;
		if(numBlueCrossing == 0 && numRedWaiting > 0 )
			// Let max num of red cars pass or num of waiting.
			if(numRedWaiting < maxNumberOfCrossingCars)
				lockRed.release(numRedWaiting);
			else
				lockRed.release(maxNumberOfCrossingCars);		
	}
}
