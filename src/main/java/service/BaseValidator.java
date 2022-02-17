package service;

public abstract class BaseValidator {
	
	final public boolean validate() {
		// validation
		boolean validateAsPerEntity = validateAsPerEntity();
		return validateAsPerEntity && true;
	}
	
	protected boolean validateAsPerEntity() {
		return true;
	}

}
