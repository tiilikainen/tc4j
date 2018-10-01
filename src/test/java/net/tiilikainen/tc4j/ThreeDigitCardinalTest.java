package net.tiilikainen.tc4j;

import org.junit.Assert;
import org.junit.Test;

public class ThreeDigitCardinalTest
{
    @Test
    public void test_getCardinalRepresentation_0()
    {
        Assert.assertEquals( "zero", new ThreeDigitCardinal( "0" ).getCardinalRepresentation() );
    }

    @Test
    public void test_getCardinalRepresentation_000()
    {
        Assert.assertEquals( "zero", new ThreeDigitCardinal( "000" ).getCardinalRepresentation() );
    }

    @Test
    public void test_getCardinalRepresentation_99()
    {
        Assert.assertEquals( "ninety nine", new ThreeDigitCardinal( "99" ).getCardinalRepresentation() );
    }

    @Test
    public void test_getCardinalRepresentation_099()
    {
        Assert.assertEquals( "ninety nine", new ThreeDigitCardinal( "099" ).getCardinalRepresentation() );
    }

    @Test
    public void test_getCardinalRepresentation_100()
    {
        Assert.assertEquals( "one hundred", new ThreeDigitCardinal( "100" ).getCardinalRepresentation() );
    }

    @Test
    public void test_getCardinalRepresentation_101()
    {
        Assert.assertEquals( "one hundred and one", new ThreeDigitCardinal( "101" ).getCardinalRepresentation() );
    }

    @Test
    public void test_getCardinalRepresentation_999()
    {
        Assert.assertEquals( "nine hundred and ninety nine",
                             new ThreeDigitCardinal( "999" ).getCardinalRepresentation() );
    }

    @Test( expected = IllegalArgumentException.class )
    public void test_ThreeDigitCardinal_9999_throwsException()
    {
        new ThreeDigitCardinal( "9999" );
    }
}
