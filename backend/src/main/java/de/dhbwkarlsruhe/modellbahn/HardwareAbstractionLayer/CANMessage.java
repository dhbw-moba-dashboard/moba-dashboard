package de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer;

import de.dhbwkarlsruhe.modellbahn.BitUtilities;
import de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.Payload;
import de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.PayloadFactory;
import de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.Schemes.CommandScheme;

public class CANMessage {
    public int priority;
    public CommandScheme command;
    public boolean response;
    public int hashValue;
    public byte[] rawValue;
    //Data Length Code : number of data bytes (0-8)
    public byte DLC;
    public Payload payload;

    public CANMessage(int priority, CommandScheme command, boolean response, int hashValue, Payload payload) {
        this.priority = priority;
        this.command = command;
        this.response = response;
        this.hashValue = hashValue;
        this.payload = payload;
    }

    public CANMessage(byte[] rawValue) {
        this.rawValue = rawValue;
    }

    public static CANMessage getMessageFromBytes(byte[] data) throws PayloadFactory.IllegalPayloadException {
        //gets the first 4 most significant bit
        int priority = BitUtilities.transformBitSequenceToInt(data, 0, 0, 0, 3);
        /* gets the least significant bit of the first byte
            which is the most significant bit of the command
            and the first 7 bits of the second byte
         */
        int commandValue = BitUtilities.transformBitSequenceToInt(data, 0, 4, 1, 3);

        CommandScheme command = CommandScheme.fromCommandValue(commandValue);
        boolean response = BitUtilities.transformBitSequenceToInt(data, 1, 5, 1, 6) == 1;
        int hash_value = BitUtilities.transformBitSequenceToInt(data, 1, 7, 3, 6);
        int DLC = BitUtilities.transformBitSequenceToInt(data, 3, 7, 4, 2);
        byte[] payloadData = BitUtilities.getBitSequence(data, 5, 3, data.length - 1, 7);
        Payload payload = PayloadFactory.createPayloadFromBytes(payloadData, command, DLC);
        return new CANMessage(priority, command, response, hash_value, payload);
    }

}
