package model;

import lombok.Data;

@Data
public class Address {
	private String addressType;
	private String  address;
	private String  city;
	private String postalCode;
	private String  country;
}
