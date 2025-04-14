package de.dhbwkarlsruhe.modellbahn.models;

import de.dhbwkarlsruhe.modellbahn.BitUtilities;
import de.dhbwkarlsruhe.modellbahn.schemes.LocValueScheme;

import java.util.ArrayList;
import java.util.List;

public record LocSpeed(int locID, int speed) implements SimpleLocValue
{
    private static final LocValueScheme type = LocValueScheme.SPEED;

    public static LocSpeed createLocSpeed(byte[] data)
    {
        int id = BitUtilities.transformBitSequenceToInt(data, 0, 0, 3, 7);
        int speed = -1;
        if (data.length == 6)
        {
            speed = BitUtilities.transformBitSequenceToInt(data, 4, 0, 5, 7);

        }

        return new LocSpeed(id, speed);

    }

    @Override
    public byte[] toByteArray()
    {

        List<byte[]> src = new ArrayList<>();
        src.add(BitUtilities.intToByteArray(locID, 4));
        int writtenSpeed = Math.max(speed, 0);
        src.add(BitUtilities.intToByteArray(writtenSpeed, 2));
        src.add(BitUtilities.intToByteArray(0, 2));//2 padding bytes
        return BitUtilities.mergeByteArrays(src);

    }

    @Override
    public int getDLC()
    {
        if (speed < 0)
        {
            return 4;
        }
        else
        {
            return 6;
        }
    }

    @Override
    public int getLoc()
    {
        return locID;
    }

    @Override
    public int getValue()
    {
        return speed;
    }

    @Override
    public LocValueScheme getType()
    {
        return type;
    }

    @Override
    public boolean isValidAnswer()
    {
        return speed >= 0;
    }
}