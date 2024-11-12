package de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * This refers to the last 8 Bytes of a CANMessage, which contain the Databytes
 */
//Todo: check for more methods to implement
public abstract class Payload {
    protected final int DLC;
    private final Gson gson;

    /**
     * @param builder is used to generate a Json represenatation of the object
     * @param DLC     Data Length Code, Number of databytes in CAN-message (0-8)
     */
    public Payload(GsonBuilder builder, int DLC) {
        this.DLC = DLC;
        gson = builder.create();
    }


    protected abstract byte[] toByteArray();

    /**
     * @return byte Array with fixed length of 8.
     */
    public byte[] getFixedByteArray() {
        byte[] srcArray = toByteArray();
        byte[] result = new byte[8];
        int maximumDatabytes = 8;
        System.arraycopy(srcArray, 0, result, 0, maximumDatabytes);
        return result;
    }

    @Override
    public String toString() {
        return gson.toJson(this);
    }
}
