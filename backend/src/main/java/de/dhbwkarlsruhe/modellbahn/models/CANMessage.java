package de.dhbwkarlsruhe.modellbahn.models;

import de.dhbwkarlsruhe.modellbahn.BitUtilities;
import de.dhbwkarlsruhe.modellbahn.schemes.CommandScheme;
import de.dhbwkarlsruhe.modellbahn.schemes.Priority;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class CANMessage
{
    private final Priority priority;
    @Getter
    private final CommandScheme command;
    @Getter
    private final boolean response;
    private final int hashValue;
    //Data Length Code : number of data bytes (0-8)
    private final int dlc;
    @Getter
    private final Model payload;

    public CANMessage(Priority prio, CommandScheme command, Model p, boolean response)
    {
        this.priority = prio;
        this.command = command;
        this.response = response;
        this.hashValue = generateHashValue();
        this.dlc = p.getDLC();
        this.payload = p;
    }

    public CANMessage(byte[] message)
    {
        priority = setPriority(message);
        command = setCommand(message);
        response = setResponse(message);
        hashValue = setHashValue(message);
        dlc = setDlc(message);
        payload = setPayload(message);
    }

    private Priority setPriority(byte[] message)
    {
        byte firstByte = message[0];
        int prio = firstByte >> 4;
        return Priority.values()[prio];

    }

    private CommandScheme setCommand(byte[] message)
    {
        int secondByte = message[1];

        return CommandScheme.fromCommandValue(secondByte);
    }

    private boolean setResponse(byte[] message)
    {
        byte secondByte = message[1];
        return (secondByte & 0x01) == 1;


    }

    private int setHashValue(byte[] message)
    {
        byte firstByte = message[2];
        byte secondByte = message[3];
        return firstByte << 8 | (secondByte & 0xFF);
    }

    private int setDlc(byte[] message)
    {
        byte dlcByte = message[4];
        return dlcByte & 0x0F;
    }

    private Model setPayload(byte[] message)
    {
        byte[] payloadArray = Arrays.copyOfRange(message, 5, 5 + dlc);
        return ModelFactory.createPayloadFromBytes(payloadArray, command);
    }

    /**
     * technically there is a correct way to generate this hash but it is not necessary. This hardcoded hash works for the current usecases
     *
     * @return hash value of the CAN message
     */
    private int generateHashValue()
    {
        return 0x5738;
    }

    /*private int generateHashValue(){
        Random r = new Random();
        long longRand= r.nextLong(0xFFFFFFFFL);
        //uidHash is calculated by XORing the lower 2bytes with the higher 2 bytes of the UID
        int uidHash = (int) ((longRand & 0x0000FFFF) ^ ((longRand & 0xFFFF0000L) >> 16));
        //the first byte has always a 0 in the MSB
        uidHash = uidHash & 0xFF7F;
        //the second byte has always a 1 in the 2 LSB
        uidHash = uidHash| 0x0300;
        return uidHash;
    }*/
    public byte[] toByteArray()
    {
        byte[] firstByte = {getFirstByte()};
        byte[] secondByte = {getSecondByte()};
        byte[] hash = BitUtilities.intToByteArray(hashValue, 2);
        byte[] length = BitUtilities.intToByteArray(this.dlc, 1);
        byte[] data = payload.toByteArray();
        return BitUtilities.mergeByteArrays(List.of(firstByte, secondByte, hash, length, data));
    }

    /**
     * @return first byte of the CAN message
     * structure of the first byte
     * 4 bit priority
     * 3 bit 0
     * 1 bit command
     */
    private byte getFirstByte()
    {
        byte firstByte = (byte) (priority.ordinal() << 4);

        firstByte = (byte) (firstByte & 0xff | (command.getCommandValue() >> 7));
        return firstByte;
    }

    /**
     * @return second byte of CAN message
     * structure of the second byte
     * 7 bit command
     * 1 bit response
     */
    private byte getSecondByte()
    {
        byte secondByte;
        secondByte = (byte) command.getCommandValue();
        secondByte = (byte) (secondByte | (response ? 0x01 : 0x00));
        return secondByte;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        CANMessage that = (CANMessage) o;
        return response == that.response && hashValue == that.hashValue && dlc == that.dlc && priority == that.priority && command == that.command && Objects.equals(payload, that.payload);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(priority, command, response, hashValue, dlc, payload);
    }

}
