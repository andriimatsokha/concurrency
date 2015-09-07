package concurrency.threadpool_v1;

import java.util.LinkedList;

public class MyQueueImpl implements MyQueue {

	private LinkedList<Runnable> tasks = new LinkedList<>();
	
	/**
	 * If shutdown == false, work in blocking mode; otherwise - non-blocking mode.
	 * In blocking mode: if tasks-list is empty - wait() until it new task will be added,
	 *                  otherwise - return task;
	 * In non-blocking mode: if tasks-list is empty - return null,
	 * 					otherwise - return task;
	 * @return task from task-list
	 * @throws InterruptedException
	 */
	@Override
	public synchronized Runnable dequeue(boolean shutdown) throws InterruptedException {
		
		if (shutdown) {
			if (tasks.isEmpty()) {
				return null;
			}
			System.out.println("[" + Thread.currentThread().getName() + "] --Process task. Tasks count = " + (tasks.size()-1));
			
			return tasks.removeFirst();
		}
		
		while (tasks.isEmpty()) {
			this.wait();
		}
		
		System.out.println("[" + Thread.currentThread().getName() + "] --Process task. Tasks count = " + (tasks.size()-1));
		
		return tasks.removeFirst();
	}
	
	
	/**
	 * Add task to queue
	 * @param task
	 */
	@Override
	public synchronized void enqueue(Runnable task) {
		
		tasks.add(task);
		
		System.out.println("[" + Thread.currentThread().getName() + "] --Add task. Tasks count = " + tasks.size());
		
		this.notifyAll();
		
	}
	
	@Override
	public synchronized boolean isEmpty() {	
		return tasks.size() == 0;
	}
	
}
