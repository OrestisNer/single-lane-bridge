
public class Main {

	public static void main(String[] args) {
		
		final int noBlueCars = 20;
		final int noRedCars = 15;
		
		int frequenceOfArrival = 2000; //milis
		int crossingTime = 200; //milis
		
		int maxNoOfCrossingCars = 3;
		
		Bridge.getInstace().setMaxNoOfCrossingCar(maxNoOfCrossingCars);
		Bridge.getInstace().setCrossingTime(crossingTime);
		
	
		
		new Thread(new Runnable() {
			public void run() {
				for(int i=0; i<noBlueCars; i++) {
					new BlueCar(i+1).start();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {}
				}
			}
		}).start();
		
		
		new Thread(new Runnable() {
			public void run() {
				for(int i=0; i<noRedCars; i++) {
					new RedCar(i+1).start();
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {}
				}
			}
		}).start();;
		
		//System.out.println("Bye Bye from main.");
		
 	}

}
