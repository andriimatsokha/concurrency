package concurrency.circularbuffer;

public class BlockingCircularBuffer<T> {

	private T[] buffer;

	private int indexPush;
	private int indexPop;
	private int size;
	
	@SuppressWarnings("unchecked")
	public BlockingCircularBuffer(int n) {
		this.buffer = (T[]) new Object[n];
		this.indexPop = 0;
		this.indexPush = 0;
		this.size = 0;
	}
	
	public synchronized void push(T elem) throws InterruptedException {
		
		while( indexPush == indexPop && size == buffer.length) {
			wait();
		}
		
		buffer[indexPush] = elem;
		indexPush = (indexPush+1)%buffer.length;
		size++;
		
		System.out.println("[" + Thread.currentThread().getName() + "] Pushed {" + elem + "}");
		printBufferState();
		
		notifyAll();
	}
	
	public synchronized T pop() throws InterruptedException {
		
		while (indexPush == indexPop && size == 0) {
			wait();
		}
		
		T elem = buffer[indexPop];
		buffer[indexPop] = null;
		indexPop = (indexPop+1)%buffer.length;
		size--;
		
		System.out.println("[" + Thread.currentThread().getName() + "] Popped {" + elem + "}");
		printBufferState();
		
		notifyAll();
		
		return elem;
	}
	
	
	private void printBufferState() {
		
		System.out.print("[ ");
		for (int i = 0; i < buffer.length; i++) {
			if (indexPush > indexPop) {
				if (i >= indexPop && i < indexPush) {
					System.out.print("* ");
				} else {
					System.out.print("- ");
				}
			} else {
				if (indexPop == indexPush) {
					if (size == buffer.length) {
						System.out.print("* ");
					} else {
							System.out.print("- ");
					}
				} else {
					if (i < indexPush || i >= indexPop) {
						System.out.print("* ");
					} else {
						System.out.print("- ");
					}
				}
			}
		}
		System.out.println("]");
		
	}
	
	
	
}
