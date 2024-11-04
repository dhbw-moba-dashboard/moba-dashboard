package de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes;

import de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.Schemes.CommandScheme;
import de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.Commands.*;
import com.google.gson.*;

import static de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.Schemes.CommandScheme.LocomotiveFunction;

//Todo: Implement PayloadFactory
public class PayloadFactory {
    public static Payload getPayload(byte[] data, CommandScheme scheme,int DLC) throws IllegalPayloadException {
        GsonBuilder builder = new GsonBuilder();
        return switch (scheme) {
            case SystemCommand -> new SystemCommand(data,DLC,builder);
            case LocomotiveDiscovery -> new LocomotiveDiscovery(data,DLC,builder);
            case MFXBind -> new MFXBind(data,DLC,builder);
            case MFXVerify -> new MFXVerify(data,DLC,builder);
            case S88Event -> new S88Event(data,DLC,builder);
            case LocomotiveDirection -> new LocomotiveDirection(data,DLC,builder);
            case LocomotiveSpeed -> new LocomotiveSpeed(data,DLC,builder);
            case LocomotiveFunction -> new LocomotiveFunction(data,DLC,builder);
            case ReadConfig -> new ReadConfig(data,DLC,builder);
            case WriteConfig -> new WriteConfig(data,DLC,builder);
            case SwitchingAccessories -> new SwitchingAccessories(data,DLC,builder);
            case AccessoriesConfig -> new AccessoriesConfig(data,DLC,builder);
            case S88Polling -> new S88Polling(data,DLC,builder);
            case SX1Event -> new SX1Event(data,DLC,builder);
            case ParticipantPing -> new ParticipantPing(data,DLC,builder);
            case UpdateOffer -> new UpdateOffer(data,DLC,builder);
            case ReadConfigData -> new ReadConfigData(data,DLC,builder);
            case BootloaderCANBound -> new BootloaderCANBound(data,DLC,builder);
            case BootloaderRailBound -> new BootloaderRailBound(data,DLC,builder);
            case ServiceStatusDataConfiguration -> new ServiceStatusDataConfiguration(data,DLC,builder);
            case RequestConfigData -> new RequestConfigData(data,DLC,builder);
            case ConfigDataStream -> new ConfigDataStream(data,DLC,builder);
            case DataStream60128 -> new DataStream60128(data,DLC,builder);

            default -> throw new IllegalArgumentException("Unknown CommandScheme");
        };
    }

    public class IllegalPayloadException extends Exception {
        public IllegalPayloadException(String message) {
            super(message);
        }
    }
}
