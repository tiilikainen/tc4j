package net.tiilikainen.tc4j;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CardinalizerTest
{
    private Cardinalizer cardinalizer;

    @Before
    public void init()
    {
        cardinalizer = CardinalizerFactory.getCardinalizer();
    }

    @Test
    public void test_getCardinalNumber_when0_returnZero()
    {
        Assert.assertEquals( cardinalizer.cardinalize( 0L ), "Zero" );
    }
}
