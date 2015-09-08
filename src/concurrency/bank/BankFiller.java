package concurrency.bank;

import java.util.Random;

public class BankFiller {

	private Random random = new Random();
	
	public void fillBankWithRandomAccounts(Bank bank, int num) {
		for(int i = 0; i < num; i++) {
			bank.addAccount(new Account(i, random.nextInt(1000)));
		}
	}
	
}
