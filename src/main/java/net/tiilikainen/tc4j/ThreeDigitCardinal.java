package net.tiilikainen.tc4j;

import java.util.LinkedList;
import java.util.ResourceBundle;

public class ThreeDigitCardinal {
    static final ResourceBundle cardinalsBundle = ResourceBundle.getBundle("cardinals");
    static final ResourceBundle miscBundle = ResourceBundle.getBundle("misc");
    private final boolean isZero;
    private final String hundredsString;
    private final String remainderString;
    private final int hundreds;
    private final int remainder;
    private final boolean hasHundreds;
    private final boolean hasRemainder;

    public String getCardinalRepresentation() {
        return cardinalRepresentation;
    }

    private String cardinalRepresentation;

    public ThreeDigitCardinal(String s) {
        if(s.length() > 3)
            throw new IllegalArgumentException("input cannot be more than 3 characters");
        String numericRepresentation = String.format("%03d", Integer.parseInt(s));
        this.isZero = numericRepresentation.equals("000");
        this.hundredsString = numericRepresentation.substring(0, 1);
        this.hundreds = Integer.parseInt(hundredsString);

        // This transformation strips off the leading zero if there is one.
        this.remainder = Integer.parseInt(numericRepresentation.substring(1));
        this.remainderString = Integer.toString(remainder);
        this.hasHundreds = hundreds > 0;
        this.hasRemainder = remainder > 0;
        this.cardinalRepresentation = cardinalize();
    }

    private String cardinalize() {
        String outputString;

        LinkedList<String> outputAsList = new LinkedList<>();

        if (hasHundreds) {
            outputAsList.add(cardinalsBundle.getString(hundredsString));
            outputAsList.add(miscBundle.getString("hundred"));
        }

        if (hasRemainder) {
            if(hasHundreds)
                outputAsList.add(miscBundle.getString("and"));

            outputAsList.add(cardinalsBundle.getString(remainderString));
        }

        if (outputAsList.size() > 0)
            outputString = String.join(" ", outputAsList);
        else
            outputString = cardinalsBundle.getString("0");

        return outputString;
    }

    public boolean isZero() {
        return isZero;
    }

    public boolean hasHundreds() {
        return hasHundreds;
    }
}
