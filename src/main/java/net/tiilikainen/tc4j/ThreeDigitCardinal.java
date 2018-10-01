package net.tiilikainen.tc4j;

import java.util.LinkedList;
import java.util.ResourceBundle;

/**
 * This class will render a three digit integer in String format into its cardinal equivalent, i.e. "255" becomes
 * "two hundred and fifty five".
 */
class ThreeDigitCardinal
{
    private static final ResourceBundle cardinalsBundle = ResourceBundle.getBundle( "cardinals" );
    private static final ResourceBundle miscBundle      = ResourceBundle.getBundle( "misc" );
    private final        boolean        isZero;
    private final        String         hundredsString;
    private final        String         remainderString;
    private final        boolean        hasHundreds;
    private final        boolean        hasRemainder;

    String getCardinalRepresentation()
    {
        return cardinalRepresentation;
    }

    private String cardinalRepresentation;

    ThreeDigitCardinal( String s )
    {
        if ( s.length() > 3 )
        {
            throw new IllegalArgumentException( "input cannot be more than 3 characters" );
        }
        // Zero-pad the string. Makes everything more predictable.
        String numericRepresentation = String.format( "%03d", Integer.parseInt( s ) );
        this.isZero = numericRepresentation.equals( "000" );
        this.hundredsString = numericRepresentation.substring( 0, 1 );
        int hundreds = Integer.parseInt( hundredsString );

        // This transformation strips off the leading zero off the remainder if there is one. The "cardinals" bundle
        // performs double duty for the hundreds digit, which of course is always one digit, and the remainder, which
        // can be one or two. I'm probably adhering to DRY not as closely as I would like in this project, but I do so
        // here.
        int remainder = Integer.parseInt( numericRepresentation.substring( 1 ) );
        this.remainderString = Integer.toString( remainder );

        this.hasHundreds = hundreds > 0;
        this.hasRemainder = remainder > 0;

        // TODO convert to lazy loading
        this.cardinalRepresentation = cardinalize();
    }

    // Performs the actual rendering.
    // TODO why did I use a list of Strings here but a StringBuilder in CardinalSequenceMember?
    private String cardinalize()
    {
        String outputString;

        LinkedList< String > outputAsList = new LinkedList<>();

        // If the input value is greater than 99...
        if ( hasHundreds )
        {
            outputAsList.add( cardinalsBundle.getString( hundredsString ) );
            outputAsList.add( miscBundle.getString( "hundred" ) );
        }

        // If input value % 100 > 0...
        if ( hasRemainder )
        {
            // And input value > 100, we need an "and".
            if ( hasHundreds )
            {
                outputAsList.add( miscBundle.getString( "and" ) );
            }

            outputAsList.add( cardinalsBundle.getString( remainderString ) );
        }

        if ( outputAsList.size() > 0 )
        {
            outputString = String.join( " ", outputAsList );
        }
        // If after all that we have nothing, then return zero.
        else
        {
            outputString = cardinalsBundle.getString( "0" );
        }

        return outputString;
    }

    boolean isZero()
    {
        return isZero;
    }

    boolean hasHundreds()
    {
        return hasHundreds;
    }
}
