package concurrency.producerconsumer;

import java.util.LinkedList;
import java.util.Queue;

public class MyQueue<T> {
	
	//private boolean empty = true;
	//private int val;
	private Queue<T> queue = new LinkedList<>();
	private int maxSize;
	
	
	
	public MyQueue(int maxSize) {
		this.maxSize = maxSize;
		queue = new LinkedList<>();
	}

	public synchronized void push(T elem) {
		
		while (queue.size() >= maxSize) {
			try {
				wait();
			} catch (InterruptedException e) {
				RuntimeException runtimeException = new RuntimeException();
				runtimeException.addSuppressed(e);
				throw runtimeException;
			}
		}
		
		queue.add(elem);
		
		System.out.println("Queue size() = " + queue.size());
		//this.val = val;
		//empty = false;
		this.notifyAll();
	}
	
	public synchronized T pop() {
		
		while(queue.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				RuntimeException runtimeException = new RuntimeException();
				runtimeException.addSuppressed(e);
				throw runtimeException;
			}
		}
		
		//empty = true;
		T elem = queue.poll();
		System.out.println("Queue size() = " + queue.size());
		this.notifyAll();
				
		return elem;
	}

}
