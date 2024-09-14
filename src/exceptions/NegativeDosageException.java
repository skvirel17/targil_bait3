package exceptions;

public class NegativeDosageException extends RuntimeException{

	public NegativeDosageException(double dosage) {
		super("The dose "+dosage+" is negative. Please make sure to enter a non-negative dose");
	}

}
