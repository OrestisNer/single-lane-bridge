import java.util.concurrent.Semaphore;

public class UnsafeBridge extends Bridge {

	private static UnsafeBridge bridgeInstance = new UnsafeBridge();
	
	private int crossingTime;
	private int numBlue , numRed;
	
	//Private constructor to ensure that only one instance of bridge will exist
	private UnsafeBridge() {
		this.numBlue = 0;
		this.numRed = 0;	
	}
	
	//Method to access Bridge instance.
	public static UnsafeBridge getInstace() {
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
		numRed++;
	}
	
	public void redCarExit(){
		numRed--;		
	}
	
	public void blueCarEnter(){
		numBlue++;
	}
	
	public void blueCarExit(){
		numBlue--;
	}

}
