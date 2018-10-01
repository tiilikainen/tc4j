package net.tiilikainen.tc4j;

import java.util.ResourceBundle;

/**
 * This class stores and then renders additional information for a three digit cardinal number that places it as a
 * subsequence into the context of a longer sequence.
 *
 * In other words, if the number being cardinalized is 123456789, ThreeDigitCardinal only knows that 456 renders into
 * "four hundred and fifty six". The containing CardinalSequenceMember object knows that it should actually be "four
 * hundred and fifty six thousand".
 */
class CardinalSequenceMember
{
    private static final ResourceBundle     myriadsBundle    = ResourceBundle.getBundle( "myriads" );
    private static final ResourceBundle     miscBundle       = ResourceBundle.getBundle( "misc" );
    private final        ThreeDigitCardinal member;
    private              boolean            isStartingMember = false;
    private              boolean            isEndingMember   = false;
    private              boolean            isNegative       = false;
    private              int                myriad           = 0;

    /**
     * Render the properly-contextualized string representation of this member in sequence.
     *
     * @return the aforesaid string representation
     */
    String getMemberAsString()
    {
        StringBuilder output = new StringBuilder();

        // The "not zero" test handles the degenerative case "-0", which is a valid Long value but signed zero isn't
        // relevant to applied mathematics... at least I don't think so.
        if ( isNegative && !member.isZero() )
        {
            output.append( miscBundle.getString( "negative" ) ).append( " " );
        }

        // If this is the ending member but not the starting member and the member has a value of less than 100, then
        // there will be an "and" missing. I call this the "1001" case, since without this logic, "1001" would be
        // rendered "One thousand one" and not the (correct) "One thousand and one".
        if ( isEndingMember && !isStartingMember && !member.hasHundreds() )
        {
            output.append( miscBundle.getString( "and" ) ).append( " " );
        }

        // Render the cardinal representation of the actual number here.
        output.append( member.getCardinalRepresentation() );

        // Add the myriad if it's non-zero and if the member isn't zero. This prevents "1000001" from rendering as
        // "One million zero thousand and one".
        if ( myriad != 0 && !member.isZero() )
        {
            output.append( " " ).append( myriadsBundle.getString( Integer.toString( myriad ) ) );
        }

        return output.toString();
    }

    CardinalSequenceMember( String numberAsString )
    {
        this.member = new ThreeDigitCardinal( numberAsString );
    }

    void setStartingMember( boolean startingMember )
    {
        this.isStartingMember = startingMember;
    }

    void setEndingMember( boolean endingMember )
    {
        this.isEndingMember = endingMember;
    }

    void setNegative( boolean negative )
    {
        this.isNegative = negative;
    }

    void setMyriad( int myriad )
    {
        this.myriad = myriad;
    }

    // For the nifty lambda in LongCardinalNumber, allowing it to ignore the degenerate case of a zero-value member
    // in the middle of a sequence.
    static boolean isNotStartingZeroMember( CardinalSequenceMember cardinalSequenceMember )
    {
        return !( cardinalSequenceMember.member.isZero() && !cardinalSequenceMember.isStartingMember );
    }
}
