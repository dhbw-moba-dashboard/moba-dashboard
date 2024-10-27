package de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes;

/**
 * This refers to the last 8 Bytes of a CANMessage, which contain the
 */
//Todo: check for more methods to implement
public interface Payload {
    byte []toByteArray();
    Payload fromByteArray(byte[] data);
}
