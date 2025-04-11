package de.dhbwkarlsruhe.modellbahn.models;


import com.google.gson.Gson;
import de.dhbwkarlsruhe.modellbahn.schemes.CommandScheme;

public class ModelFactory
{
    //this prevents the class from being instantiated
    private ModelFactory()
    {
    }

    public static Model createPayloadFromBytes(byte[] data, CommandScheme scheme)
    {
        return switch (scheme)
        {
            case LOCOMOTIVE_SPEED -> LocSpeed.createLocSpeed(data);
            case LOCOMOTIVE_DIRECTION -> LocDirection.createLocDirection(data);

            default -> UnknownModel.createErrorModel(data);
        };
    }


    public static String getJsonSerialString(Model p)
    {
        Gson g = new Gson();
        return g.toJson(p);
    }


}
