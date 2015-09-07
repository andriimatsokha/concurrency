package concurrency.philosophers;

public class Fork {
	
	private boolean free = true;
	private final int order;
	
	public Fork(int order) {
		this.order = order;
	}

	public synchronized void take() throws InterruptedException {
		
			while(!free) {
				this.wait();
			}
			free = false;
		
	}
	
	public synchronized void release() {
		free = true;
		this.notifyAll();
	}

	public int getOrder() {
		return order;
	}
	
	
	
}
