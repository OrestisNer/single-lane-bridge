import java.util.concurrent.Semaphore;

public class SwitchBridge {
private static SwitchBridge bridgeInstance = new SwitchBridge();
	
	private int crossingTime;
	private int numBlueCrossing , numRedCrossing, numBlueWaiting, numRedWaiting;
	private Semaphore lockBlue ,lockRed , lockMaxBlue, lockMaxRed;
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
	
	//Method to access Bridge instance.
	public static SwitchBridge getInstace() {
		return bridgeInstance;
	}
	
	public void setMaxNoOfCrossingCar(int max) {
		maxNumberOfCrossingCars = max;
	}
	
	public void setCrossingTime(int crossingTime) {
		this.crossingTime = crossingTime;
	}
	
	public int getCrossingTime() {
		return crossingTime;
	}
		
	
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
	
	public void redCarExit(){
		System.out.println("\t\t\t\t\t\tRed Releasing");
		if(numBlueWaiting == 0)
			lockRed.release();
		--numRedCrossing;
		if(numRedCrossing == 0) lockBlue.release(maxNumberOfCrossingCars);
		//When a red car exit the bridge release BLUE semaphore so a blue car can enter the bridge.
		
		
	}
	
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
	
	public void blueCarExit(){
		System.out.println("Blue Releasing");
		if(numRedWaiting == 0)
			lockBlue.release();
		//When a blue car exit the bridge release RED semaphore so a red car can enter the bridge.
		numBlueCrossing--;
		if(numBlueCrossing == 0) lockRed.release(maxNumberOfCrossingCars);
	}
}
