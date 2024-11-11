package de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.Schemes;

import lombok.Getter;

/**
 *These are the commands that can be sent to the CAN-Bus
 * They are 8 bits long
 * and the third field of the CAN-Frame
  */
@Getter
public enum CommandScheme {
    SystemCommand("SystemCommand",0x00),
    LocomotiveDiscovery("LocomotiveDiscovery",0x01),
    MFXBind("MFXBind",0x02),
    MFXVerify("MFXVerify",0x03),
    LocomotiveSpeed("LocomotiveSpeed",0x04),
    LocomotiveDirection("LocomotiveDirection",0x05),
    LocomotiveFunction("LocomotiveFunction",0x06),
    ReadConfig("ReadConfig",0x07),
    WriteConfig("WriteConfig",0x08),
    SwitchingAccessories("SwitchingAccessories",0x0B),
    AccessoriesConfig("AccessoriesConfig",0x0C),
    S88Polling("S88Polling",0x10),
    S88Event("S88Event",0x11),
    SX1Event("SX1Event",0x12),
    ParticipantPing("ParticipantPing",0x18),
    UpdateOffer("UpdateOffer",0x19),
    ReadConfigData("ReadConfigData",0x1A),
    BootloaderCANBound("BootloaderCANBound",0x1B),
    BootloaderRailBound("BootloaderRailBound",0x1C),
    ServiceStatusDataConfiguration("ServiceStatusDataConfiguration",0x1D),
    RequestConfigData("RequestConfigData",0x20),
    ConfigDataStream("ConfigDataStream",0x21),
    DataStream60128("DataStream60128",0x22),
    UnknownCommand("UnknownCommand",-1);

    private final String command;
    private final int CommandValue;

    CommandScheme(String command, int commandValue) {
        this.command = command;
        CommandValue = commandValue;

    }

    public static CommandScheme fromCommand(String command) {
        for (CommandScheme scheme : CommandScheme.values()) {
            if (scheme.getCommand().equals(command)) return scheme;
        }
        return UnknownCommand;
    }
    public static CommandScheme fromCommandValue(int commandValue) {
        for (CommandScheme scheme : CommandScheme.values()) {
            if (scheme.getCommandValue() == commandValue) return scheme;
        }
        return UnknownCommand;
    }
}
