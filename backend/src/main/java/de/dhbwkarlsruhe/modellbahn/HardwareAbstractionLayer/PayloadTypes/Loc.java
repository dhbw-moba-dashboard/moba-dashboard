package de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes;

public class Loc implements Payload{
    @Override
    public byte[] toByteArray() {
        return new byte[0];
    }

    @Override
    public Payload fromByteArray(byte[] data) {
        return null;
    }
}
