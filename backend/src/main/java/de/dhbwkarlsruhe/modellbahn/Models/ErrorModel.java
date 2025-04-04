package de.dhbwkarlsruhe.modellbahn.Models;

public class ErrorModel implements Model
{
    public static ErrorModel createErrorModel(byte[] data)
    {
        return new ErrorModel();
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
