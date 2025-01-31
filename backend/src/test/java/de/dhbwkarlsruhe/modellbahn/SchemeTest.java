package de.dhbwkarlsruhe.modellbahn;

import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.schemes.CommandScheme;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SchemeTest {
	@Test
	void commandScheme() {
		CommandScheme c = CommandScheme.fromCommandValue(1);
		Assertions.assertEquals("LocomotiveDiscovery", c.getCommand());
		c = CommandScheme.fromCommandValue(69);
		Assertions.assertEquals("UnknownCommand", c.getCommand());
		c = CommandScheme.fromCommand("AccessoriesConfig");
		Assertions.assertEquals(12, c.getCommandValue());
	}
}
