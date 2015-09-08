package concurrency.bank;

public class Account {
	
	//Unique id
	private int id;
	private int amount;

	public Account(int id, int amount) {
		this.id = id;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void deposit(int amount) {
		this.amount += amount;
	}

	public boolean withdraw(int amount) {
		if (this.amount >= amount) {
			this.amount -= amount;
			return true;
		}
		return false;
	}

}
