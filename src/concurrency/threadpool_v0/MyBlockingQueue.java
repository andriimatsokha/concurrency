package concurrency.threadpool_v0;

import java.util.LinkedList;

public class MyBlockingQueue {

	private LinkedList<Runnable> tasks = new LinkedList<>();
	
	public synchronized Runnable dequeue() throws InterruptedException {
		
		while (tasks.isEmpty()) {
			this.wait();
		}
		
		System.out.println("[" + Thread.currentThread().getName() + "] --Process task. Tasks count = " + (tasks.size()-1));
		
		return tasks.removeFirst();
	}
	
	
	public synchronized void enqueue(Runnable task) {
		
		tasks.add(task);
		
		System.out.println("[" + Thread.currentThread().getName() + "] --Add task. Tasks count = " + tasks.size());
		
		this.notifyAll();
		
	}
	
	public synchronized int size() {	
		return tasks.size();
	}
	
}
