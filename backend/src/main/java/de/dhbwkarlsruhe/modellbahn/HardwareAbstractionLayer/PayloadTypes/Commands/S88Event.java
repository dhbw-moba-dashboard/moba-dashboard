package de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.Commands;

import de.dhbwkarlsruhe.modellbahn.BitUtilities;
import de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.Payload;
import jakarta.annotation.Nullable;

public class S88Event implements Payload {
    public int deviceID;
    public int contactID;
    public int parameter = -1;
    public int oldState;
    public int newState;
    public int timestamp;
    public int DLC;
    public S88Event(byte[] data,int DLC) {
        this.DLC = DLC;
        this.deviceID = BitUtilities.transformBitSequenceToInt(data, 0, 0,1,7);
        this.contactID = BitUtilities.transformBitSequenceToInt(data, 0, 2,3,7);
        if (DLC == 5) {
            this.parameter = BitUtilities.transformBitSequenceToInt(data, 4, 0,4,7);
        } else if (DLC ==8) {
            this.oldState = BitUtilities.transformBitSequenceToInt(data, 4, 0,4,7);
            this.newState = BitUtilities.transformBitSequenceToInt(data, 5, 0,5,7);
            this.timestamp = BitUtilities.transformBitSequenceToInt(data, 6, 0,7,7);
        }

    }
    public S88Event(int deviceID, int contactID, int parameter, int oldState, int newState, int timestamp) {
        this.deviceID = deviceID;
        this.contactID = contactID;
        this.parameter = parameter;
        this.oldState = oldState;
        this.newState = newState;
    }
    @Override
    public byte[] toByteArray() {
        byte [] data = new byte[8];
        byte [] deviceID = BitUtilities.intToByteArray(this.deviceID);
        byte [] contactID = BitUtilities.intToByteArray(this.contactID);
        byte [] parameter = BitUtilities.intToByteArray(this.parameter);
        byte [] oldState = BitUtilities.intToByteArray(this.oldState);
        byte [] newState = BitUtilities.intToByteArray(this.newState);
        byte [] timestamp = BitUtilities.intToByteArray(this.timestamp);
        System.arraycopy(deviceID,0,data,0,2);
        System.arraycopy(contactID,0,data,2,2);
        if (this.parameter != -1)
            System.arraycopy(parameter,0,data,4,1);
        else {
            System.arraycopy(oldState,0,data,4,1);
            System.arraycopy(newState,0,data,5,1);
            System.arraycopy(timestamp,0,data,6,2);
        }
        return data;
    }

    @Override
    public Payload fromByteArray(byte[] data) {
        return null;
    }
}
