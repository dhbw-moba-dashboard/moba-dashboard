package de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.payloadtypes;


import com.google.gson.Gson;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.controller.models.request.LocModel.LocDirection;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.controller.models.request.LocModel.LocSpeed;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.schemes.CommandScheme;

public class PayloadFactory {
	//this prevents the class from being instantiated
	private PayloadFactory() {
	}
    public static Payload createPayloadFromBytes(byte[] data, CommandScheme scheme) {
        return switch (scheme) {
			case LOCOMOTIVE_SPEED -> LocSpeed.createLocSpeed(data);
			case LOCOMOTIVE_DIRECTION -> LocDirection.createLocDirection(data);

            default -> throw new IllegalArgumentException("Unknown CommandScheme");
        };
    }

	public static String getJsonSerialString(Payload p) {
		Gson g = new Gson();
		return g.toJson(p);
	}
    

}
