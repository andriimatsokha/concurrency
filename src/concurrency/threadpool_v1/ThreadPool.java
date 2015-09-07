package concurrency.threadpool_v1;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
	
	private MyQueue tasksBlockingQueue;
	
	private List<PooledThread> threads;
	
	private volatile boolean shutdown = false; 
	
	public static ThreadPool newThreadPool(int nThreads) {
		ThreadPool threadPool = new ThreadPool(nThreads, new MyQueueImpl());
		
		for (PooledThread pt : threadPool.threads) {
			pt.start();
		}
		
		return threadPool;
	}

	private ThreadPool(int nThreads, MyQueue tasksBlockingQueue) {
		threads = new LinkedList<>();
		this.tasksBlockingQueue = tasksBlockingQueue;
		
		for (int i = 0; i < nThreads; i++) {
			threads.add(new PooledThread(tasksBlockingQueue));
		}
	}
	
	public void shutdown() {
		shutdown = true;
		for (PooledThread pt : threads) {
			pt.shutdown();
		}
	}
	
	public void submit(Runnable task) {
		if (shutdown) {
			throw new RuntimeException("Thread pool is already closed!");
		}

		tasksBlockingQueue.enqueue(task);
	}
	
}
