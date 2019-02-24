import java.util.LinkedList;

public class Bridge {
	
	private static Bridge bridgeInstance = new Bridge();
	
	private int maxNoOfCrossingCars;
	private int crossingTime;
	private LinkedList<RedCar> redCars;
	private LinkedList<BlueCar> blueCars;
	
	private Bridge() {
		this.redCars = new LinkedList<>();
		this.blueCars = new LinkedList<>();
	}
	
	
	public void addBlueCar(BlueCar bcar) {
		this.blueCars.addFirst(bcar);
	}
	
	public void addRedCar(RedCar rcar) {
		this.redCars.addFirst(rcar);
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
	
	
	
	
	
	
	
	
}
