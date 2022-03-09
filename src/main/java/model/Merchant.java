package model;

import lombok.Data;

@Data
public class Merchant {
	private String merchantId;
	private String merchantName;
	private String merchantcategoryCode;
	//Commented this field for POC
	//private SupplementaryData supplementaryData;
}
