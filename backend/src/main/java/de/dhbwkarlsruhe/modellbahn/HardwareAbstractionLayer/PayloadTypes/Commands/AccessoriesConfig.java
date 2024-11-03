package de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.Commands;

import de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.Payload;

public class AccessoriesConfig implements Payload {
    AccessoriesConfig(byte[] data) {
    }
    @Override
    public byte[] toByteArray() {
        return new byte[0];
    }

    @Override
    public Payload fromByteArray(byte[] data) {
        return null;
    }
}
