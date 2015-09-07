package concurrency.threadpool_v1;

public class PooledThread implements Runnable{

	private MyQueue tasksBlockingQueue;
	
	private Thread thread;

	private volatile boolean shutdown = false;

	public PooledThread(MyQueue tasksBlockingQueue) {
		this.tasksBlockingQueue = tasksBlockingQueue;
		
		thread = new Thread(this);
	}
	
	public void start() {
		thread.start();
	}

	@Override
	public void run() {
		while(true) {
			Runnable task;
			try {
				task = tasksBlockingQueue.dequeue(shutdown);
				if (task != null) {
					task.run();
				}
			} catch (InterruptedException e) {
			} finally {
				if (shutdown && tasksBlockingQueue.isEmpty()) {
					return;
				}
			}
		}
	}
	
	public void shutdown() {
		shutdown = true;
		thread.interrupt();
	}
	
	
}
