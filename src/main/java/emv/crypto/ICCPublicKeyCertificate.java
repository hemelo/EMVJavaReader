package emv.crypto;

import crypto.Certificate;
import crypto.PublicKey;
import emv.application.EMVApplication;
import exception.PKIException;
import exception.ParseException;
import utils.crypto.HashUtils;
import utils.crypto.RSADecipher;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class ICCPublicKeyCertificate extends Certificate {

    private EMVApplication application;
    private IssuerPublicKeyCertificate issuerPublicKeyCertificate;

    private byte[] pan;

    public ICCPublicKeyCertificate(EMVApplication application, IssuerPublicKeyCertificate issuerPublicKeyCertificate) {
        this.application = application;
        this.issuerPublicKeyCertificate = issuerPublicKeyCertificate;
        pan = new byte[10];
    }

    @Override
    public boolean isValid() throws ParseException {

        if (validationPerformed) return valid;

        if (this.issuerPublicKeyCertificate == null)  {
            this.issuerPublicKeyCertificate = application.getIssuerPublicKeyCertificate();
        }

        if (this.issuerPublicKeyCertificate == null || !this.issuerPublicKeyCertificate.isValid()) {
            return setAndGetValid(false);
        }

        PublicKey issuerKey = issuerPublicKeyCertificate.getPublicKey();

        byte[] data = RSADecipher.decipher(signedBytes, issuerKey);
        ByteArrayInputStream stream = new ByteArrayInputStream(data);

        int header = stream.read();

        if (header != 0x6A) {
            throw new ParseException("Certificate data Header is not equals 0x6A");
        }

        this.certFormat = (byte) stream.read();

        if (certFormat != 0x04) {
            throw new PKIException("Invalid certificate format");
        }

        stream.read(pan, 0, pan.length);
        stream.read(this.certExpirationDate, 0, this.certExpirationDate.length);
        stream.read(this.certSerialNumber, 0, this.certSerialNumber.length);

        this.hashAlgorithmIndicator   = stream.read() & 0xFF;
        this.publicKeyAlgorithmIndicator = stream.read() & 0xFF;

        int publicKeyModLength = stream.read() & 0xFF;
        int publicKeyExpLength = stream.read() & 0xFF;
        int modBytesLength = stream.available() - 21;

        if (publicKeyModLength < modBytesLength) {
            modBytesLength = publicKeyModLength;
        }

        byte[] modTemporary = new byte[modBytesLength];
        stream.read(modTemporary, 0, modTemporary.length);
        publicKey.setModulus(modTemporary);

        byte[] padding = new byte[stream.available() - 21];
        stream.read(padding, 0, padding.length);

        stream.read(this.hash, 0, this.hash.length);

        byte[] auxPublicKeyModulus = publicKey.getModulus();
        byte[] auxPublicKeyExponent = publicKey.getExponent();

        // HASH VERIFY

        ByteArrayOutputStream hStream = new ByteArrayOutputStream();
        hStream.write(this.certFormat);
        hStream.write(this.pan, 0, this.pan.length);
        hStream.write(this.certExpirationDate, 0, this.certExpirationDate.length);
        hStream.write(this.certSerialNumber, 0, this.certSerialNumber.length);
        hStream.write((byte) this.hashAlgorithmIndicator);
        hStream.write((byte) this.publicKeyAlgorithmIndicator);
        hStream.write((byte) publicKeyModLength);
        hStream.write((byte) publicKeyExpLength);

        int nPaddingBytes = issuerKey.getModulus().length - auxPublicKeyModulus.length - 42;
        hStream.write(auxPublicKeyModulus, 0, auxPublicKeyModulus.length);

        for (int i = 0; i < nPaddingBytes; i++) {
            hStream.write((byte) 0xBB);
        }

        hStream.write(auxPublicKeyExponent, 0, auxPublicKeyExponent.length);

        byte[] offlineAuthenticationRecords = application.getOfflineDataAuthenticationRecords();
        hStream.write(offlineAuthenticationRecords, 0, offlineAuthenticationRecords.length);

        byte[] hash = HashUtils.SHA1(hStream.toByteArray());

        if (! Arrays.equals(hash, this.hash)) {
            throw new ParseException("Issuer Certificate Hash is not valid");
        }

        int trailer = stream.read();

        if (trailer != 0xBC) {
            throw new ParseException("Certificate data Trailer is not equals 0xBC");
        }

        if (stream.available() > 0) {
            throw new ParseException("Error parsing certificate. Bytes left on parsing: " + stream.available() + "bytes");
        }

        return this.setAndGetValid(true);
    }
}
