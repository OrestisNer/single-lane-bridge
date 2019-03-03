/**
 * 
 * @author Nerantzis R. Orestis
 * 
 * Abstract class that every bridge inherits the 
 * basic attributes and methods.
 *
 */
public abstract class Bridge {
	
	protected int crossingTime;
	protected int numBlueCrossing , numRedCrossing;
	
	/**
	 * Method that is triggering when 
	 * a blue car enters the bridge.
	 */
	protected void blueCarEnter() {}
	
	/**
	 * Method that is triggering when 
	 * a blue car exits the bridge.
	 */
	protected void blueCarExit() {}
	
	/**
	 * Method that is triggering when
	 * a red car enters the bridge
	 */
	protected void redCarEnter() {}
	
	/**
	 * Method that is triggering when
	 * a red car exits the bridge
	 */
	protected void redCarExit() {}
	
	protected void setMaxNoOfCrossingCar(int max) {} 
	
	protected void setCrossingTime(int crossingTime) {
		this.crossingTime = crossingTime;
	}
	
	protected int getCrossingTime() {
		return crossingTime;
	}
}
