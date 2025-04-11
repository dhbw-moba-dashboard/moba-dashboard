package de.dhbwkarlsruhe.modellbahn.models;

/**
 * This refers to the last 8 Bytes of a CANMessage, which contain the Databytes
 */
public interface Model
{
    byte[] toByteArray();

    int getDLC();
}
