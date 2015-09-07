package concurrency.threadpool_v0;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
	
	private MyBlockingQueue tasksBlockingQueue;
	
	private List<PooledThread> threads;
	
	public static ThreadPool newThreadPool(int nThreads) {
		ThreadPool threadPool = new ThreadPool(nThreads);
		
		for (PooledThread pt : threadPool.threads) {
			pt.start();
		}
		
		return threadPool;
	}
	
	private ThreadPool(int nThreads) {
		threads = new LinkedList<>();
		tasksBlockingQueue = new MyBlockingQueue();
		
		for (int i = 0; i < nThreads; i++) {
			threads.add(new PooledThread(tasksBlockingQueue));
		}
	}
	
	public void shutdown() {
		for (PooledThread pt : threads) {
			pt.interrupt();
		}
	}
	
	public void submit(Runnable task) {
		tasksBlockingQueue.enqueue(task);
	}
	
	public boolean hasTasks() {
		return tasksBlockingQueue.size() > 0;
	}
	
	
	
}
