import java.util.concurrent.Semaphore;

/**
 * 
 * @author Nerantzis R. Orestis
 * Class that implements Singleton Pattern
 * 
 * Fair Bridge will handle all the cars in the most fair way.
 * In dense movement will alternately let car crossing the bridge.
 * When the movement is not dense then will give priority 
 * to cars that the number is higher.
 * 
 */
public class FairBridge extends Bridge{
	
	private static FairBridge bridgeInstance = new FairBridge();
	
	private int numBlueWaiting, numRedWaiting;
	private Semaphore lockBlue ,lockRed;
	private int maxNumberOfCrossingCars;
	
	//Private constructor to ensure that only one instance of bridge will exist
	private FairBridge() {
		this.numBlueCrossing = 0;
		this.numRedCrossing = 0;
		this.numBlueWaiting = 0;
		this.numRedWaiting = 0;
		this.lockBlue = new Semaphore(0);
		this.lockRed = new Semaphore(0);
		
	}
	
	public static FairBridge getInstace() {
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
		//Pass instantly if none blue car is waiting neither is on bridge. 
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
		if(numBlueWaiting == 0)
			lockRed.release();
		--numRedCrossing;
		//Taking Action only when the bridge is empty
		if(numRedCrossing == 0) {
			// Let blue cars pass if num of waiting is greater than max
			if(numBlueWaiting >= maxNumberOfCrossingCars) 
				lockBlue.release(maxNumberOfCrossingCars);
			//Let red cars pass if blue is no higher than max but red is.
			else if(numRedWaiting >=maxNumberOfCrossingCars) 
				lockRed.release(maxNumberOfCrossingCars);
			// if none of them are higher than max let the greater between them.
			else if(numBlueWaiting > numRedWaiting)
				lockBlue.release(numBlueWaiting);
			else if(numBlueWaiting < numRedWaiting)
				lockRed.release(numRedWaiting);
			// if bridge is empty , initialize 
			else if (numBlueWaiting==0 && numRedWaiting==0){
				this.lockBlue = new Semaphore(0);
				this.lockRed = new Semaphore(0);
			//Else Let Blue 
			}else {
				lockBlue.release(numBlueWaiting);
			}
				
		}
		//When a red car exit the bridge release BLUE semaphore so a blue car can enter the bridge.
		
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void blueCarEnter(){
		numBlueWaiting++;
		//Pass instantly if none red car is waiting neither is on bridge. 
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
		if(numRedWaiting == 0)
			lockBlue.release();
		numBlueCrossing--;
		//Taking Action only when the bridge is empty
		if(numBlueCrossing == 0) {
			// Let red cars pass if num of waiting is greater than max
			if(numRedWaiting >= maxNumberOfCrossingCars) {
				lockRed.release(maxNumberOfCrossingCars);
			}
			//Let blue cars pass if red is no higher than max but blue is.
			else if(numBlueWaiting >=maxNumberOfCrossingCars) {
				lockBlue.release(maxNumberOfCrossingCars);
			}
			// if none of them are higher than max let the greater between them.
			else if(numBlueWaiting > numRedWaiting) {
				lockBlue.release(numBlueWaiting);
			}
			else if(numBlueWaiting < numRedWaiting) {
				lockRed.release(numRedWaiting);
			}
			// if bridge is empty , initialize 
			else if (numBlueWaiting==0 && numRedWaiting==0){
				this.lockBlue = new Semaphore(0);
				this.lockRed = new Semaphore(0);
			//Else Let Red 
			}else {
				lockRed.release(numRedWaiting);
			}
				
		}
		
		
	}	
}
