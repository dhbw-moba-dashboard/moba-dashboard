package de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * This refers to the last 8 Bytes of a CANMessage, which contain the
 */
//Todo: check for more methods to implement
public abstract class Payload {
    private final Gson gson;
    protected final int DLC;
    public Payload(GsonBuilder builder,int DLC){
        this.DLC = DLC;
        gson = builder.create();
    }

    public abstract byte[] toByteArray();
    @Override
    public String toString(){
        return gson.toJson(this);
    }
}
