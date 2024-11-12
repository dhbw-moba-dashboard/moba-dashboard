package de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.Commands;

import com.google.gson.GsonBuilder;
import de.dhbwkarlsruhe.modellbahn.BitUtilities;
import de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.Payload;

public abstract class Mfx extends Payload {
    /**
     * @param builder is used to generate a Json represenatation of the object
     * @param DLC     Data Length Code, Number of databytes in CAN-message (0-8)
     */
    public Mfx(GsonBuilder builder, int DLC) {
        super(builder, DLC);
    }

    protected int getUID(byte[] data) {
        if (data.length < 4) throw new IllegalArgumentException("Data is too short");
        return BitUtilities.transformBitSequenceToInt(data, 0, 0, 3, 7);
    }

    protected int getSID(byte[] data) {
        if (data.length < 6) throw new IllegalArgumentException("Data is too short");
        return BitUtilities.transformBitSequenceToInt(data, 4, 0, 5, 7);
    }

    public abstract byte[] toByteArray();

    public abstract Payload fromByteArray(byte[] data);
}
