package de.dhbwkarlsruhe.modellbahn.moba_representation;


import de.dhbwkarlsruhe.modellbahn.moba_representation.interfaces.Model;

public class UnknownModel implements Model {
    public static UnknownModel createErrorModel() {
        return new UnknownModel();
    }

    @Override
    public byte[] toByteArray() {
        return new byte[0];
    }

    @Override
    public int getDLC() {
        return 0;
    }

    @Override
    public boolean isValidAnswer() {
        return false;
    }


}
