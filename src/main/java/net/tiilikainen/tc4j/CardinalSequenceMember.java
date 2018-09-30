package net.tiilikainen.tc4j;

import java.util.ResourceBundle;

public class CardinalSequenceMember {
    private static final ResourceBundle myriadsBundle = ResourceBundle.getBundle("myriads");
    private static final ResourceBundle miscBundle = ResourceBundle.getBundle("misc");
    private final ThreeDigitCardinal member;
    private boolean isStartingMember = false;
    private boolean isEndingMember = false;
    private boolean isNegative = false;
    private int myriad = 0;

    public String getMemberAsString() {
        StringBuilder output = new StringBuilder();
        if (isStartingMember && isNegative && !member.isZero())
            output.append(miscBundle.getString("negative")).append(" ");
        if (isEndingMember && !isStartingMember && !member.hasHundreds())
            output.append(miscBundle.getString("and")).append(" ");
        output.append(member.getCardinalRepresentation());
        if (myriad != 0 && !member.isZero())
            output.append(" ").append(myriadsBundle.getString(Integer.toString(myriad)));
        return output.toString();
    }

    public CardinalSequenceMember(String numberAsString) {
        if (numberAsString.startsWith("-")) {
            this.member = new ThreeDigitCardinal(numberAsString.substring(1));
            if (!member.isZero())
                isNegative = true;
        } else
            this.member = new ThreeDigitCardinal(numberAsString);
    }

    public void setStartingMember(boolean startingMember) {
        this.isStartingMember = startingMember;
    }

    public void setEndingMember(boolean endingMember) {
        this.isEndingMember = endingMember;
    }

    public void setNegative(boolean negative) {
        this.isNegative = negative;
    }

    public void setMyriad(int myriad) {
        this.myriad = myriad;
    }

    public static boolean isNotStartingZeroMember(CardinalSequenceMember cardinalSequenceMember) {
        return !(cardinalSequenceMember.member.isZero() && !cardinalSequenceMember.isStartingMember);
    }
}
