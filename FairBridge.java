import java.util.concurrent.Semaphore;

/**
 * 
 * @author Orestis
 * Class that implements Singleton Pattern
 * Bridge is the class that simulates the bridge which we want to secure.
 * In real life problems bridge could be a memory or a variable etc.
 */
public class FairBridge extends Bridge{
	
	private static FairBridge bridgeInstance = new FairBridge();
	
	private int crossingTime;
	private int numBlueCrossing , numRedCrossing, numBlueWaiting, numRedWaiting;
	private Semaphore lockBlue ,lockRed , lockMaxBlue, lockMaxRed;
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
	
	//Method to access Bridge instance.
	public static FairBridge getInstace() {
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
		if(numRedCrossing == 0) {
			// Αν τα κόκκινα που περιμένουν είναι παραπάνω από το max δώσε προτεραιότητα
			if(numBlueWaiting >= maxNumberOfCrossingCars) 
				lockBlue.release(maxNumberOfCrossingCars);
			//Αν τα κοκκίνα δεν είναι παραπάνω αλλά είναι τα μπλε δώσε στα μπλε
			else if(numRedWaiting >=maxNumberOfCrossingCars) 
				lockRed.release(maxNumberOfCrossingCars);
			// Αν κανένα δεν είναι πάνω από το μαξ δώσε σε αυτό με το μεγαλύτερο προτεραιότητα
			else if(numBlueWaiting > numRedWaiting)
				lockBlue.release(numBlueWaiting);
			else if(numBlueWaiting < numRedWaiting)
				lockRed.release(numRedWaiting);
			//αν δεν περιμένει κανένα αρχικοποίεισαι τα semaphores
			else if (numBlueWaiting==0 && numRedWaiting==0){
				this.lockBlue = new Semaphore(0);
				this.lockRed = new Semaphore(0);
			// είναι ίσα αλλά λίγότερο από το max
			}else {
				lockBlue.release(numRedWaiting);
			}
				
		}
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
		//Παίρνει τις αποφάσεις όταν τελειώσει η διέλευση
		if(numBlueCrossing == 0) {
			// Αν τα κόκκινα που περιμένουν είναι παραπάνω από το max δώσε προτεραιότητα
			if(numRedWaiting >= maxNumberOfCrossingCars) {
				lockRed.release(maxNumberOfCrossingCars);
				System.out.println("-------release from 1");
			}
			//Αν τα κοκκίνα δεν είναι παραπάνω αλλά είναι τα μπλε δώσε στα μπλε
			else if(numBlueWaiting >=maxNumberOfCrossingCars) {
				lockBlue.release(maxNumberOfCrossingCars);
				System.out.println("-------release from 2");
			}
			// Αν κανένα δεν είναι πάνω από το μαξ δώσε σε αυτό με το μεγαλύτερο προτεραιότητα
			else if(numBlueWaiting > numRedWaiting) {
				lockBlue.release(numBlueWaiting);
				System.out.println("-------release from 3");
			}
			else if(numBlueWaiting < numRedWaiting) {
				lockRed.release(numRedWaiting);
				System.out.println("-------release from 4");
			}
			//αν δεν περιμένει κανένα αρχικοποίεισαι τα semaphores
			else if (numBlueWaiting==0 && numRedWaiting==0){
				this.lockBlue = new Semaphore(0);
				this.lockRed = new Semaphore(0);
			// είναι ίσα λίγότερο από το max
			}else {
				lockRed.release(numRedWaiting);
				System.out.println("-------release from 5");
			}
				
		}
		
		
	}	
}
