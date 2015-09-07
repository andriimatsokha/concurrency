package concurrency.producerconsumer;

public class Producer implements Runnable {

	private MyQueue<Integer> queue;
	private Thread thread;
	private String name;
	
	public Producer(MyQueue queue, String name) {
		this.queue = queue;
		this.name = name;
		thread = new Thread(this);
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
		
		while (!Thread.currentThread().isInterrupted()) {
			queue.push(++counter);
			System.out.println("[" + name + "] Pushed " + counter);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				return;
			}
		}
	}

}
