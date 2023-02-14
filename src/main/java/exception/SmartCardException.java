package exception;

public class SmartCardException extends RuntimeException {
	
	 public SmartCardException(String message){
        super(message);
    }

    public SmartCardException(String message, Throwable cause){
        super(message, cause);
    }
    public SmartCardException(Throwable cause){
        super(cause);
    }
}
