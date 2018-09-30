package net.tiilikainen.tc4j;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class CardinalSequence {
    LinkedList<CardinalSequenceMember> sequenceList = new LinkedList<>();
    private final String asString;

    public CardinalSequence(String value) {
        String absoluteValue;
        int myriad = 0;
        boolean negative = false;
        if (value.startsWith("-")) {
            absoluteValue = value.substring(1);
            negative = true;
        } else
            absoluteValue = value;
        for (int i = absoluteValue.length(); i > 0; i -= 3) {
            CardinalSequenceMember member = new CardinalSequenceMember(absoluteValue.substring(i < 3 ? 0 : i - 3, i));
            member.setMyriad(myriad);
            sequenceList.addFirst(member);
            myriad++;
        }
        sequenceList.getFirst().setNegative(negative);
        sequenceList.getFirst().setStartingMember(true);
        sequenceList.getLast().setEndingMember(true);

        asString = toSentenceCase(sequenceList.stream()
                .filter(CardinalSequenceMember::isNotStartingZeroMember)
                .map(CardinalSequenceMember::getMemberAsString)
                .collect(Collectors.joining(" ")));
    }

    public String asString() {
        return asString;
    }

    private static String toSentenceCase(String input) {
        StringBuilder output = new StringBuilder(input);
        output.replace(0, 1, Character.toString(Character.toTitleCase(input.charAt(0))));
        return output.toString();
    }
}
