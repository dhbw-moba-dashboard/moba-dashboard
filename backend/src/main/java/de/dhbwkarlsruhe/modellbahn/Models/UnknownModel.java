package de.dhbwkarlsruhe.modellbahn.Models;

import de.dhbwkarlsruhe.modellbahn.schemes.LocValueScheme;

public class UnknownModel implements SimpleLocValue
{
    public static UnknownModel createErrorModel(byte[] data)
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
}
