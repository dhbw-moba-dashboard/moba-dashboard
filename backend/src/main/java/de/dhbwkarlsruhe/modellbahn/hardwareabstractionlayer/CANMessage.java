package de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer;

import de.dhbwkarlsruhe.modellbahn.BitUtilities;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.payloadtypes.Payload;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.payloadtypes.PayloadFactory;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.schemes.CommandScheme;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.schemes.Priority;

import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class CANMessage {
    public Priority priority;
    public CommandScheme command;
    public boolean response;
    public int hashValue;
    public byte[] rawValue;
    //Data Length Code : number of data bytes (0-8)
    public byte DLC;
    public Payload payload;
    public CANMessage(Priority prio, CommandScheme command,String json, boolean response  ){
        this.priority = prio;
        this.command = command;
        this.response = response;
        this.hashValue = generateHashValue();
        this.payload = PayloadFactory.createPayloadFromJson(json, command);
    }

    public CANMessage(byte[] message) {
        System.out.println("Received message : "+ Arrays.toString(message));
    }

    private int generateHashValue(){
        Random r = new Random();
        long longRand= r.nextLong(0xFFFFFFFFL);
        //uidHash is calculated by XORing the lower 2bytes with the higher 2 bytes of the UID
        int uidHash = (int) ((longRand & 0x0000FFFF) ^ ((longRand & 0xFFFF0000L) >> 16));
        //the first byte has always a 0 in the MSB
        uidHash = uidHash & 0xFF7F;
        //the second byte has always a 1 in the 2 LSB
        uidHash = uidHash| 0x0300;
        return uidHash;
    }
    public byte[] toByteArray(){
        byte[] firstByte = {getFirstByte()};
        byte [] secondByte = {getSecondByte()};
        byte[] hash = BitUtilities.intToByteArray(hashValue,2);
        byte[] dlc = {(byte) (DLC << 4)};
        byte [] data = payload.toByteArray();
        return BitUtilities.mergeByteArrays(List.of(firstByte,secondByte,hash,dlc,data));
    }

    /**
     *
     * @return first byte of the CAN message
     * structure of the first byte
     * 4 bit priority
     * 3 bit 0
     * 1 bit command
     */
    private byte getFirstByte(){
        byte firstByte = 0x00;
        firstByte = (byte) (firstByte | (priority.ordinal() << 4));
        firstByte = (byte) (firstByte | (command.getCommandValue()>>7));
        return firstByte;
    }

    /**
     *
     * @return second byte of CAN message
     * structure of the second byte
     * 7 bit command
     * 1 bit response
     */
    private byte getSecondByte(){
        byte secondByte = 0x00;
        secondByte = (byte) (secondByte | (command.getCommandValue() & 0xFE));
        secondByte = (byte) (secondByte | (response? 0x01 : 0x00));
        return secondByte;
    }

}
