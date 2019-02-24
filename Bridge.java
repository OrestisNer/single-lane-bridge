import java.util.LinkedList;

public class Bridge {
	
	private static Bridge bridgeInstance = new Bridge();
	
	private int maxNoOfCrossingCars;
	private int crossingTime;
	private int time;
	private int noBlue , noRed, noBlueWait, noRedWait;
	
	private Bridge() {
		this.time = 0;
		this.noBlue = 0;
		this.noRed = 0;
	}
	
	
	public static Bridge getInstace() {
		return bridgeInstance;
	}
	
	public void setMaxNoOfCrossingCar(int max) {
		this.maxNoOfCrossingCars = max;
	}
	
	public void setCrossingTime(int time) {
		this.crossingTime = time;
	}
	
	synchronized void redCarEnter() throws InterruptedException {
		
		while(noBlue>0) wait();
		noRed++;
	}
	
	synchronized void redCarExit() throws InterruptedException {
		--noRed;
		if(noRed==0) notifyAll();
		
	}
	
	synchronized void blueCarEnter() throws InterruptedException {
		while(noRed > 0) wait();
		noBlue++;
	}
	
	synchronized void blueCarExit() throws InterruptedException {
		--noBlue;
		if(noBlue > 0) notifyAll();
		
	}
	
	

	
	
	
	
	
	
	
	
}
