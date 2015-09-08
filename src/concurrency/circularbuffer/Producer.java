package concurrency.circularbuffer;

public class Producer implements Runnable {

	private BlockingCircularBuffer<String> queue;
	private Thread thread;
	private String name;
	
	public Producer(BlockingCircularBuffer<String> queue, String name) {
		this.queue = queue;
		this.name = name;
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
		
		int counter = 0;
		
		while (!Thread.interrupted()) {
			try {
				queue.push(name + "->" + ++counter);
			
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				return;
			}
		}
	}

}
