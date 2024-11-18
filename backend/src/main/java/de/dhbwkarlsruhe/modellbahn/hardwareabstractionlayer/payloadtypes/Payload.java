package de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.payloadtypes;

/**
 * This refers to the last 8 Bytes of a CANMessage, which contain the Databytes
 */
public interface Payload {

    byte[] toByteArray();
}
