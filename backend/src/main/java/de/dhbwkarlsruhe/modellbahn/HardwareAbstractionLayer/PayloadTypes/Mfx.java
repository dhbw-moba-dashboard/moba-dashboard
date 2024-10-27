package de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes;

public class Mfx implements Payload{
    @Override
    public byte[] toByteArray() {
        return new byte[0];
    }

    @Override
    public Payload fromByteArray(byte[] data) {
        return null;
    }
}
