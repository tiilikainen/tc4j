package net.tiilikainen.tc4j;

public class CardinalizerMain
{
    public static void main( String... args )
    {
        System.out.println( "args = [" + args + "]" );
        System.out.println( CardinalizerFactory.getCardinalizer().cardinalize(123456789L ) );
    }
}
