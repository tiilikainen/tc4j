package net.tiilikainen.tc4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * TC4J - Tiili's Cardinalizer for Java
 *
 * This main class provides a simple command-line REPL loop-style interface to evaluate the functionality of the
 * classes in this package.
 */
public class CardinalizerMain
{
    public static void main( String... args )
    {
        System.out.println( "TC4J - Tiili's Cardinalizer For Java" );
        System.out.println( "Input a Long number value to see its English cardinal equivalent." );
        System.out.println( "Hit 'Enter' on an empty line to exit." );

        BufferedReader reader = null;

        try
        {
            reader = new BufferedReader( new InputStreamReader( System.in ) );

            while ( true )
            {
                System.out.println( "Input number, or <enter> to exit: " );
                String line = reader.readLine();

                // Exit if empty line encountered.
                if ( line.length() == 0 )
                {
                    break;
                }

                // Invoke entry class and get output.
                try
                {
                    LongCardinalNumber longCardinalNumber = new LongCardinalNumber( line );
                    System.out.println( longCardinalNumber.asLong() + ": " + longCardinalNumber.asString() );
                }
                catch( NumberFormatException e )
                {
                    System.out.println(e.getMessage());
                }
            }
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
        finally
        {
            if ( reader != null )
            {
                try
                {
                    reader.close();
                }
                catch ( IOException e )
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
