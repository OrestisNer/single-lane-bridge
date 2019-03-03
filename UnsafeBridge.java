
/**
 * 
 * @author Nerantzis R. Orestis
 * Class that implements Singleton Pattern
 * 
 * Unsafe bridge just let cars crossing.
 * Inherits all the methods.
 */

public class UnsafeBridge extends Bridge {

	private static UnsafeBridge bridgeInstance = new UnsafeBridge();
	
	//Private constructor to ensure that only one instance of bridge will exist
	private UnsafeBridge() {
		this.numBlueCrossing = 0;
		this.numRedCrossing = 0;	
	}
	
	public static UnsafeBridge getInstace() {
		return bridgeInstance;
	}

	

}
