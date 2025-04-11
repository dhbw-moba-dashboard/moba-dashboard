package de.dhbwkarlsruhe.modellbahn.models;

import com.google.gson.Gson;
import de.dhbwkarlsruhe.modellbahn.schemes.CommandScheme;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

//this prevents the class from being instantiated
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ModelFactory {
    public static Model createPayloadFromBytes(byte[] data, CommandScheme scheme) {
        return switch (scheme) {
            case LOCOMOTIVE_SPEED -> LocSpeed.createLocSpeed(data);
            case LOCOMOTIVE_DIRECTION -> LocDirection.createLocDirection(data);

            default -> UnknownModel.createErrorModel();
        };
    }

    public static String getJsonSerialString(Model p) {
        Gson g = new Gson();
        return g.toJson(p);
    }
}
