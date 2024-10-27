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
    public Payload payload;
    public CANMessage(int priority, CommandScheme command, boolean response, int hashValue, Payload payload){
        this.priority = priority;
        this.command = command;
        this.response = response;
        this.hashValue = hashValue;
        this.payload = payload;
    }
    public CANMessage(byte[] rawValue){
        this.rawValue = rawValue;
    }

    public static CANMessage getMessageFromBytes(byte[] data){
        //gets the first 4 most significant bit
         int priority= BitUtilities.transformBitSequenceToInt(data, 0, 0,0,3);
        /* gets the least significant bit of the first byte
            which is the most significant bit of the command
            and the first 6 bits of the second byte
         */
        int commandValue = BitUtilities.transformBitSequenceToInt(data, 0, 7, 1, 6);
        CommandScheme command = CommandScheme.fromCommandValue(commandValue);

        boolean response = BitUtilities.transformBitSequenceToInt(data, 1, 7, 1, 7) == 1;
        int hash_value = BitUtilities.transformBitSequenceToInt(data, 1, 0, 2, 7);
        byte [] payloadData = BitUtilities.getBitSequence(data, 5, 0, 13, 7);
        Payload payload = PayloadFactory.getPayload(payloadData);
        return new CANMessage(priority, command, response, hash_value, payload);
    }

}
