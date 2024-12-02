package de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.payloadtypes;

import com.google.gson.Gson;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.payloadtypes.commands.LocomotiveDirection;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.payloadtypes.commands.LocomotiveSpeed;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.payloadtypes.commands.RequestConfigData;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.schemes.CommandScheme;

public class PayloadFactory {
	//this prevents the class from being instantiated
	private PayloadFactory() {
	}
    public static Payload createPayloadFromBytes(byte[] data, CommandScheme scheme) {
        return switch (scheme) {
			case LOCOMOTIVE_SPEED -> new LocomotiveSpeed(data);
			case LOCOMOTIVE_DIRECTION -> new LocomotiveDirection(data);

            default -> throw new IllegalArgumentException("Unknown CommandScheme");
        };
    }

    public static Payload createPayloadFromJson(String json, CommandScheme scheme) {
        Gson gson = new Gson();

        return switch (scheme) {
			case LOCOMOTIVE_DIRECTION -> gson.fromJson(json, LocomotiveDirection.class);
			case LOCOMOTIVE_SPEED -> gson.fromJson(json, LocomotiveSpeed.class);
            case REQUEST_CONFIG_DATA -> gson.fromJson(json, RequestConfigData.class);

            default -> throw new IllegalArgumentException("Unknown CommandScheme");
        };
    }

}
