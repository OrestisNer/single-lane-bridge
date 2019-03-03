import java.util.Arrays;
import java.util.List;

public class Main {
	
	private static Bridge bridge = null;
	
	public static void main(String[] args) {
		
		if(!isInputsValid(args)) {
			System.exit(0);
		}
		String bridgeImplementation = args[0];
		final int noBlueCars = Integer.parseInt(args[1]);
		final int noRedCars = Integer.parseInt(args[2]);
		
		int frequenceOfArrival = Integer.parseInt(args[3]); //milis
		int crossingTime = Integer.parseInt(args[4]); //milis
		int maxNoOfCrossingCars = 0;
		
		if(args.length == 6)
			maxNoOfCrossingCars = Integer.parseInt(args[5]);
		
		initialiazeBridge(bridgeImplementation);
		bridge.setMaxNoOfCrossingCar(maxNoOfCrossingCars);
		bridge.setCrossingTime(crossingTime);
		
		//Create two threads that will creating Cars(Threads) asynchronously
		new Thread(new Runnable() {
			public void run() {
				for(int i=0; i<noBlueCars; i++) {
					new BlueCar(i+1,bridge).start();
					//Frequence of car arrival
					try {
						Thread.sleep(frequenceOfArrival);
					} catch (InterruptedException e) {}
				}
			}
		}).start();
		
		
		new Thread(new Runnable() {
			public void run() {
				for(int i=0; i<noRedCars; i++) {
					new RedCar(i+1,bridge).start();
					//Frequence of car arrival
					try {
						Thread.sleep(frequenceOfArrival);
					} catch (InterruptedException e) {}
				}
			}
		}).start();
		
		
 	}
	
	
	private static boolean isInputsValid(String args[]) {
		
		if(args.length >4) {
			List<String> validBridgeNames = Arrays.asList("unsafe", "safe", "switch","fair");
			if(!validBridgeNames.contains(args[0])) {
				System.out.println("Wrong bridge name.");
				printExecutionFormat();
				return false;
			}
			
			for(int i=1; i<args.length; i++) {
				if(!args[i].chars().allMatch( Character::isDigit )) {
					System.out.println("Argument "+i+" should be a number");
					printExecutionFormat();
					return false;
				}
			}
			return true;
		}
		System.out.println("Wrong Number of arguments.");
		printExecutionFormat();
		return false;
		
	}
	
	private static void printExecutionFormat() {
		System.out.println("[+] Wrong Format");
		System.out.println("[+] First Argument is the bridge type : {\"unsafe\", \"safe\", \"switch\",\"fair\"}");
		System.out.println("[+] Second Argument is the number of blue cars.");
		System.out.println("[+] Third Argument is the number of red cars.");
		System.out.println("[+] Fourth Argument is the frequence of car arrivals. (millis)");
		System.out.println("[+] Fifth Argument is the bridge crossing time. (millis)");
		System.out.println("[+] Sixth Argument **Only for fair bridge** is the maximum number of cars crossing the bridge.");
		System.out.println("[+] E.g : java Main fair 20 30 400 600 5 ");		
	}
	
	private static void initialiazeBridge(String bridgeImplementation) {
		switch(bridgeImplementation) {
			case "unsafe":
				bridge = UnsafeBridge.getInstace();
				break;
			case "fair":
				bridge = FairBridge.getInstace();
				break;
			case "safe":
				bridge = SafeBridge.getInstace();
				break;
		}
	}

}
