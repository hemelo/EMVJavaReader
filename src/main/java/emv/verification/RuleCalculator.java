package emv.verification;

class RuleCalculator {

    // Global vars
    private static byte _fByte, _sByte;

    public static Rule getRule(CardVerificationRule cvr) {

        _fByte = cvr.getFirstByte();
        _sByte = cvr.getSecondByte();

        return failCVMProcessing() ? Rule.FAIL_PROCESSING
                : noCVMRequired() ? Rule.NO_CVM_REQUIRED
                : hasPlainTextPINVerificationPerformedByICC() ? Rule.PLAINTEXT_PIN_VERIFIED_BY_ICC
                : hasPlainTextPINVerificationPerformedByICCAndSignaturePaper() ? Rule.PLAINTEXT_PIN_VERIFIED_BY_ICC_AND_SIGNATURE_ON_PAPER
                : hasEncipheredPINVerifiedOnline() ? Rule.ENCIPHERED_PIN_VERIFIED_ONLINE
                : hasEncipheredPINVerificationPerformedByICC() ? Rule.ENCIPHERED_PIN_VERIFIED_BY_ICC
                : hasEncipheredPINVerificationPerformedByICCAndSignaturePaper() ? Rule.ENCIPHERED_PIN_VERIFIED_BY_ICC_AND_SIGNATURE_ON_PAPER
                : hasSignaturePaper() ? Rule.SIGNATURE_ON_PAPER
                : isReservedForIndividualPaymentSystems() ? Rule.RESERVED_FOR_USE_BY_THE_INDIVIDUAL_PAYMENT_SYSTEMS
                : Rule.RFU;
    }

    public static String getConditionCodeDescription(CardVerificationRule cvr) {
        return switch (cvr.getSecondByte()) {
            case 0x00 -> "Always";
            case 0x01 -> "If unattended cash";
            case 0x02 -> "If not unattended cash and not manual cash and not purchase with cashback";
            case 0x03 -> "If terminal supports the CVM";
            case 0x04 -> "If manual cash";
            case 0x05 -> "If purchase with cashback";
            case 0x06 -> "If transaction is in the application currency and is under " + cvr.getAmountFieldXStr() + " value";
            case 0x07 -> "If transaction is in the application currency and is over " + cvr.getAmountFieldXStr() + " value";
            case 0x08 -> "If transaction is in the application currency and is under " + cvr.getSecondAmountFieldYStr() + " value";
            case 0x09 -> "If transaction is in the application currency and is over " + cvr.getSecondAmountFieldYStr() + " value";
            default -> cvr.getSecondByte() <= 0x7F ? "RFU" : "Reserved for use by individual payment systems";
        };
    }

    private static boolean failCVMProcessing() {
    }

    private static boolean noCVMRequired() {
    }

    private static boolean hasPlainTextPINVerificationPerformedByICC() {
    }

    private static boolean hasPlainTextPINVerificationPerformedByICCAndSignaturePaper() {
    }

    private static boolean hasEncipheredPINVerifiedOnline() {
    }

    private static boolean hasEncipheredPINVerificationPerformedByICC() {
    }

    private static boolean hasEncipheredPINVerificationPerformedByICCAndSignaturePaper() {
    }

    private static boolean hasSignaturePaper() {
    }

    private static boolean isReservedForIndividualPaymentSystems() {
    }


}
