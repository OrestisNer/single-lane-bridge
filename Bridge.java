import java.util.LinkedList;

public class Bridge {
	
	private static Bridge bridgeInstance = new Bridge();
	
	private int maxNoOfCrossingCars;
	private int crossingTime;
	private int time;
	private int noBlue , noRed, noBlueWait, noRedWait;
	private boolean blueturn;
	
	private Bridge() {
		this.time = 0;
		this.noBlue = 0;
		this.noRed = 0;
		this.noBlueWait = 0;
		this.noRedWait = 0;
		this.blueturn = true;
	}
	
	
	public static Bridge getInstace() {
		return bridgeInstance;
	}
	
	public void setMaxNoOfCrossingCar(int max) {
		this.maxNoOfCrossingCars = max;
	}
	
	public void setCrossingTime(int crossingTime) {
		this.crossingTime = crossingTime;
	}
	
	public int getCrossingTime() {
		return crossingTime;
	}
	
	public void increaseTime() {
		this.time++;
	}
	
	synchronized void redCarEnter() throws InterruptedException {
		noRedWait++;
		//System.out.println("\t\t\t\t\t\t[+Red waiting "+noRedWait+"]");
		while(noBlue>0) wait();
		noRedWait--;
		//System.out.println("\t\t\t\t\t\t[-Red waiting "+noRedWait+"]");
		noRed++;
	}
	
	synchronized void redCarExit() throws InterruptedException {
		--noRed;
		if(noRed==0) notifyAll();
		
	}
	
	synchronized void blueCarEnter() throws InterruptedException {
		noBlueWait++;
		//System.out.println("[+Blue waiting "+noBlueWait+"]");
		while(noRed > 0) wait();
		noBlueWait--;
		//System.out.println("[-Blue waiting "+noBlueWait+"]");
		noBlue++;
	}
	
	synchronized void blueCarExit() throws InterruptedException {
		noBlue--;
		if(noBlue == 0) notifyAll();
		
	}
	
	

	
	
	
	
	
	
	
	
}
