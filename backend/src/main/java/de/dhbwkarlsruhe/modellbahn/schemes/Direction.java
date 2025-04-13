package de.dhbwkarlsruhe.modellbahn.schemes;


/**
 * direction of a loc <br>
 * the CS3 answers with FORWARD or BACKWARD
 */
public enum Direction
{

    SAME,
    FORWARD,
    BACKWARD,
    SWITCH,
    /**
     * used to generate frames that request the current direction
     */
    REQUEST
}