package GUI.exceptions;


public class FutureDateException extends Exception {
    public FutureDateException() {
        super("The date entered is in the future.");
        }
    public FutureDateException(String message) {
        super(message);
        }
    }

