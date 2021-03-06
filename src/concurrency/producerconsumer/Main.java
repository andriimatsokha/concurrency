package concurrency.producerconsumer;

import java.util.ArrayList;
import java.util.List;

public class Main {
	
	private final static int COUNT_PRODUCERS = 2;
	private final static int COUNT_CONSUMERS = 4;
	
	public static void main(String[] args) throws Exception {
		
		List<Producer> producers = new ArrayList<>();
		List<Consumer> consumers = new ArrayList<>();
		
		MyQueue queue = new MyQueue(5);
		
		for(int i = 0; i < COUNT_PRODUCERS; i++) {
			producers.add(new Producer(queue, "Producer#" + i));
		}
		
		Thread.sleep(2000);
		
		for(int i = 0; i < COUNT_CONSUMERS; i++) {
			consumers.add(new Consumer(queue, "Consumer#" + i));
		}
		
		for(Consumer consumer : consumers) {
			consumer.start();
		}
		Thread.sleep(100);
		for(Producer producer : producers) {
			producer.start();
		}
		
		Thread.sleep(20_000);
		
		for(Consumer consumer : consumers) {
			consumer.interrupt();;
		}
		for(Producer producer : producers) {
			producer.interrupt();;
		}
		
	}
	
}
