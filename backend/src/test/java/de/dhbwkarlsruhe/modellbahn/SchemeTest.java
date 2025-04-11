package de.dhbwkarlsruhe.modellbahn;

import de.dhbwkarlsruhe.modellbahn.schemes.CommandScheme;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SchemeTest {
	@Test
	void commandScheme() {
		CommandScheme c = CommandScheme.fromCommandValue(0x02);
		Assertions.assertEquals("LocomotiveDiscovery", c.getCommand());
		c = CommandScheme.fromCommandValue(0x69);
		Assertions.assertEquals("UnknownCommand", c.getCommand());
		c = CommandScheme.fromCommand("AccessoriesConfig");
		Assertions.assertEquals(0x18, c.getCommandValue());
	}
}
