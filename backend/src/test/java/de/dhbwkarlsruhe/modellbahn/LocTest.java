package de.dhbwkarlsruhe.modellbahn;

import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.controller.models.request.LocModel;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.payloadtypes.Payload;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.payloadtypes.PayloadFactory;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.schemes.Direction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LocTest {
	@Test
	void speed() {
		byte[] bytes = new byte[]{0x00, 0x00, 0x00, 0x03, 0x00, 0x10};

		Payload jsonPayload = new LocModel.LocSpeed(3, 16);
		Payload bytePayload = LocModel.LocSpeed.createLocSpeed(bytes);
		byte[] convertedBytes = bytePayload.toByteArray();
		Assertions.assertEquals(jsonPayload, bytePayload);
		Assertions.assertArrayEquals(bytes, convertedBytes);

	}

	@Test
	void model() {
		//Todo: test edge cases like -1 and serialize/deserialize json properly
		int speed = 16;
		int id = 4;
		LocModel.LocSpeed locSpeed = new LocModel.LocSpeed(id, speed);
		Assertions.assertEquals("{\"locID\":4,\"speed\":16}", PayloadFactory.getJsonSerialString(locSpeed));
		LocModel.LocDirection locDirection = new LocModel.LocDirection(id, Direction.FORWARD);
		Assertions.assertEquals("{\"locID\":4,\"direction\":\"FORWARD\"}", PayloadFactory.getJsonSerialString(locDirection));
	}
}
