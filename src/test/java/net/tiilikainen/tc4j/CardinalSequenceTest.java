package net.tiilikainen.tc4j;

import org.junit.Assert;
import org.junit.Test;

public class CardinalSequenceTest {
    @Test
    public void test_CardinalSequence_0() {
        Assert.assertEquals("Zero", new CardinalSequence("0").asString());
    }

    @Test
    public void test_CardinalSequence_negative0() {
        Assert.assertEquals("Zero", new CardinalSequence("-0").asString());
    }

    @Test
    public void test_CardinalSequence_999() {
        Assert.assertEquals("Nine hundred and ninety nine", new CardinalSequence("999").asString());
    }

    @Test
    public void test_CardinalSequence_negative999() {
        Assert.assertEquals("Negative nine hundred and ninety nine", new CardinalSequence("-999").asString());
    }

    @Test
    public void test_CardinalSequence_1000() {
        Assert.assertEquals("One thousand", new CardinalSequence("1000").asString());
    }

    @Test
    public void test_CardinalSequence_1001() {
        Assert.assertEquals("One thousand and one", new CardinalSequence("1001").asString());
    }

    @Test
    public void test_CardinalSequence_negative1001() {
        Assert.assertEquals("Negative one thousand and one", new CardinalSequence("-1001").asString());
    }

    @Test
    public void test_CardinalSequence_999909() {
        Assert.assertEquals("Nine hundred and ninety nine thousand and ninety nine", new CardinalSequence("999099").asString());
    }

    @Test
    public void test_CardinalSequence_999999() {
        Assert.assertEquals("Nine hundred and ninety nine thousand nine hundred and ninety nine", new CardinalSequence("999999").asString());
    }

    @Test
    public void test_CardinalSequence_1000001001() {
        Assert.assertEquals("One billion one thousand and one", new CardinalSequence("1000001001").asString());
    }

    @Test
    public void test_CardinalSequence_LongMIN_VALUE() {
        Assert.assertEquals("Negative nine quintillion two hundred and twenty three quadrillion " +
                "three hundred and seventy two trillion thirty six billion " +
                "eight hundred and fifty four million seven hundred and seventy five thousand " +
                "eight hundred and eight", new CardinalSequence(Long.toString(Long.MIN_VALUE)).asString());
    }

    @Test
    public void test_CardinalSequence_LongMAX_VALUE() {
        Assert.assertEquals("Nine quintillion two hundred and twenty three quadrillion " +
                "three hundred and seventy two trillion thirty six billion " +
                "eight hundred and fifty four million seven hundred and seventy five thousand " +
                "eight hundred and seven", new CardinalSequence(Long.toString(Long.MAX_VALUE)).asString());
    }
}
