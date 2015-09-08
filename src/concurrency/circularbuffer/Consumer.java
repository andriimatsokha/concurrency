package concurrency.circularbuffer;

public class Consumer implements Runnable {

	private BlockingCircularBuffer<String> queue;
	private Thread thread;
	//private String name;
	
	public Consumer(BlockingCircularBuffer<String> queue, String name) {
		this.queue = queue;
		//this.name = name;
		thread = new Thread(this, name);
	}
	
	public void start() {
		thread.start();
	}
	
	public void interrupt() {
		thread.interrupt();
	}
	
	@Override
	public void run() {
		while(!Thread.interrupted()) {
			try {
				String val = queue.pop();
				
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				return;
			}
		}
	}

}
