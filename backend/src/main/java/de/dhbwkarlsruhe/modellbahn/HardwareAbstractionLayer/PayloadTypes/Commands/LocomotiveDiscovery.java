package de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.Commands;

import com.google.gson.GsonBuilder;
import de.dhbwkarlsruhe.modellbahn.BitUtilities;
import de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.Payload;
import de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.Schemes.ProtocolScheme;
import lombok.Getter;

@Getter
public class LocomotiveDiscovery extends Payload {
    private final int locID;
    private final int mfx_range;
    private final ProtocolScheme protocol;
    private int askRatio = -1;

    public LocomotiveDiscovery(byte[] data, int DLC, GsonBuilder builder) {
        super(builder, DLC);
        if (DLC == 0) {
            locID = 0;
            mfx_range = -1;
            protocol = ProtocolScheme.unknown;
        } else if (DLC == 1) {
            locID = 0;
            int protocolNumber = BitUtilities.transformBitSequenceToInt(data, 0, 0, 0, 7);
            this.protocol = ProtocolScheme.fromProtocolValue(protocolNumber);
            mfx_range = getMFX_range(protocolNumber);
        } else if (DLC == 5) {
            locID = BitUtilities.transformBitSequenceToInt(data, 0, 0, 3, 7);
            int protocolNumber = BitUtilities.transformBitSequenceToInt(data, 4, 0, 4, 7);
            this.protocol = ProtocolScheme.fromProtocolValue(protocolNumber);
            mfx_range = getMFX_range(protocolNumber);
        } else {
            locID = BitUtilities.transformBitSequenceToInt(data, 0, 0, 3, 7);
            mfx_range = -1;
            protocol = ProtocolScheme.unknown;
            askRatio = BitUtilities.transformBitSequenceToInt(data, 5, 0, 5, 7);
        }

    }

    @Override
    public byte[] toByteArray() {
        if (locID == 0 && protocol == ProtocolScheme.unknown) {

        }

        //TODO implement
        throw new IllegalArgumentException("not implemented");
    }

    private int getMFX_range(int protocolNumber) {
        if (protocolNumber <= 32) return protocolNumber;
        else if (protocolNumber >= 64 && protocolNumber <= 96) return protocolNumber - 64;
        else return -1;
    }
}
