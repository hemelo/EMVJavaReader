package emv.verification;

public class CardVerificationRule {

    private byte firstByte;
    private byte secondByte;
    private byte[] amountFieldXBytes;
    private String amountFieldXStr;
    private byte[] secondAmountFieldYBytes;
    private String secondAmountFieldYStr;
    private Rule rule;

    public CardVerificationRule(byte firstByte, byte secondByte, byte[] amountFieldX, byte[] secondAmountFieldY) {
        this.firstByte = firstByte;
        this.secondByte = secondByte;
        this.amountFieldXBytes = amountFieldX;
        this.amountFieldXStr = StringUtils.formatAmountField(amountFieldX);
        this.secondAmountFieldYBytes = secondAmountFieldY;
        this.secondAmountFieldYStr = StringUtils.formatAmountField(secondAmountFieldYBytes);

        this.rule = RuleCalculator.getRule(this);
    }

    public byte getFirstByte() {
        return firstByte;
    }

    public byte getSecondByte() {
        return secondByte;
    }

    String getAmountFieldXStr() {
        return amountFieldXStr;
    }

    String getSecondAmountFieldYStr() {
        return secondAmountFieldYStr;
    }

    void setRule(Rule rule) {
        this.rule = rule;
    }

    public static final class Builder {
        private byte firstByte;
        private byte secondByte;
        private byte[] amountFieldXBytes;
        private byte[] secondAmountFieldYBytes;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder withFirstByte(byte firstByte) {
            this.firstByte = firstByte;
            return this;
        }

        public Builder withSecondByte(byte secondByte) {
            this.secondByte = secondByte;
            return this;
        }

        public Builder withAmountFieldXBytes(byte[] amountFieldXBytes) {
            this.amountFieldXBytes = amountFieldXBytes;
            return this;
        }

        public Builder withSecondAmountFieldYBytes(byte[] secondAmountFieldYBytes) {
            this.secondAmountFieldYBytes = secondAmountFieldYBytes;
            return this;
        }

        public CardVerificationRule build() {
            return new CardVerificationRule(firstByte, secondByte, amountFieldXBytes, secondAmountFieldYBytes);
        }
    }
}
