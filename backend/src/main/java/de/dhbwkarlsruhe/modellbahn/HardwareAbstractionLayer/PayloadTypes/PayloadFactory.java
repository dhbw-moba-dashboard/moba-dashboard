package de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes;

import com.google.gson.Gson;
import de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.Commands.LocomotiveDirection;
import de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.Commands.LocomotiveSpeed;
import de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.Schemes.CommandScheme;

//Todo: Implement PayloadFactory
public class PayloadFactory {
    public static Payload createPayloadFromBytes(byte[] data, CommandScheme scheme) {
        return switch (scheme) {
            case LocomotiveSpeed -> new LocomotiveSpeed(data);
            case LocomotiveDirection -> new LocomotiveDirection(data);

            default -> throw new IllegalArgumentException("Unknown CommandScheme");
        };
    }

    public static Payload createPayloadFromJson(String json, CommandScheme scheme) {
        Gson gson = new Gson();

        return switch (scheme) {
            case LocomotiveDirection -> gson.fromJson(json, LocomotiveDirection.class);
            case LocomotiveSpeed -> gson.fromJson(json, LocomotiveSpeed.class);

            default -> throw new IllegalArgumentException("Unknown CommandScheme");
        };
    }

}
