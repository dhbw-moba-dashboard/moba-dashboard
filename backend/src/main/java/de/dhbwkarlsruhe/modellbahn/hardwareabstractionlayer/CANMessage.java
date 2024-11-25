package de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer;

import de.dhbwkarlsruhe.modellbahn.BitUtilities;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.payloadtypes.Payload;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.payloadtypes.PayloadFactory;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.schemes.CommandScheme;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.schemes.Priority;

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
    public CANMessage(Priority prio, CommandScheme command, boolean response  ){
        this.priority = prio;
        this.command = command;
        this.response = response;
        this.hashValue = generateHashValue();
        this.payload = PayloadFactory.createPayloadFromJson("{}", command);
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

}
