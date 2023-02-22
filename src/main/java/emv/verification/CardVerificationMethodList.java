package emv.verification;

import exception.SmartCardException;

import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CardVerificationMethodList {

    private LinkedList<CardVerificationRule> cvRules = new LinkedList<CardVerificationRule>();
    private byte[] amountField;
    private byte[] secondAmountField;

    public CardVerificationMethodList(byte[] data) {

        if (data.length < 8) {
            throw new IllegalArgumentException("Argument \"data\" must have length greater or equals 8 ");
        }

        ByteArrayInputStream stream  = new ByteArrayInputStream(data);

        amountField = new byte[4];
        secondAmountField = new byte[4];

        stream.read(amountField, 0, amountField.length);
        stream.read(secondAmountField, 0, secondAmountField.length);

        if(stream.available() % 2 != 0 ){
            throw new SmartCardException("CVM Rules is not a multiple of 2");
        }

        while(stream.available() > 0){
            byte[] rule = new byte[2];
            stream.read(rule, 0, rule.length);
            cvRules.add(
                    CardVerificationRule.Builder
                            .builder()
                            .withFirstByte(rule[0])
                            .withSecondByte(rule[1])
                            .withAmountFieldXBytes(amountField)
                            .withSecondAmountFieldYBytes(secondAmountField)
                            .build()
            );
        }
    }

    public List<CardVerificationRule> getRules() {
        return Collections.unmodifiableList(cvRules);
    }
}
