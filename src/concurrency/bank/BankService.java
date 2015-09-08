package concurrency.bank;

public interface BankService {
	public boolean transfer(Account from, Account to, int amount);
}
