package service;

import exceptions.GenericValidationError;

public class SampleBankValidator extends BaseValidator {

	@Override
	protected boolean validateAsPerEntity() {
		// Validation code for Sample bank
		throw new GenericValidationError("Generic validation error");
	}

}
