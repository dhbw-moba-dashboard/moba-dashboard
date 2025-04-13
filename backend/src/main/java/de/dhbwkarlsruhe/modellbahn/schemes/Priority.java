package de.dhbwkarlsruhe.modellbahn.schemes;

/**
 * first byte of every CAN-Frame
 * currently there is no usage for any priority except command -> first byte always 0x00
 */
public enum Priority
{
    COMMAND,
    STOP,
    MELDUNG,
    ANHALTEN,

}
