package de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.Commands;

import com.google.gson.GsonBuilder;
import de.dhbwkarlsruhe.modellbahn.BitUtilities;
import de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.Payload;
import lombok.Getter;

import java.util.List;

@Getter
public class LocomotiveDirection extends Payload {
    public enum Direction {
        SAME,
        FORWARD,
        BACKWARD,
        SWITCH
    }
    private final Direction direction;
    private final int locID;
    public LocomotiveDirection(byte[] data, int DLC, GsonBuilder builder) {
        super(builder,DLC);
        locID = BitUtilities.transformBitSequenceToInt(data, 0, 0, 3, 7);
        int dir = BitUtilities.transformBitSequenceToInt(data, 4, 0, 4, 7);
        switch (dir){
            case 1 -> direction = Direction.FORWARD;
            case 2 -> direction = Direction.BACKWARD;
            case 3 -> direction = Direction.SWITCH;
            default -> direction = Direction.SAME;
        }
    }
    public byte[] toByteArray() {
        List<byte []> data = List.of(
                BitUtilities.intToByteArray(locID),
                BitUtilities.intToByteArray(direction.ordinal())
        );
        return BitUtilities.mergeByteArrays(data,DLC);
    }

}
