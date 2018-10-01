package net.tiilikainen.tc4j;

import org.junit.Assert;
import org.junit.Test;

public class CardinalSequenceMemberTest
{
    @Test
    public void test_getMemberAsString_0()
    {
        CardinalSequenceMember member = new CardinalSequenceMember( "0" );
        Assert.assertEquals( "zero", member.getMemberAsString() );
    }

    @Test
    public void test_getMemberAsString_negative0()
    {
        CardinalSequenceMember member = new CardinalSequenceMember( "-0" );
        member.setStartingMember( true );
        Assert.assertEquals( "zero", member.getMemberAsString() );
    }

    @Test
    public void test_getMemberAsString_negative1()
    {
        CardinalSequenceMember member = new CardinalSequenceMember( "-1" );
        member.setStartingMember( true );
        Assert.assertEquals( "negative one", member.getMemberAsString() );
    }

    @Test
    public void test_getMemberAsString_negative1_notStartingMember()
    {
        CardinalSequenceMember member = new CardinalSequenceMember( "-1" );
        Assert.assertEquals( "one", member.getMemberAsString() );
    }

    @Test
    public void test_getMemberAsString_1000()
    {
        CardinalSequenceMember member = new CardinalSequenceMember( "1" );
        member.setMyriad( 1 );
        Assert.assertEquals( "one thousand", member.getMemberAsString() );
    }

    @Test
    public void test_getMemberAsString_0_withMyriad()
    {
        CardinalSequenceMember member = new CardinalSequenceMember( "0" );
        Assert.assertEquals( "zero", member.getMemberAsString() );
    }

    @Test
    public void test_getMemberAsString_1_withAnd()
    {
        CardinalSequenceMember member = new CardinalSequenceMember( "1" );
        member.setEndingMember( true );
        Assert.assertEquals( "and one", member.getMemberAsString() );
    }
}
