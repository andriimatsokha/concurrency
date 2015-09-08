package concurrency.bank;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main {
	
	private static class Task {
		
		private final Account from;
		private final Account to;
		private final int amount;
	
		public Task(Account from, Account to, int amount) {
			this.from = from;
			this.to = to;
			this.amount = amount;
		}
	}
	
	private static class Transaction implements Runnable {

		private Task task;
		private BankService bankService;
		
		public Transaction(Task task, BankService bankService) {
			this.task = task;
			this.bankService = bankService;
		}

		@Override
		public void run() {
			bankService.transfer(task.from, task.to, task.amount);
		}
		
	}
	
	private static int NUM_THREADS = 20_000;
	
	public static void main(String[] args) throws InterruptedException {
		Main main = new Main();
		
		Bank bank = new Bank();
		
		main.fillBank(bank);
		int totalBefore = bank.calcTotalAmount();
		main.runTransactionsInThreads(bank, NUM_THREADS);
		
		System.out.println("Total before transfer = " + totalBefore);
		System.out.println("Total after transfer = " + bank.calcTotalAmount());
		
	}
	
	
	
	private void fillBank(Bank bank) {
		BankFiller bankFiller = new BankFiller();
		bankFiller.fillBankWithRandomAccounts(bank, 1000);
	}
	
	private void runTransactionsInThreads(Bank bank, int numThreads) throws InterruptedException {
		BankService bankService = new BankServiceImpl();
		
		Random random = new Random();
		List<Thread> threads = new LinkedList<Thread>();
		
		for(int i = 0; i < numThreads; i++) {
			Account from = bank.getRandomAccount();
			Account to = bank.getRandomAccount();
			int amount = random.nextInt(500); 
			
			Thread thread = new Thread(new Transaction(new Task(from, to, amount), bankService));
			threads.add(thread);
		}
		
		for(Thread t : threads) {
			t.start();
		}

		for(Thread t : threads) {
			t.join();
		}
	}
	
}
