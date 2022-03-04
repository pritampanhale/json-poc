package model;

import lombok.Data;

@Data
public class BillingAmount {
	private String value;
	private String ccy;
	private String exchangerate;
	private String date;
}
