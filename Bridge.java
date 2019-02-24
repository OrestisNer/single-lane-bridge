import java.util.concurrent.Semaphore;

public class Bridge {
	
	private static Bridge bridgeInstance = new Bridge();
	
	private int crossingTime;
	private int time;
	private int numBlue , numRed, numBlueWait, numRedWait;
	private Semaphore lockBlue;
	private Semaphore lockRed;
	
	private Bridge() {
		this.time = 0;
		this.numBlue = 0;
		this.numRed = 0;
		this.numBlueWait = 0;
		this.numRedWait = 0;
		this.lockBlue = new Semaphore(1);
		this.lockRed = new Semaphore(0);
		
	}
	
	
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
		//System.out.println("\t\t\t---NRW:"+numRedWait+"---");
		try {
			lockRed.acquire();
		} catch (InterruptedException e) {}
		numRedWait--;
		numRed++;
	}
	
	public void redCarExit(){
		--numRed;
		lockBlue.release();
		
	}
	
	public void blueCarEnter(){
		numBlueWait++;
		//System.out.println("\t\t\t---NBW:"+numBlueWait+"---");
		try {
			lockBlue.acquire();
		} catch (InterruptedException e) {}
		numBlueWait--;
		numBlue++;
	}
	
	public void blueCarExit(){
		lockRed.release();
		numBlue--;
	}
	
	

	
	
	
	
	
	
	
	
}
