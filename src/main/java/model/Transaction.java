package model;

public class Transaction {
	private String transactionId;
	private String transactionDateTime;
	private String transactionLifeCycleId;
	private String transactionType;
	private String transactionSubType;
	private String transactionChannel;

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionDateTime() {
		return transactionDateTime;
	}

	public void setTransactionDateTime(String transactionDateTime) {
		this.transactionDateTime = transactionDateTime;
	}

	public String getTransactionLifeCycleId() {
		return transactionLifeCycleId;
	}

	public void setTransactionLifeCycleId(String transactionLifeCycleId) {
		this.transactionLifeCycleId = transactionLifeCycleId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getTransactionSubType() {
		return transactionSubType;
	}

	public void setTransactionSubType(String transactionSubType) {
		this.transactionSubType = transactionSubType;
	}

	public String getTransactionChannel() {
		return transactionChannel;
	}

	public void setTransactionChannel(String transactionChannel) {
		this.transactionChannel = transactionChannel;
	}
}
