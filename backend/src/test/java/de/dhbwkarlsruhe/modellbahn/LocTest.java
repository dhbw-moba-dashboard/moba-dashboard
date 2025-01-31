package de.dhbwkarlsruhe.modellbahn;

import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.controller.models.request.LocModel;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.payloadtypes.Payload;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.payloadtypes.PayloadFactory;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.payloadtypes.commands.LocomotiveDirection;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.schemes.CommandScheme;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LocTest {
	@Test
	void speed() {
		byte[] bytes = new byte[]{0x00, 0x00, 0x00, 0x03, 0x00, 0x10};
		String json = "{locID:3,speed:16}";
		Payload jsonPayload = PayloadFactory.createPayloadFromJson(json, CommandScheme.LOCOMOTIVE_SPEED);
		Payload bytePayload = PayloadFactory.createPayloadFromBytes(bytes, CommandScheme.LOCOMOTIVE_SPEED);
		byte[] convertedBytes = bytePayload.toByteArray();
		Assertions.assertEquals(jsonPayload, bytePayload);
		Assertions.assertArrayEquals(bytes, convertedBytes);

	}

	@Test
	void model() {
		//Todo: test edge cases like -1 and serialize/deserialize json properly
		int speed = 16;
		int id = 4;
		LocModel.LocSpeed locSpeed = new LocModel.LocSpeed(speed);
		String input = locSpeed.buildJson(id);
		Assertions.assertEquals("{\"locID\":4,\"speed\":16}", input);
		LocModel.LocDirection locDirection = new LocModel.LocDirection(LocomotiveDirection.Direction.FORWARD);
		String inputDirection = locDirection.buildJson(id);
		Assertions.assertEquals("{\"locID\":4,\"direction\":FORWARD}", inputDirection);
	}
}
