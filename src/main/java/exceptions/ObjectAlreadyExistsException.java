package exceptions;

import javax.swing.text.GapContent;

public class ObjectAlreadyExistsException extends RuntimeException{

	public <T, G> ObjectAlreadyExistsException(T object,G target) {
		super("The "+object+ "Already Exists in "+target);
	}

	
}
