package de.dhbwkarlsruhe.modellbahn.schemes;

import lombok.Getter;

/**
 * These are the commands that can be sent to the CAN-Bus
 * They are 8 bits long
 * and the third field of the CAN-Frame
 */
@Getter
public enum CommandScheme {
    SYSTEM_COMMAND("SystemCommand", 0x00),
    LOCOMOTIVE_DISCOVERY("LocomotiveDiscovery", 0x02),
    MFX_BIND("MFXBind", 0x04),
    MFX_VERIFY("MFXVerify", 0x06),
    LOCOMOTIVE_SPEED("LocomotiveSpeed", 0x08),
    LOCOMOTIVE_DIRECTION("LocomotiveDirection", 0x0A),
    LOCOMOTIVE_FUNCTION("LocomotiveFunction", 0x0C),
    READ_CONFIG("ReadConfig", 0x0E),
    WRITE_CONFIG("WriteConfig", 0x10),
    SWITCHING_ACCESSORIES("SwitchingAccessories", 0x16),
    ACCESSORIES_CONFIG("AccessoriesConfig", 0x18),
    S_88_POLLING("S88Polling", 0x20),
    S_88_EVENT("S88Event", 0x22),
    SX_1_EVENT("SX1Event", 0x24),
    PARTICIPANT_PING("ParticipantPing", 0x30),
    UPDATE_OFFER("UpdateOffer", 0x32),
    READ_CONFIG_DATA("ReadConfigData", 0x34),
    BOOTLOADER_CAN_BOUND("BootloaderCANBound", 0x36),
    BOOTLOADER_RAIL_BOUND("BootloaderRailBound", 0x38),
    SERVICE_STATUS_DATA_CONFIGURATION("ServiceStatusDataConfiguration", 0x3A),
    REQUEST_CONFIG_DATA("RequestConfigData", 0x40),
    CONFIG_DATA_STREAM("ConfigDataStream", 0x42),
    DATA_STREAM_60128("DataStream60128", 0x44),
    UNKNOWN_COMMAND("UnknownCommand", -1);

    private final String command;
    private final int commandValue;

    /**
     * used to map command to hex-Value
     * @param command Command name
     * @param commandValue "Wert in CAN-ID in Dokumentation S.11"
     */
    CommandScheme(String command, int commandValue) {
        this.command = command;
        this.commandValue = commandValue;

    }

    public static CommandScheme fromCommand(String command) {
        for(CommandScheme scheme : CommandScheme.values()) {
            if (scheme.getCommand().equals(command)) return scheme;
        }
        return UNKNOWN_COMMAND;
    }

    public static CommandScheme fromCommandValue(int commandValue) {
        commandValue &= 0xFE; // last bit is response bit should be set to 0 to get the command value
        for(CommandScheme scheme : CommandScheme.values()) {
            if (scheme.getCommandValue() == commandValue) return scheme;
        }
        return UNKNOWN_COMMAND;
    }
}
