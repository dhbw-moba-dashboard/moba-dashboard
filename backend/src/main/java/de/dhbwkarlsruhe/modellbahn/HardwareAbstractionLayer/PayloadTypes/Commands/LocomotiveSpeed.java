package de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.Commands;

import com.google.gson.Gson;
import de.dhbwkarlsruhe.modellbahn.BitUtilities;
import de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.Payload;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class LocomotiveSpeed implements Payload{
    private final int Speed;
    private final int LocID;
    public LocomotiveSpeed(byte[] data ) {
        LocID = BitUtilities.transformBitSequenceToInt(data,0,0,4,7);
        Speed = BitUtilities.transformBitSequenceToInt(data,4,0,5,7);
    }
    @Override
    public byte[] toByteArray() {
        List<byte[]> src = new ArrayList<>();
        src.add(BitUtilities.intToByteArray(LocID));
        src.add(BitUtilities.intToByteArray(Speed));
        return BitUtilities.mergeByteArrays(src,8);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
