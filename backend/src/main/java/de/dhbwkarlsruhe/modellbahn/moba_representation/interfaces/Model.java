package de.dhbwkarlsruhe.modellbahn.moba_representation.interfaces;

/**
 * object representation of the data in a CAN-Frame
 */
public interface Model {
    /**
     * @return serialization of the Model
     */
    byte[] toByteArray();

    /**
     * @return the dlc based on the values of the object
     */
    int getDLC();

    boolean isValidAnswer();

}
