package de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes;

import de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.Schemes.CommandScheme;
import de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.Commands.*;
import com.google.gson.*;

//Todo: Implement PayloadFactory
public class PayloadFactory {
    public static Payload createPayloadFromBytes(byte[] data, CommandScheme scheme, int DLC) throws IllegalPayloadException {
        return switch (scheme) {
            case LocomotiveSpeed -> new LocomotiveSpeed(data);

            default -> throw new IllegalArgumentException("Unknown CommandScheme");
        };
    }
    public static Payload createPayloadFromJson(String json, CommandScheme scheme) throws IllegalPayloadException {
        Gson gson = new Gson();

        return switch (scheme) {
            case LocomotiveSpeed -> gson.fromJson(json, LocomotiveSpeed.class);

            default -> throw new IllegalArgumentException("Unknown CommandScheme");
        };
    }


    public class IllegalPayloadException extends Exception {
        public IllegalPayloadException(String message) {
            super(message);
        }
    }
}
