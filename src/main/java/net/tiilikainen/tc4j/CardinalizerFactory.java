package net.tiilikainen.tc4j;

import java.util.*;

public class CardinalizerFactory {
    public static Cardinalizer getCardinalizer() {
        return new DefaultCardinalizerImpl();
    }

    private static class DefaultCardinalizerImpl implements Cardinalizer {
        static final ResourceBundle cardinalsBundle = ResourceBundle.getBundle("cardinals");
        static final ResourceBundle myriadsBundle = ResourceBundle.getBundle("myriads");
        static final ResourceBundle miscBundle = ResourceBundle.getBundle("misc");

        @Override
        public String cardinalize(long l) {

//            return toSentenceCase(String.join(" ", stringList));
            return null;
        }

        private static String toSentenceCase(String input) {
            StringBuilder output = new StringBuilder(input);
            output.replace(0, 1, Character.toString(Character.toTitleCase(input.charAt(0))));
            return output.toString();
        }
    }
}
