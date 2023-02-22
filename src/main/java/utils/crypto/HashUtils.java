package utils.crypto;

import exception.PKIException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {
    public static byte[] SHA1(byte[] data)  {
        try {
            MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
            return sha1.digest(data);
        } catch (NoSuchAlgorithmException ex) {
            throw new PKIException("SHA-1 hash algorithm not available", ex);
        }
    }
}
