package crypto;

import utils.data.ByteUtils;

public class PublicKey {
    private byte[] exponent, modulus, remainder;

    private PublicKey() {}

    public byte[] getExponent() {
        return ByteUtils.copy(exponent);
    }

    public void setExponent(byte[] exponent) {
        this.exponent = exponent;
    }

    public byte[] getModulus() {
        return ByteUtils.mergeArrays(modulus, remainder);
    }

    public void setModulus(byte[] modulus) {
        this.modulus = modulus;
    }

    public byte[] getRemainder() {
        return ByteUtils.copy(remainder);
    }

    public void setRemainder(byte[] remainder) {
        this.remainder = remainder;
    }

    private int getLength() {
        return modulus.length + remainder.length;
    }

    public static class Builder {

        private byte[] exponent = new byte[0];
        private byte[] modules = new byte[0];
        private byte[] remainder = new byte[0];

        public Builder() {}

        public Builder setExponent(byte[] exponent) {
            this.exponent = exponent;
            return this;
        }

        public Builder setModules(byte[] modules) {
            this.modules = modules;
            return this;
        }

        public Builder setRemainder(byte[] remainder) {
            this.remainder = remainder;
            return this;
        }

        public PublicKey build() {
            PublicKey key = new PublicKey();
            key.setExponent(exponent);
            key.setModulus(modules);
            key.setRemainder(remainder);
            return key;
        }
    }
}
