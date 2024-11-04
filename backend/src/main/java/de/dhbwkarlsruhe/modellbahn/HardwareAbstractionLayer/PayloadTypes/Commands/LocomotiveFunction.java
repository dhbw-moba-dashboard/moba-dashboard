package de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.Commands;

import com.google.gson.GsonBuilder;
import de.dhbwkarlsruhe.modellbahn.BitUtilities;
import de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.Payload;
import lombok.Getter;

import java.util.List;

@Getter
public class LocomotiveFunction extends Payload {
    private final int function;
    private final int value;
    private final int LocID;
    private final int extraFunction;
    public LocomotiveFunction(byte[] data, int DLC, GsonBuilder builder) {
        super(builder, DLC);
        LocID = BitUtilities.transformBitSequenceToInt(data, 0, 0, 3, 7);
        function = BitUtilities.transformBitSequenceToInt(data, 4, 0, 4, 7);
        value = BitUtilities.transformBitSequenceToInt(data, 5, 0, 5, 7);
        extraFunction = BitUtilities.transformBitSequenceToInt(data, 6, 0, 7, 7);

    }
    @Override
    public byte[] toByteArray() {
        List<byte []> src = List.of(
            BitUtilities.intToByteArray(LocID),
            BitUtilities.intToByteArray(function),
            BitUtilities.intToByteArray(value),
            BitUtilities.intToByteArray(extraFunction)
        );
        return BitUtilities.mergeByteArrays(src, 8);
    }
}
