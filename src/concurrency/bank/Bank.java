package concurrency.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bank {

	private List<Account> accounts = new ArrayList<>();
	private Random random = new Random();
	
	public void addAccount(Account acc) {
		accounts.add(acc);
	}
	
	public Account getRandomAccount() {
		return accounts.get(random.nextInt(accounts.size()));
	}

	public int calcTotalAmount() {
		int total = 0;
		for(Account acc : accounts) {
			total += acc.getAmount();
		}
		return total;
	}
}
