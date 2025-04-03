package de.dhbwkarlsruhe.modellbahn;

import de.dhbwkarlsruhe.modellbahn.Models.LocSpeed;
import de.dhbwkarlsruhe.modellbahn.Models.Model;
import de.dhbwkarlsruhe.modellbahn.Models.ModelFactory;
import de.dhbwkarlsruhe.modellbahn.schemes.Direction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LocTest {
	@Test
	void speed() {
		byte[] bytes = new byte[]{0x00,0x00,//Id bytes empty
				0x40, 0x0d, //loc id : 16397
				0x01, (byte) 0xf4, //speed 500 max 1024 min 0
				0x00,0x00};;

		Model jsonPayload = new LocSpeed(16397, 500);

		byte[] convertedBytes = jsonPayload.toByteArray();
		Assertions.assertArrayEquals(bytes, convertedBytes);

	}

}
