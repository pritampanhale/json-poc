package service;

public abstract class BaseValidator {
	
	final public boolean validate() {
		boolean validateAsPerEntity = validateAsPerEntity();
		return validateAsPerEntity && true;
	}
	
	protected boolean validateAsPerEntity() {
		return true;
	}

}
