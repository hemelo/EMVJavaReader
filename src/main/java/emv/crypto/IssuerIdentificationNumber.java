package emv.crypto;

import utils.data.BinaryCodedUtils;
import utils.data.ByteUtils;

import java.util.Arrays;

public class IssuerIdentificationNumber {

    byte[] identificationNumber;

    public IssuerIdentificationNumber(byte[] identificationNumber) {

        if (identificationNumber == null) {
            throw new IllegalArgumentException("Argument \"identificationNumber\" cannot be null");
        } else if (identificationNumber.length != 3) {
            throw new IllegalArgumentException("Argument \"identificationNumber\" must have length 3");
        }

        this.identificationNumber = ByteUtils.copy(identificationNumber);
    }

    public IssuerIdentificationNumber(int identificationNumber) {
        if (identificationNumber > 1000000 || identificationNumber < 0) {
            throw new IllegalArgumentException("Argument \"identificationNumber\" must be between 0 and 999999");
        }

        byte[] iinBytes = BinaryCodedUtils.intToBinaryCodedDecimalByteArray(identificationNumber);

        if (iinBytes.length != 6)
            iinBytes = ByteUtils.ressizeArray(iinBytes, 6);

        this.identificationNumber = iinBytes;
    }

    public int getValue() {
        return BinaryCodedUtils.binaryHexCodedDecimalToInt(identificationNumber);
    }

    public byte[] getBytes() {
        return ByteUtils.copy(this.identificationNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IssuerIdentificationNumber that = (IssuerIdentificationNumber) o;

        return Arrays.equals(identificationNumber, that.identificationNumber);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(identificationNumber);
    }
}
