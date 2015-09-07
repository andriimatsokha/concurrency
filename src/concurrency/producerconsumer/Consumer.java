package concurrency.producerconsumer;

public class Consumer implements Runnable {

	private MyQueue<Integer> queue;
	private Thread thread;
	private String name;
	
	public Consumer(MyQueue queue, String name) {
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
		while(!Thread.interrupted()) {
			int val = queue.pop();
			
			System.out.println("[" + name + "] Popped " + val);
			
		}
	}

}
