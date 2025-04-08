package de.dhbwkarlsruhe.modellbahn.Models;

import de.dhbwkarlsruhe.modellbahn.BitUtilities;

import java.util.ArrayList;
import java.util.List;

public record LocSpeed(int locID, int speed) implements SimpleLocValue
{
    public static LocSpeed createLocSpeed(byte[] data)
    {

        int id = BitUtilities.transformBitSequenceToInt(data, 0, 0, 3, 7);
        int speed = -1;
        if (data.length > 4) {
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
        src.add(BitUtilities.intToByteArray(0,2));//2 padding bytes
        return BitUtilities.mergeByteArrays(src);
        //speed to zero return new byte[]{0x00,0x08,0x57,0x38,0x06,0x00,0x00,0x40,0x0d,0x00,0x00,0x00,0x00};
        //return new byte[]{0x00,0x08,0x57,0x38,0x06,0x00,0x00,0x40,0x0d,0x01,(byte) 0xf4,0x00,0x00};

    }

    @Override
    public int getDLC()
    {
        if (speed <0){
            return 4;
        }
        else {
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
}