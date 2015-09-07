package concurrency.threadpool_v1;

public interface MyQueue {

	public abstract Runnable dequeue(boolean shutdown) throws InterruptedException;
	public abstract void enqueue(Runnable task);
	public abstract boolean isEmpty();

}