package crypto;

import exception.ParseException;

public abstract class Certificate {

    protected PublicKey publicKey;
    protected byte[] signedBytes;
    protected byte[] certExpirationDate;
    protected byte[] certSerialNumber;
    protected byte certFormat;
    protected int hashAlgorithmIndicator;
    protected int publicKeyAlgorithmIndicator;
    protected byte[] hash;
    protected boolean valid;
    protected boolean validationPerformed;

    public Certificate() {
        certExpirationDate = new byte[2];
        certSerialNumber = new byte[3];
        hash = new byte[20];
        valid = false;
        validationPerformed = false;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public boolean setAndGetValid(boolean valid) {
        this.validationPerformed = true;
        this.valid = valid;
        return this.valid;
    }

    public abstract boolean isValid() throws ParseException;
}
