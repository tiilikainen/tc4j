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

    /**
     * Generates a cardinal number from a Long.
     *
     * @param value number to generate from
     */
    public LongCardinalNumber( Long value )
    {
        this( value.toString() );
    }

    /**
     * Generates a cardinal number from a String. The rendering is done at object creation time.
     *
     * TODO handle this lazily
     *
     * @param value string representation of number to generate from
     *
     * @throws NumberFormatException when a Long number cannot be parsed from the value
     */
    public LongCardinalNumber( String value ) throws NumberFormatException
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

        // This routine does its work by breaking the string value down into a sequence of three-character substrings,
        // since our system of naming numbers advances to the next term once per three orders of magnitude. I have
        // decided to refer to three orders of magnitude as a "myriad", the dictionary definition of which is "enormous
        // number". In this context I mean it to represent the magnitude of number represented by the next substring to
        // be processed. This variable indicates what floor(log1000(value)) of this substring should evaluate to.
        int myriad = 0;

        String absoluteValue;

        boolean negative = false;

        // Strip off and save the negative sign. This handles the case where the input is Long.MIN_VALUE since
        // multiplying the value by -1 will cause the output to be erroneous, since Long.MIN_VALUE * -1 would exceed
        // Long.MAX_VALUE if there were no size restriction.
        if ( value.startsWith( "-" ) )
        {
            absoluteValue = value.substring( 1 );
            negative = true;
        }
        else
        {
            absoluteValue = value;
        }

        // TODO investigate whether using an ArrayList and reversing it would be more performant.
        // (however, I think this makes the code a bit easier to read since it is processing the number in reverse)
        LinkedList< CardinalSequenceMember > sequenceList = new LinkedList<>();

        // Process the number three digits at a time in reverse order by myriad.
        for ( int i = absoluteValue.length(); i > 0; i -= 3 )
        {
            // Create a new three digit cardinal sequence for this substring.
            CardinalSequenceMember member = new CardinalSequenceMember(
                    absoluteValue.substring( i < 3 ? 0 : i - 3, i ) );

            // Set the myriad appropriately; 0 for the final three digits, 1 for thousands, 2 for millions, etc.
            member.setMyriad( myriad );

            // Add this sequence to the head of the list.
            sequenceList.addFirst( member );

            myriad++;
        }

        sequenceList.getFirst().setNegative( negative );
        sequenceList.getFirst().setStartingMember( true );
        sequenceList.getLast().setEndingMember( true );

        // This generates the string.
        // Step 1: Remove sequences that start with zero if they don't head the list. This prevents a number like
        //         1000001 from being rendered "One million zero thousand and one".
        // Step 2: Render the sequence number. It can't be rendered earlier in the process because the sequences
        //         aren't yet populated with all of the information they need to render correctly.
        // Step 3: Join string on space.
        asString = toSentenceCase( sequenceList.stream()
                                               .filter( CardinalSequenceMember::isNotStartingZeroMember )
                                               .map( CardinalSequenceMember::getMemberAsString )
                                               .collect( Collectors.joining( " " ) ) );
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

    public String asString()
    {
        return asString;
    }

    @Override
    public String toString()
    {
        return "LongCardinalNumber{asLong=" + asLong() + ", asString='" + asString() + "'}";
    }
}
