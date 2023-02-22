package exception;

import java.security.NoSuchAlgorithmException;

public class PKIException  extends RuntimeException {
    public PKIException(String msg){
        super(msg);
    }

    public PKIException(String msg, Throwable cause){
        super(msg, cause);
    }
}