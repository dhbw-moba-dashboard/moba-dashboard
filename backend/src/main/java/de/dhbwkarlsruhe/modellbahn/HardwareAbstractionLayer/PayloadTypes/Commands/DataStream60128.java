package de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.Commands;

import com.google.gson.GsonBuilder;
import de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.Payload;

public class DataStream60128 extends Payload {
    /**
     * @param builder is used to generate a Json represenatation of the object
     * @param DLC     Data Length Code, Number of databytes in CAN-message (0-8)
     */
    public DataStream60128(GsonBuilder builder, int DLC) {
        super(builder, DLC);
    }

    @Override
    public byte[] toByteArray() {
        return new byte[0];
    }
}
