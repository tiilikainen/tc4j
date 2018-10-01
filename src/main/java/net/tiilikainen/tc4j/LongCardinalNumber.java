package net.tiilikainen.tc4j;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * This class defines an object that is a cardinal representation of a long integer value.
 */
public class LongCardinalNumber
{
    // For formatting Long.MIN_VALUE and Long.MAX_VALUE so that they don't wrap in a smaller terminal.
    private static NumberFormat validRangeFormatter = new DecimalFormat( "0.00E0" );

    private final long asLong;

    private final String asString;

    public LongCardinalNumber( Long value )
    {
        this( value.toString() );
    }

    public LongCardinalNumber( String value ) throws IllegalArgumentException
    {
        try
        {
            asLong = Long.parseLong( value );
        }
        catch ( NumberFormatException e )
        {
            throw new NumberFormatException(
                    "Invalid input: [" + value + "]. Must be a number between ~" + validRangeFormatter
                            .format( Long.MIN_VALUE ) + " and ~" + validRangeFormatter.format( Long.MAX_VALUE ) + "." );
        }

        String absoluteValue;
        int myriad = 0;
        boolean negative = false;
        if ( value.startsWith( "-" ) )
        {
            absoluteValue = value.substring( 1 );
            negative = true;
        }
        else
        {
            absoluteValue = value;
        }
        LinkedList< CardinalSequenceMember > sequenceList = new LinkedList<>();
        for ( int i = absoluteValue.length(); i > 0; i -= 3 )
        {
            CardinalSequenceMember member = new CardinalSequenceMember(
                    absoluteValue.substring( i < 3 ? 0 : i - 3, i ) );
            member.setMyriad( myriad );
            sequenceList.addFirst( member );
            myriad++;
        }
        sequenceList.getFirst().setNegative( negative );
        sequenceList.getFirst().setStartingMember( true );
        sequenceList.getLast().setEndingMember( true );

        asString = toSentenceCase( sequenceList.stream()
                                               .filter( CardinalSequenceMember::isNotStartingZeroMember )
                                               .map( CardinalSequenceMember::getMemberAsString )
                                               .collect( Collectors.joining( " " ) ) );
    }

    public String asString()
    {
        return asString;
    }

    private static String toSentenceCase( String input )
    {
        StringBuilder output = new StringBuilder( input );
        output.replace( 0, 1, Character.toString( Character.toTitleCase( input.charAt( 0 ) ) ) );
        return output.toString();
    }

    public long asLong()
    {
        return asLong;
    }

    @Override
    public String toString()
    {
        return "LongCardinalNumber{asLong=" + asLong() + ", asString='" + asString() + "'}";
    }
}
