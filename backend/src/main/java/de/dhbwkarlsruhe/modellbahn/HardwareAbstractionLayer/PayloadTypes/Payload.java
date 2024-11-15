package de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * This refers to the last 8 Bytes of a CANMessage, which contain the Databytes
 */
//Todo: check for more methods to implement
public interface Payload {

    byte[] toByteArray();

//    /**
//     *
//     * @return byte Array with fixed length of 8.
//     */
//    public byte[] getFixedByteArray(){
//        byte[] srcArray = toByteArray();
//        byte [] result = new byte[8];
//        int maximumDatabytes = 8;
//        System.arraycopy(srcArray, 0, result, 0, maximumDatabytes);
//        return result;
//    }
}
