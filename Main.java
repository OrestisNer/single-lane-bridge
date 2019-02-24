
public class Main {

	public static void main(String[] args) {
		
		int noBlueCars = 20;
		int noRedCars = 15;
		
		int frequenceOfArrival = 2000; //milis
		int crossingTime = 200; //milis
		
		int maxNoOfCrossingCars = 3;
		
		Bridge.getInstace().setMaxNoOfCrossingCar(maxNoOfCrossingCars);
		Bridge.getInstace().setCrossingTime(crossingTime);
		
		int id = 1;
		while(true) {
					
			if(noBlueCars > 0) {
				new BlueCar(id).start();
				noBlueCars--;
			}
			
			if(noRedCars > 0) {
				new RedCar(id).start();
				noRedCars--;
			}
			
			id++;
			
			if(noRedCars == 0 && noBlueCars == 0)
				break;		
			
			try {
				Thread.sleep(frequenceOfArrival);
			} catch (InterruptedException e) {}
		}
		
		System.out.println("Bye Bye from main.");
		
 	}

}
