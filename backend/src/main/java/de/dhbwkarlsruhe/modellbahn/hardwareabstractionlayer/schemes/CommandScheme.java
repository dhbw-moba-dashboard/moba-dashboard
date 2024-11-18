package de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.schemes;

import lombok.Getter;

/**
 * These are the commands that can be sent to the CAN-Bus
 * They are 8 bits long
 * and the third field of the CAN-Frame
 */
@Getter
public enum CommandScheme {
    SYSTEM_COMMAND("SystemCommand", 0x00),
    LOCOMOTIVE_DISCOVERY("LocomotiveDiscovery", 0x01),
    MFX_BIND("MFXBind", 0x02),
    MFX_VERIFY("MFXVerify", 0x03),
    LOCOMOTIVE_SPEED("LocomotiveSpeed", 0x04),
    LOCOMOTIVE_DIRECTION("LocomotiveDirection", 0x05),
    LOCOMOTIVE_FUNCTION("LocomotiveFunction", 0x06),
    READ_CONFIG("ReadConfig", 0x07),
    WRITE_CONFIG("WriteConfig", 0x08),
    SWITCHING_ACCESSORIES("SwitchingAccessories", 0x0B),
    ACCESSORIES_CONFIG("AccessoriesConfig", 0x0C),
    S_88_POLLING("S88Polling", 0x10),
    S_88_EVENT("S88Event", 0x11),
    SX_1_EVENT("SX1Event", 0x12),
    PARTICIPANT_PING("ParticipantPing", 0x18),
    UPDATE_OFFER("UpdateOffer", 0x19),
    READ_CONFIG_DATA("ReadConfigData", 0x1A),
    BOOTLOADER_CAN_BOUND("BootloaderCANBound", 0x1B),
    BOOTLOADER_RAIL_BOUND("BootloaderRailBound", 0x1C),
    SERVICE_STATUS_DATA_CONFIGURATION("ServiceStatusDataConfiguration", 0x1D),
    REQUEST_CONFIG_DATA("RequestConfigData", 0x20),
    CONFIG_DATA_STREAM("ConfigDataStream", 0x21),
    DATA_STREAM_60128("DataStream60128", 0x22),
    UNKNOWN_COMMAND("UnknownCommand", -1);

    private final String command;
    private final int commandValue;

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
        for(CommandScheme scheme : CommandScheme.values()) {
            if (scheme.getCommandValue() == commandValue) return scheme;
        }
        return UNKNOWN_COMMAND;
    }
}
