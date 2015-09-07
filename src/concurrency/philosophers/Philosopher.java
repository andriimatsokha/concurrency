package concurrency.philosophers;

public class Philosopher implements Runnable {

	private String name;
	private Fork leftFork;
	private Fork rightFork;
	
	private int eatTimes;
	
	private Thread thread;
	
	public Philosopher(String name, Fork leftFork, Fork rightFork) {
		this.name = name;
		this.leftFork = leftFork;
		this.rightFork = rightFork;
		
		thread = new Thread(this);
	}

	public void start() {
		thread.start();
	}
	
	public void interrupt() {
		thread.interrupt();
	}


	/**
	 * Solution: use partial order on forks.
	 * 			Each philosopher first tries to take fork with lower number(order), 
	 * 			second - tries to take fork with higher number.
	 * 			Release forks in reverse order. (*Release in the same order also seems to work)
	 */
	@Override
	public void run() {
		
		while(!Thread.currentThread().isInterrupted()) {
			
			Fork firstFork = null;
			Fork secondFork = null;
			
			try {

				if (leftFork.getOrder()  < rightFork.getOrder()) {
					firstFork = leftFork;
					secondFork = rightFork;
				} else {
					firstFork = rightFork;
					secondFork = leftFork;
				}
				
				System.out.println("[" + name + "] Trying to take first fork...");
				firstFork.take();
				System.out.println("[" + name + "] Took first fork");
				System.out.println("[" + name + "] Trying to take second fork...");
				secondFork.take();
				System.out.println("[" + name + "] Took second fork");
				
				System.out.println("[" + name + "] I'm eating..");
				
				eatTimes++;
			
				Thread.sleep(5);
				
				secondFork.release();
				firstFork.release();
				
				
				//firstFork.release();
				//secondFork.release();
				
				System.out.println("[" + name + "] Release both forks");
				System.out.println("[" + name + "] Release both forks");
				
			} catch (InterruptedException e) {
				return;
			}
			
		}
	}

	@Override
	public String toString() {
		return "Philosoph [name=" + name + ", eatTimes=" + eatTimes + "]";
	}
	
	

}
