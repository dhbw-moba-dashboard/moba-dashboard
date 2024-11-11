package de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.Schemes;

import lombok.Getter;

@Getter
public enum ProtocolScheme {
    MFX(0),
    MM2_20kHz(33),
    MM2_40kHz(34),
    DCC_read_short(35),
    DCC_read_long(36),
    DCC_identify(37),
    SX1_read(38),
    SX1_identify(39),
    unknown(-1),
    MFX_main(64);
    private final int protocolValue;
    ProtocolScheme(int protocolValue) {
        this.protocolValue = protocolValue;
    }
    public static ProtocolScheme fromProtocolValue(int protocolValue) {
        if (protocolValue > 0 && protocolValue < 33)
            return MFX;
        if (protocolValue >= 64 && protocolValue <= 96)
            return MFX_main;
        for (ProtocolScheme scheme: ProtocolScheme.values()) {
            if (scheme.getProtocolValue() == protocolValue) {
                return scheme;
            }
        }
        return unknown;
    }

}
