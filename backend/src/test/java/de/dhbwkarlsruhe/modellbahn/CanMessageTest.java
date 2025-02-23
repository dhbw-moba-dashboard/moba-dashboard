package de.dhbwkarlsruhe.modellbahn;

import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.CANMessage;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.controller.models.request.LocModel;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.schemes.CommandScheme;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.schemes.Direction;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.schemes.Priority;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CanMessageTest {
    @Test
    void hash(){
        CANMessage message = new CANMessage(Priority.BEFEHLE, CommandScheme.LOCOMOTIVE_DIRECTION, new LocModel.LocDirection(1, Direction.FORWARD), false);
        int smallestHash = 0x0300;
        int prunedHash = message.hashValue&0x0380;
        Assertions.assertEquals(smallestHash, prunedHash);
    }
}
