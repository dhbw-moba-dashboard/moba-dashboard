package de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.Commands;

import de.dhbwkarlsruhe.modellbahn.BitUtilities;
import de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.Payload;

public abstract  class Mfx implements Payload {
    protected int getUID(byte []data){
        if (data.length <4) throw new IllegalArgumentException("Data is too short");
        return BitUtilities.transformBitSequenceToInt(data,0,0,3,7);
    }
    protected int getSID(byte []data){
        if (data.length <6) throw new IllegalArgumentException("Data is too short");
        return BitUtilities.transformBitSequenceToInt(data,4,0,5,7);
    }
    public abstract byte[] toByteArray();
    public abstract Payload fromByteArray(byte[] data);
}
