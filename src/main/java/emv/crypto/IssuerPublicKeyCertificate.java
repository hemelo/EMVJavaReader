package emv.crypto;

import crypto.Certificate;
import crypto.CertificationAuthority;
import exception.PKIException;
import exception.ParseException;
import exception.SmartCardException;
import utils.crypto.HashUtils;
import utils.crypto.RSADecipher;
import utils.data.BinaryCodedUtils;
import utils.data.ByteUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class IssuerPublicKeyCertificate extends Certificate {

    private CertificationAuthority certificationAuthority;
    private int issuerIdentifier;

    public IssuerPublicKeyCertificate(CertificationAuthority certificationAuthority) {
        super();
        this.issuerIdentifier = -1;
        this.certificationAuthority = certificationAuthority;
    }

    @Override
    public boolean isValid() throws ParseException {

        if (validationPerformed) return valid;

        if (this.certificationAuthority == null)  {
            return setAndGetValid(false);
        }

        byte[] data = RSADecipher.decipher(signedBytes, publicKey);
        ByteArrayInputStream stream = new ByteArrayInputStream(data);

        int header = stream.read();

        if (header != 0x6A) {
            throw new ParseException("Certificate data Header is not equals 0x6A");
        }

        this.certFormat = (byte) stream.read();

        if (certFormat != 0x02) {
            throw new PKIException("Invalid certificate format");
        }

        byte[] issuerIdentifierPadded = new byte[4];
        stream.read(issuerIdentifierPadded, 0, issuerIdentifierPadded.length);
        this.issuerIdentifier = getIssuerIdentifierFromByteArray(issuerIdentifierPadded);

        stream.read(certExpirationDate, 0, certExpirationDate.length);
        stream.read(certSerialNumber, 0, certSerialNumber.length);

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

        ByteArrayOutputStream hStream = new ByteArrayOutputStream();
        hStream.write(this.certFormat);
        hStream.write(issuerIdentifierPadded, 0, issuerIdentifierPadded.length);
        hStream.write(this.certExpirationDate, 0, this.certExpirationDate.length);
        hStream.write(this.certSerialNumber, 0, this.certSerialNumber.length);
        hStream.write((byte) this.hashAlgorithmIndicator);
        hStream.write((byte) this.publicKeyAlgorithmIndicator);
        hStream.write((byte) publicKeyModLength);
        hStream.write((byte) publicKeyExpLength);
        hStream.write(auxPublicKeyModulus, 0, auxPublicKeyModulus.length);
        hStream.write(auxPublicKeyExponent, 0, auxPublicKeyExponent.length);

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

    private static int getIssuerIdentifierFromByteArray(byte[] issuerIdentifierPadded) {
        String hex = ByteUtils.toHexString(issuerIdentifierPadded);
        int padStartPos = hex.toUpperCase().indexOf('F');

        if (padStartPos != -1) {
            hex = hex.substring(0, padStartPos);
        }

        return BinaryCodedUtils.binaryHexCodedDecimalToInt(hex);
    }

}
