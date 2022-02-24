package model;

import lombok.Data;

@Data
public class Transaction {
	private String transactionId;
	private String transactionDateTime;
	private String transactionLifeCycleId;
	private String transactionoriginatedcountrycode;
	private String transactionType;
	//Commented this field for POC
	//private String transactionSubType;
	private String transactionChannel;

}
