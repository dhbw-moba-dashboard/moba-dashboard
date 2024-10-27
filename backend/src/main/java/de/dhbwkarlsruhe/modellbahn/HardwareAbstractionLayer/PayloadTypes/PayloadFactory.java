package de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes;
//Todo: Implement PayloadFactory
public class PayloadFactory {
    public static Payload getPayload(byte[] data){
        return new Loc();
    }
}
