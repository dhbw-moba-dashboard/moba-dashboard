package de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.Commands;

import de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.Payload;

public class MFXVerify implements Payload {
    @Override
    public byte[] toByteArray() {
        return new byte[0];
    }

    @Override
    public Payload fromByteArray(byte[] data) {
        return null;
    }
}