package concurrency.threadpool_v0;

public class ThreadPoolRunner {

	private static class MyTask implements Runnable {

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + " started task");
			int sum = 0;
			for (long i = 0; i < 200_000_000L; i++) {
				sum += i;
			}
			System.out.println(Thread.currentThread().getName() + " completed task. Result = " + sum);
			
		}
		
	}
	
	
	public static void main(String[] args) throws Exception {
		
		//Thread.sleep(15000);
		
		ThreadPool threadPool = ThreadPool.newThreadPool(5);
		
		System.out.println("Thread pool created");
		
		Runnable task = new MyTask();
		
		for (int i = 0; i < 20; i++) {
			threadPool.submit(task);
		}
		
		while (threadPool.hasTasks()) {
			Thread.sleep(5000);
		}
		
		System.out.println("All tasks completed. Shutdown");
		
		threadPool.shutdown();
		
	}
	
}
