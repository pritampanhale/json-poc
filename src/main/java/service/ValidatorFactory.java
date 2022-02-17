package service;

import constants.Entities;

public class ValidatorFactory {
	

	private ValidatorFactory() {
		
	}
	
	public static BaseValidator getValidator(String entity) {
		Entities ent = Entities.valueOf(entity);
		
		switch (ent) {
		case SAMPLE_BANK:
			return new SampleBankValidator();

		default:
			break;
		}
		return null;
	}

}
