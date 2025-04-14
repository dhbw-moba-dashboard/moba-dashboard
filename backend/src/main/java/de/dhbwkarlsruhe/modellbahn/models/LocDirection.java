package de.dhbwkarlsruhe.modellbahn.models;

import de.dhbwkarlsruhe.modellbahn.BitUtilities;
import de.dhbwkarlsruhe.modellbahn.schemes.Direction;
import de.dhbwkarlsruhe.modellbahn.schemes.LocValueScheme;

import java.util.List;

public record LocDirection(int locID, Direction direction) implements SimpleLocValue
{
    private static final LocValueScheme type = LocValueScheme.DIRECTION;

    public static LocDirection createLocDirection(byte[] data)
    {
        int id = BitUtilities.transformBitSequenceToInt(data, 0, 0, 3, 7);
        int dir = -1;
        if (data.length == 5)
        {
            dir = BitUtilities.transformBitSequenceToInt(data, 4, 0, 4, 7);

        }
        Direction direction;
        switch (dir)
        {
            case 0 -> direction = Direction.SAME;
            case 1 -> direction = Direction.FORWARD;
            case 2 -> direction = Direction.BACKWARD;
            case 3 -> direction = Direction.SWITCH;
            default -> direction = Direction.REQUEST;
        }
        return new LocDirection(id, direction);
    }

    @Override
    public byte[] toByteArray()
    {
        int directionByte = (direction == Direction.REQUEST) ? 0 : this.direction.ordinal();
        List<byte[]> data = List.of(
                BitUtilities.intToByteArray(locID, 4),
                BitUtilities.intToByteArray(directionByte, 1),
                BitUtilities.intToByteArray(0, 3) // padding bytes
        );
        return BitUtilities.mergeByteArrays(data);
    }

    @Override
    public int getDLC()
    {
        if (direction == Direction.REQUEST)
        {
            return 4;
        }
        return 5;
    }

    @Override
    public int getLoc()
    {
        return locID;
    }

    @Override
    public int getValue()
    {
        return direction.ordinal();
    }

    @Override
    public LocValueScheme getType()
    {
        return type;
    }

    @Override
    public boolean isValidAnswer()
    {
        return direction == Direction.FORWARD || direction == Direction.BACKWARD;
    }

}


