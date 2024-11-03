package de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes;

import de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.Schemes.CommandScheme;
import de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.Commands.*;

import static de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.Schemes.CommandScheme.LocomotiveFunction;

//Todo: Implement PayloadFactory
public class PayloadFactory {
    public static Payload getPayload(byte[] data, CommandScheme scheme,int DLC) throws IllegalPayloadException {
        return switch (scheme) {
            case SystemCommand -> new SystemCommand(data,DLC);
            case LocomotiveDiscovery -> new LocomotiveDiscovery(data,DLC);
            case MFXBind -> new MFXBind(data,DLC);
            case MFXVerify -> new MFXVerify(data,DLC);
            case LocomotiveDirection -> new LocomotiveDirection(data,DLC);
            case LocomotiveSpeed -> new LocomotiveSpeed(data,DLC);
            case LocomotiveFunction -> new LocomotiveFunction(data,DLC);
            case ReadConfig -> new ReadConfig(data,DLC);
            case WriteConfig -> new WriteConfig(data,DLC);
            case SwitchingAccessories -> new SwitchingAccessories(data,DLC);
            case AccessoriesConfig -> new AccessoriesConfig(data,DLC);
            case S88Polling -> new S88Polling(data,DLC);
            case S88Event -> new S88Event(data,DLC);
            case SX1Event -> new SX1Event(data,DLC);
            case ParticipantPing -> new ParticipantPing(data,DLC);
            case UpdateOffer -> new UpdateOffer(data,DLC);
            case ReadConfigData -> new ReadConfigData(data,DLC);
            case BootloaderCANBound -> new BootloaderCANBound(data,DLC);
            case BootloaderRailBound -> new BootloaderRailBound(data,DLC);
            case ServiceStatusDataConfiguration -> new ServiceStatusDataConfiguration(data,DLC);
            case RequestConfigData -> new RequestConfigData(data,DLC);
            case ConfigDataStream -> new ConfigDataStream(data,DLC);
            case DataStream60128 -> new DataStream60128(data,DLC);
            default -> throw new IllegalArgumentException("Unknown CommandScheme");
        };
    }

    public class IllegalPayloadException extends Exception {
        public IllegalPayloadException(String message) {
            super(message);
        }
    }
}
