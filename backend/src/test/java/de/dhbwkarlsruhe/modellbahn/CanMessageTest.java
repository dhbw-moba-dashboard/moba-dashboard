package de.dhbwkarlsruhe.modellbahn;

import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.CANMessage;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.schemes.CommandScheme;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.schemes.Priority;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class CanMessageTest {
    @Test
    void hash(){
        CANMessage message= new CANMessage(Priority.BEFEHLE, CommandScheme.LOCOMOTIVE_DIRECTION, false);
        int smallestHash = 0x0300;
        int prunedHash = message.hashValue&0x0380;
        Assertions.assertEquals(smallestHash, prunedHash);
    }
}
