package model;

import lombok.Data;

@Data
public class Amounts {
	private TransactionAmount transactionAmount;
	private BillingAmount billingAmount;
	private AdditionalAmount additionalAmount;
}
