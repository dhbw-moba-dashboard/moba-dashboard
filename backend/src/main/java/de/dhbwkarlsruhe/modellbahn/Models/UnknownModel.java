package de.dhbwkarlsruhe.modellbahn.Models;

public class UnknownModel implements Model
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
}
