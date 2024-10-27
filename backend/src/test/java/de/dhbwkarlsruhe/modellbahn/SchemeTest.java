package de.dhbwkarlsruhe.modellbahn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.Schemes.CommandScheme;


public class SchemeTest {
    @Test
    void commandScheme(){
        CommandScheme c = CommandScheme.fromCommandValue(1);
        Assertions.assertEquals(c.getCommand(),"LocomotiveDiscovery");
        c = CommandScheme.fromCommandValue(69);
        Assertions.assertEquals(c.getCommand(),"UnknownCommand");
        c=CommandScheme.fromCommand("AccessoriesConfig");
        Assertions.assertEquals(c.getCommandValue(),12);
    }
}
