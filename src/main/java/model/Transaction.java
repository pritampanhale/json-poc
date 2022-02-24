package model;

import lombok.Data;

@Data
public class Transaction {
	private String transactionId;
	private String transactionDateTime;
	private String transactionLifeCycleId;
	private String transactionType;
	private String transactionSubType;
	private String transactionChannel;

}
