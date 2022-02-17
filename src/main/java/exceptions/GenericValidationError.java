package exceptions;

public class GenericValidationError extends RuntimeException{

	public GenericValidationError(String err) {
		super(err);
	}
}
