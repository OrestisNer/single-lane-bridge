import java.util.concurrent.Semaphore;

/**
 * 
 * @author Orestis
 * Class that implements Singleton Pattern
 * Bridge is the class that simulates the bridge which we want to secure.
 * In real life problems bridge could be a memory or a variable etc.
 */
public class Bridge {
	
	private static Bridge bridgeInstance = new Bridge();
	
	private int crossingTime;
	private int time;
	private int numBlue , numRed, numBlueWait, numRedWait;
	private Semaphore lockBlue;
	private Semaphore lockRed;
	
	//Private constructor to ensure that only one instance of bridge will exist
	private Bridge() {
		this.time = 0;
		this.numBlue = 0;
		this.numRed = 0;
		this.numBlueWait = 0;
		this.numRedWait = 0;
		this.lockBlue = new Semaphore(0);
		this.lockRed = new Semaphore(0);
		
	}
	
	//Method to access Bridge instance.
	public static Bridge getInstace() {
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
	
	public int getTime() {
		return time;
	}
	
	public void increaseTime() {
		this.time++;
	}
	
	
	public void redCarEnter(){
		numRedWait++;
		//Red car will pass instantly if none blue car is waiting neither is on bridge. 
		if(numBlueWait > 0 || numBlue > 0) {
			try {
				System.out.println("\t\t\t\t\t\tRed Waiting");
				lockRed.acquire();
				System.out.println("\t\t\t\t\tRed Stopped Waiting");
			} catch (InterruptedException e) {}
		}
		numRedWait--;
		numRed++;
	}
	
	public void redCarExit(){
		System.out.println("\t\t\t\t\t\tRed Releasing");
		lockBlue.release();
		--numRed;
		//When a red car exit the bridge release BLUEx semaphore so a blue car can enter the bridge.
		
		
	}
	
	public void blueCarEnter(){
		numBlueWait++;
		//Blue car will pass instantly if none red car is waiting neither is on bridge. 
		if(numRedWait > 0 || numRed>0) {
			try {
				System.out.println("Blue Waiting");
				lockBlue.acquire();
				System.out.println("Blue stop Waiting");
			} catch (InterruptedException e) {}
		}
		numBlueWait--;
		numBlue++;
	}
	
	public void blueCarExit(){
		System.out.println("Blue Releasing");
		lockRed.release();
		//When a blue car exit the bridge release RED semaphore so a red car can enter the bridge.
		numBlue--;
	}
	
	

	
	
	
	
	
	
	
	
}
