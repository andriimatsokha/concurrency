package concurrency.threadpool_v0;

public class PooledThread implements Runnable{

	private MyBlockingQueue tasksBlockingQueue;
	
	private Thread thread;

	public PooledThread(MyBlockingQueue tasksBlockingQueue) {
		this.tasksBlockingQueue = tasksBlockingQueue;
		
		thread = new Thread(this);
	}
	
	public void start() {
		thread.start();		
	}
	
	@Override
	public void run() {
		
		while(!Thread.currentThread().isInterrupted()) {
			Runnable task;
			try {
				task = tasksBlockingQueue.dequeue();
			} catch (InterruptedException e) {
				return;
			}
			
			task.run();
			
		}
		
	}
	
	
	public void interrupt() {
		thread.interrupt();
	}
	
	
}
