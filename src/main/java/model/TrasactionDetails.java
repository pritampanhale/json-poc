package model;

public class TrasactionDetails {
	private Transaction transaction;
	private Consumer consumer;
	private DebatorAccount debatorAccount;

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public Consumer getConsumer() {
		return consumer;
	}

	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}

	public DebatorAccount getDebatorAccount() {
		return debatorAccount;
	}

	public void setDebatorAccount(DebatorAccount debatorAccount) {
		this.debatorAccount = debatorAccount;
	}

}
