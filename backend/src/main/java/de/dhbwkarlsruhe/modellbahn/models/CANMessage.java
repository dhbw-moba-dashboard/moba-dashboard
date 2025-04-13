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
    /**
     * value is used to check if a frame is valid. There is a correct way to generate this hash, but this works for any
     * current use case
     */
    private static final int HASH_VALUE = 0x5738;

    private final Priority priority;
    @Getter
    private final CommandScheme command;
    @Getter
    private final boolean response;
    private final int hashValue;
    /**
     * data length code: the number of data bytes : 0-8
     */
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

    /**
     * first 4 bits of a frame are the priority
     *
     * @param message CAN-Frame as a byte-array
     * @return Priority of CAN-Frame
     */
    private Priority setPriority(byte[] message)
    {
        byte firstByte = message[0];
        int prio = firstByte >> 4;
        return Priority.values()[prio];
    }

    /**
     * evaluates the command of a given frame. According to documentation the command takes up 8 bit in a frame
     * from the last bit of the first byte to the 7th bit of the second byte but the first bit is always zero so it's not necessary to consider the first byte
     */
    private CommandScheme setCommand(byte[] message)
    {
        int secondByte = message[1];

        return CommandScheme.fromCommandValue(secondByte);
    }

    /**
     * last bit of the second byte is the response bit
     *
     * @return checks whether this message is a response from the CS3 or not
     */
    private boolean setResponse(byte[] message)
    {
        byte secondByte = message[1];
        return (secondByte & 0x01) == 1;
    }

    /**
     * This is currently completely useless because the hashvalue has no value for the application only for the CS3
     *
     * @return Hash value of this frame.
     */
    private int setHashValue(byte[] message)
    {
        byte firstByte = message[2];
        byte secondByte = message[3];
        return firstByte << 8 | (secondByte & 0xFF);
    }

    /**
     * the dlc is important for creating the Model.
     * They are the last 4 bit of the 5th byte.
     *
     * @return dlc of the frame
     */
    private int setDlc(byte[] message)
    {
        byte dlcByte = message[4];
        return dlcByte & 0x0F;
    }

    /**
     * the last 8 byte of a frame contain the
     * data bytes
     *
     * @return the Model of the current message
     */
    private Model setPayload(byte[] message)
    {
        byte[] payloadArray = Arrays.copyOfRange(message, 5, 5 + dlc);
        return ModelFactory.createPayloadFromBytes(payloadArray, command);
    }

    /**
     * currently there is only the hard-coded hash but in the future there may be the need to implement the correct method
     */
    private int generateHashValue()
    {
        return HASH_VALUE;
    }

    /**
     * @return byte serialization of the object
     */
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
