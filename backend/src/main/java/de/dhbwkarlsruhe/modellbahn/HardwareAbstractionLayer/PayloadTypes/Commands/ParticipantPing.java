package de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.Commands;

import de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.Payload;

public class ParticipantPing implements Payload {
    public int senderID;
    public int softwareVersion;
    public int deviceID;
    public int DLC;
    ParticipantPing(byte[] data,int DLC) {


    }
    @Override
    public byte[] toByteArray() {
        return new byte[0];
    }

    @Override
    public Payload fromByteArray(byte[] data) {
        return null;
    }
}
