package model;

import lombok.Data;

@Data
public class TransactionDetails {
	private Transaction transaction;
	private Consumer consumer;
	private DebatorAccount debatorAccount;
}
