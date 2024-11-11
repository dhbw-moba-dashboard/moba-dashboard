package de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes;

import de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.Schemes.CommandScheme;
import de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.Commands.*;
import com.google.gson.*;

//Todo: Implement PayloadFactory
public class PayloadFactory {
    public static Payload createPayloadFromBytes(byte[] data, CommandScheme scheme, int DLC) throws IllegalPayloadException {
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
    public static Payload createPayloadFromJson(String json, CommandScheme scheme) throws IllegalPayloadException {
        Gson gson = new Gson();

        return switch (scheme) {
            case SystemCommand -> gson.fromJson(json, SystemCommand.class);
            case LocomotiveDiscovery -> gson.fromJson(json, LocomotiveDiscovery.class);
            case MFXBind -> gson.fromJson(json, MFXBind.class);
            case MFXVerify ->gson.fromJson(json, MFXVerify.class);
            case S88Event -> gson.fromJson(json, S88Event.class);
            case LocomotiveDirection -> gson.fromJson(json, LocomotiveDirection.class);
            case LocomotiveSpeed -> gson.fromJson(json, LocomotiveSpeed.class);
            case LocomotiveFunction -> gson.fromJson(json, LocomotiveFunction.class);
            case ReadConfig -> gson.fromJson(json, ReadConfig.class);
            case WriteConfig -> gson.fromJson(json, WriteConfig.class);
            case SwitchingAccessories -> gson.fromJson(json, SwitchingAccessories.class);
            case AccessoriesConfig -> gson.fromJson(json, AccessoriesConfig.class);
            case S88Polling -> gson.fromJson(json, S88Polling.class);
            case SX1Event -> gson.fromJson(json, SX1Event.class);
            case ParticipantPing -> gson.fromJson(json, ParticipantPing.class);
            case UpdateOffer -> gson.fromJson(json, UpdateOffer.class);
            case ReadConfigData -> gson.fromJson(json, ReadConfigData.class);
            case BootloaderCANBound -> gson.fromJson(json, BootloaderCANBound.class);
            case BootloaderRailBound -> gson.fromJson(json, BootloaderRailBound.class);
            case ServiceStatusDataConfiguration -> gson.fromJson(json, ServiceStatusDataConfiguration.class);
            case RequestConfigData -> gson.fromJson(json, RequestConfigData.class);
            case ConfigDataStream -> gson.fromJson(json, ConfigDataStream.class);
            case DataStream60128 -> gson.fromJson(json, DataStream60128.class);

            default -> throw new IllegalArgumentException("Unknown CommandScheme");
        };
    }

    public class IllegalPayloadException extends Exception {
        public IllegalPayloadException(String message) {
            super(message);
        }
    }
}
