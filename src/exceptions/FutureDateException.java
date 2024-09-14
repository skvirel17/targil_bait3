package exceptions;

import java.util.Date;

import control.Hospital;

public class FutureDateException extends RuntimeException{

	public FutureDateException(Date date) {
		super("The date"+date+" is a future date. Please enter a date previous to "+Hospital.TODAY);
	}

}
