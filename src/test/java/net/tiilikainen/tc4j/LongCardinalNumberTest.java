package net.tiilikainen.tc4j;

import org.junit.Assert;
import org.junit.Test;

public class LongCardinalNumberTest
{
    @Test
    public void test_LongCardinalNumber_0()
    {
        Assert.assertEquals( "Zero", new LongCardinalNumber( "0" ).asString() );
    }

    @Test
    public void test_LongCardinalNumber_13()
    {
        Assert.assertEquals( "Thirteen", new LongCardinalNumber( "13" ).asString() );
    }

    @Test
    public void test_LongCardinalNumber_85()
    {
        Assert.assertEquals( "Eighty five", new LongCardinalNumber( "85" ).asString() );
    }

    @Test
    public void test_LongCardinalNumber_5237()
    {
        Assert.assertEquals( "Five thousand two hundred and thirty seven",
                             new LongCardinalNumber( "5237" ).asString() );
    }

    @Test
    public void test_LongCardinalNumber_negative0()
    {
        Assert.assertEquals( "Zero", new LongCardinalNumber( "-0" ).asString() );
    }

    @Test
    public void test_LongCardinalNumber_999()
    {
        Assert.assertEquals( "Nine hundred and ninety nine", new LongCardinalNumber( "999" ).asString() );
    }

    @Test
    public void test_LongCardinalNumber_negative999()
    {
        Assert.assertEquals( "Negative nine hundred and ninety nine", new LongCardinalNumber( "-999" ).asString() );
    }

    @Test
    public void test_LongCardinalNumber_1000()
    {
        Assert.assertEquals( "One thousand", new LongCardinalNumber( "1000" ).asString() );
    }

    @Test
    public void test_LongCardinalNumber_1001()
    {
        Assert.assertEquals( "One thousand and one", new LongCardinalNumber( "1001" ).asString() );
    }

    @Test
    public void test_LongCardinalNumber_negative1001()
    {
        Assert.assertEquals( "Negative one thousand and one", new LongCardinalNumber( "-1001" ).asString() );
    }

    @Test
    public void test_LongCardinalNumber_999909()
    {
        Assert.assertEquals( "Nine hundred and ninety nine thousand and ninety nine",
                             new LongCardinalNumber( "999099" ).asString() );
    }

    @Test
    public void test_LongCardinalNumber_999999()
    {
        Assert.assertEquals( "Nine hundred and ninety nine thousand nine hundred and ninety nine",
                             new LongCardinalNumber( "999999" ).asString() );
    }

    @Test
    public void test_LongCardinalNumber_1000001001()
    {
        Assert.assertEquals( "One billion one thousand and one", new LongCardinalNumber( "1000001001" ).asString() );
    }

    @Test
    public void test_LongCardinalNumber_LongMIN_VALUE()
    {
        Assert.assertEquals( "Negative nine quintillion two hundred and twenty three quadrillion " +
                             "three hundred and seventy two trillion thirty six billion " +
                             "eight hundred and fifty four million seven hundred and seventy five thousand " +
                             "eight hundred and eight",
                             new LongCardinalNumber( Long.toString( Long.MIN_VALUE ) ).asString() );
    }

    @Test
    public void test_LongCardinalNumber_LongMAX_VALUE()
    {
        Assert.assertEquals( "Nine quintillion two hundred and twenty three quadrillion " +
                             "three hundred and seventy two trillion thirty six billion " +
                             "eight hundred and fifty four million seven hundred and seventy five thousand " +
                             "eight hundred and seven",
                             new LongCardinalNumber( Long.toString( Long.MAX_VALUE ) ).asString() );
    }

    @Test( expected = NumberFormatException.class )
    public void test_LongCardinalNumber_invalidInput()
    {
        new LongCardinalNumber( "asdf" ).asString();
    }

    @Test
    public void test_LongCardinalNumber_toString()
    {
        Assert.assertEquals( "LongCardinalNumber{asLong=0, asString='Zero'}", new LongCardinalNumber( 0L ).toString() );
    }
}
