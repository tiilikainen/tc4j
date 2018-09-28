package net.tiilikainen.tc4j;

public class CardinalizerFactory
{
    public static Cardinalizer getCardinalizer()
    {
        return new DefaultCardinalizerImpl();
    }

    private static class DefaultCardinalizerImpl implements Cardinalizer {}
}
