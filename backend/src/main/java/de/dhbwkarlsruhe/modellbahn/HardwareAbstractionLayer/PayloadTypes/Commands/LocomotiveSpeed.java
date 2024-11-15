package de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.Commands;

import com.google.gson.Gson;
import de.dhbwkarlsruhe.modellbahn.BitUtilities;
import de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.Payload;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class LocomotiveSpeed implements Payload {
    private final int speed;
    private final int locID;

    public LocomotiveSpeed(byte[] data) {
        locID = BitUtilities.transformBitSequenceToInt(data, 0, 0, 3, 7);
        speed = BitUtilities.transformBitSequenceToInt(data, 4, 0, 5, 7);
    }

    @Override
    public byte[] toByteArray() {
        List<byte[]> src = new ArrayList<>();
        src.add(BitUtilities.intToByteArray(locID, 4));
        src.add(BitUtilities.intToByteArray(speed, 2));
        return BitUtilities.mergeByteArrays(src);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocomotiveSpeed that = (LocomotiveSpeed) o;
        return speed == that.speed && locID == that.locID;
    }
}