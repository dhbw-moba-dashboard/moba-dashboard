package de.dhbwkarlsruhe.modellbahn;

import de.dhbwkarlsruhe.modellbahn.Models.CANMessage;
import de.dhbwkarlsruhe.modellbahn.Models.LocSpeed;
import de.dhbwkarlsruhe.modellbahn.schemes.CommandScheme;
import de.dhbwkarlsruhe.modellbahn.schemes.Priority;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CANMessageTest
{
    @Test
    void messageToByteArray()
    {
        LocSpeed locSpeed = new LocSpeed(16397, 500);
        byte[] resultingByteArray = new byte[]{0x00, 0x08 //4 bit priority 3bit padding 8bit command 1bit answer bit
                ,0x57,0x38//hash
                ,0x06 //4bit padding 4bit dlc
                ,0x00,0x00,//Id bytes empty
                0x40, 0x0d, //loc id : 16397
                0x01, (byte) 0xf4, //speed 500 max 1024 min 0
                0x00,0x00};
        CANMessage message = new CANMessage(Priority.BEFEHLE, CommandScheme.LOCOMOTIVE_SPEED, locSpeed,false);

        byte[] convertedBytes = message.toByteArray();
        Assertions.assertArrayEquals(resultingByteArray, convertedBytes);
    }

    @Test
    void byteArrayToMessage()
    {
        LocSpeed locSpeed = new LocSpeed(16397, 500);
        byte[] resultingByteArray = new byte[]{0x00, 0x08 //4 bit priority 3bit padding 8bit command 1bit answer bit
                , 0x57, 0x38//hash
                , 0x06 //4bit padding 4bit dlc
                , 0x00, 0x00,//Id bytes empty
                0x40, 0x0d, //loc id : 16397
                0x01, (byte) 0xf4, //speed 500 max 1024 min 0
                0x00, 0x00};
        CANMessage resultingMessage = new CANMessage(Priority.BEFEHLE, CommandScheme.LOCOMOTIVE_SPEED, locSpeed, false);
        CANMessage convertedMessage = new CANMessage(resultingByteArray);
        Assertions.assertEquals(resultingMessage, convertedMessage);
    }
}
