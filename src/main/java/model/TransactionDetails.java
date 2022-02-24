package model;

import lombok.Data;

@Data
public class TransactionDetails {
	private Transaction transaction;
	private Consumer consumer;
	private Amounts amounts;
	private DebatorAccount debatorAccount;
	private CreditorAccount creditorAccount;
	private Merchant merchant;
}
