package de.dhbwkarlsruhe.modellbahn.Models;

import de.dhbwkarlsruhe.modellbahn.BitUtilities;
import de.dhbwkarlsruhe.modellbahn.schemes.Direction;

import java.util.List;

public record LocDirection(int locID, Direction direction) implements Model
{
    public static LocDirection createLocDirection(byte[] data)
    {
        int id = BitUtilities.transformBitSequenceToInt(data, 0, 0, 3, 8);
        int dir = BitUtilities.transformBitSequenceToInt(data, 4, 0, 4, 7);
        Direction direction;
        switch (dir)
        {
            case 1 -> direction = Direction.FORWARD;
            case 2 -> direction = Direction.BACKWARD;
            case 3 -> direction = Direction.SWITCH;
            default -> direction = Direction.SAME;
        }
        return new LocDirection(id, direction);
    }

    @Override
    public byte[] toByteArray()
    {
        List<byte[]> data = List.of(
                BitUtilities.intToByteArray(locID, 4),
                BitUtilities.intToByteArray(direction.ordinal(), 1)
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
        return 6;
    }

}


