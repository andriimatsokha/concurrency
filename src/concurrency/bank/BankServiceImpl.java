package concurrency.bank;

public class BankServiceImpl implements BankService {

	@Override
	public boolean transfer(Account from, Account to, int amount) {
		
		Account first = from.getId() < to.getId() ? from : to;
		Account second = from.getId() > to.getId() ? from : to;
		
		synchronized (first) {
			synchronized (second) {
				if (from.withdraw(amount)) {
					to.deposit(amount);
					System.out.format("Transfer from acc [%d] to acc [%d] amount [%d]%n", from.getId(), to.getId(), amount);
					return true;
				}
			}
		}
		System.out.format("[FAIL] Transfer from acc [%d] to acc [%d] amount [%d]%n", from.getId(), to.getId(), amount);
		return false;
	}
	
}
