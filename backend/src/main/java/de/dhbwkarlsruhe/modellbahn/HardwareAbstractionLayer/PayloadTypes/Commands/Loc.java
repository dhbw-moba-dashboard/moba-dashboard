package de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.Commands;

import de.dhbwkarlsruhe.modellbahn.BitUtilities;
import de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.Payload;

public abstract class Loc implements Payload {
    private int locID;
    protected int getLocID(byte [] data) throws IllegalArgumentException{
        if (data.length < 4) throw new IllegalArgumentException("Data length is too short");
        return BitUtilities.transformBitSequenceToInt(data, 0, 0, 3, 7);
    }

    public abstract byte[] toByteArray();

    public abstract Payload fromByteArray(byte[] data);
}
