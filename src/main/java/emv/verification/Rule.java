package emv.verification;

public enum Rule {
    FAIL_PROCESSING("Fail CVM processing"),
    NO_CVM_REQUIRED("No CVM required"),
    PLAINTEXT_PIN_VERIFIED_BY_ICC("Plaintext PIN verification performed by ICC"),
    PLAINTEXT_PIN_VERIFIED_BY_ICC_AND_SIGNATURE_ON_PAPER("Plaintext PIN verification performed by ICC and signature (paper)"),
    ENCIPHERED_PIN_VERIFIED_ONLINE("Enciphered PIN verified online"),
    ENCIPHERED_PIN_VERIFIED_BY_ICC("Enciphered PIN verification performed by ICC"),
    ENCIPHERED_PIN_VERIFIED_BY_ICC_AND_SIGNATURE_ON_PAPER("Enciphered PIN verification performed by ICC and signature (paper)"),
    SIGNATURE_ON_PAPER("Signature (paper)"),
    RESERVED_FOR_USE_BY_THE_INDIVIDUAL_PAYMENT_SYSTEMS("Reserved for use by individual payment systems"),
    RESERVED_FOR_USE_BY_THE_ISSUER("Reserved for use by the issuer"),
    NOT_AVAILABLE_FOR_USE("Value is not available for use"),
    RFU("Reserved for future use");

    private String description;

    private Rule(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
