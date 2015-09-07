package concurrency.philosophers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
	
	private static final int NUMBER_PHILOSOPHERS_AND_FORKS = 5;

	public static void main(String[] args) throws Exception {
		
		List<Philosopher> philosophers = new ArrayList<>(NUMBER_PHILOSOPHERS_AND_FORKS);
		List<Fork> forks = new ArrayList<>(NUMBER_PHILOSOPHERS_AND_FORKS);
		
		for (int i = 0; i < NUMBER_PHILOSOPHERS_AND_FORKS; i++) {
			forks.add(new Fork(i));
		}
		
		for (int i = 0; i < NUMBER_PHILOSOPHERS_AND_FORKS; i++) {
			
			int indexOfLeftFork = i;
			int indexOfRightFork = (i+1) % NUMBER_PHILOSOPHERS_AND_FORKS;
			Fork leftFork = forks.get(indexOfLeftFork);
			Fork rightFork = forks.get(indexOfRightFork);
			
			//System.out.println(i + " <-> " + indexOfRightFork);
			
			philosophers.add(new Philosopher("Philosoph"+i, forks.get(indexOfLeftFork), forks.get(indexOfRightFork)));
		}
		
		for (Philosopher philosopher : philosophers) {
			philosopher.start();
		}
		
		//Thread.sleep(120_000);
		TimeUnit.SECONDS.sleep(10);
		
		try {
			for (Philosopher philosopher : philosophers) {
				philosopher.interrupt();
			}
		} catch (Exception e) {
		}
		
		System.out.println("---------------------");
		
		for (Philosopher philosopher : philosophers) {
			System.out.println(philosopher);
		}
	}
	
}
