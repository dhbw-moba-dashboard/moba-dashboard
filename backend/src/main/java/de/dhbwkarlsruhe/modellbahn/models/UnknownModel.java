package de.dhbwkarlsruhe.modellbahn.models;

import de.dhbwkarlsruhe.modellbahn.schemes.LocValueScheme;

public class UnknownModel implements SimpleLocValue
{
    public static UnknownModel createErrorModel()
    {
        return new UnknownModel();
    }

    @Override
    public byte[] toByteArray()
    {
        return new byte[0];
    }

    @Override
    public int getDLC()
    {
        return 0;
    }

    @Override
    public int getLoc()
    {
        return 0;
    }

    @Override
    public int getValue()
    {
        return 0;
    }

    @Override
    public LocValueScheme getType()
    {
        return null;
    }

    @Override
    public boolean isValidAnswer()
    {
        return false;
    }
}
