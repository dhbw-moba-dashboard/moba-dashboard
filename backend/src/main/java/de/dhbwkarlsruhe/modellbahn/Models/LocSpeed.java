package de.dhbwkarlsruhe.modellbahn.Models;

import de.dhbwkarlsruhe.modellbahn.BitUtilities;

import java.util.ArrayList;
import java.util.List;

public record LocSpeed(int locID, int speed) implements Model
{
    public static LocSpeed createLocSpeed(byte[] data)
    {
        int id = BitUtilities.transformBitSequenceToInt(data, 0, 0, 3, 7);
        int speed = BitUtilities.transformBitSequenceToInt(data, 4, 0, 5, 7);
        return new LocSpeed(id, speed);

    }

    @Override
    public byte[] toByteArray()
    {

        List<byte[]> src = new ArrayList<>();
        src.add(BitUtilities.intToByteArray(locID, 4));
        src.add(BitUtilities.intToByteArray(speed, 2));
        //return BitUtilities.mergeByteArrays(src);
        //speed to zero return new byte[]{0x00,0x08,0x57,0x38,0x06,0x00,0x00,0x40,0x0d,0x00,0x00,0x00,0x00};
        //return new byte[]{0x00,0x08,0x57,0x38,0x06,0x00,0x00,0x40,0x0d,0x01,(byte) 0xf4,0x00,0x00};

        return new byte[]{0x00,0x08,0x57,0x38,0x06,0x00,0x00,0x40,0x0d,0x01,(byte)0xf4,0x00,0x00};
    }
}