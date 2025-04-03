package de.dhbwkarlsruhe.modellbahn;

import de.dhbwkarlsruhe.modellbahn.Models.CANMessage;
import de.dhbwkarlsruhe.modellbahn.Models.LocSpeed;
import de.dhbwkarlsruhe.modellbahn.schemes.CommandScheme;
import de.dhbwkarlsruhe.modellbahn.schemes.Priority;
import org.junit.jupiter.api.Test;

public class CANMessageTest
{
    @Test
    void speed(){
        LocSpeed locSpeed = new LocSpeed(16397, 500);
        byte[] resultingByteArray = new byte[]{0x00,0x08,0x57,0x38,0x06 // header
                ,0x00,0x00,//Id bytes empty
                0x40, 0x0d, //loc id : 16397
                0x01, (byte) 0xf4, //speed 500 max 1024 min 0
                0x00,0x00};
        CANMessage message = new CANMessage(Priority.BEFEHLE, CommandScheme.LOCOMOTIVE_SPEED, locSpeed,false);
    }
}
