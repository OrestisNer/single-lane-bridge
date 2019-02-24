import java.util.concurrent.Semaphore;

public class Main {

	public static void main(String[] args) {
		
		final int noBlueCars = 15;
		final int noRedCars = 20;
		
		int frequenceOfArrival = 2000; //milis
		int crossingTime = 200; //milis
		
		int maxNoOfCrossingCars = 3;
		
		Bridge.getInstace().setMaxNoOfCrossingCar(maxNoOfCrossingCars);
		Bridge.getInstace().setCrossingTime(crossingTime);
		
		
		new Thread(new Runnable() {
			public void run() {
				for(int i=0; i<noBlueCars; i++) {
					Bridge.getInstace().increaseTime();
					new BlueCar(i+1).start();
					try {
						Thread.sleep(400);
					} catch (InterruptedException e) {}
				}
			}
		}).start();
		
		
		new Thread(new Runnable() {
			public void run() {
				for(int i=0; i<noRedCars; i++) {
					Bridge.getInstace().increaseTime();
					new RedCar(i+1).start();
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {}
				}
			}
		}).start();
		
		
 	}

}
