package de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.Commands;

import com.google.gson.GsonBuilder;
import de.dhbwkarlsruhe.modellbahn.BitUtilities;
import de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.Payload;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class LocomotiveSpeed extends Payload {
    private final int speed;
    private final int LocID;
    public LocomotiveSpeed(byte[] data, int DLC, GsonBuilder builder) {
        super(builder,DLC);
        LocID = BitUtilities.transformBitSequenceToInt(data,0,0,4,7);
        speed = BitUtilities.transformBitSequenceToInt(data,4,0,5,7);
    }
    @Override
    public byte[] toByteArray() {
        List<byte[]> src = new ArrayList<>();
        src.add(BitUtilities.intToByteArray(LocID));
        src.add(BitUtilities.intToByteArray(speed));
        return BitUtilities.mergeByteArrays(src,8);
    }
}
