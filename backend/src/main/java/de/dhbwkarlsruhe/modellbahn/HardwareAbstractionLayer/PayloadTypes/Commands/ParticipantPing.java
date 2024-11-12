package de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.Commands;

import com.google.gson.GsonBuilder;
import de.dhbwkarlsruhe.modellbahn.BitUtilities;
import de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.Payload;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ParticipantPing extends Payload {
    private final int senderID;
    private final int softwareVersion;
    private final int deviceID;

    public ParticipantPing(byte[] data, int DLC, GsonBuilder builder) {
        super(builder, DLC);
        this.senderID = BitUtilities.transformBitSequenceToInt(data, 0, 0, 3, 7);
        this.softwareVersion = BitUtilities.transformBitSequenceToInt(data, 4, 0, 5, 7);
        this.deviceID = BitUtilities.transformBitSequenceToInt(data, 6, 0, 7, 7);
    }

    public ParticipantPing(int senderID, int softwareVersion, int deviceID, GsonBuilder builder, int DLC) {
        super(builder, DLC);
        this.senderID = senderID;
        this.softwareVersion = softwareVersion;
        this.deviceID = deviceID;
    }

    @Override
    public byte[] toByteArray() {
        List<byte[]> srcList = new ArrayList<>();
        srcList.add(BitUtilities.intToByteArray(getSoftwareVersion()));
        srcList.add(BitUtilities.intToByteArray(getSoftwareVersion()));
        srcList.add(BitUtilities.intToByteArray(getDeviceID()));
        return BitUtilities.mergeByteArrays(srcList, 8);
    }
}
