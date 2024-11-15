package de.dhbwkarlsruhe.modellbahn;

import de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.Payload;
import de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.PayloadFactory;
import de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.Schemes.CommandScheme;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LocTest {
	@Test
	void speed() {
		byte[] bytes = new byte[]{0x00, 0x00, 0x00, 0x03, 0x00, 0x10};
		String json = "{locID:3,speed:16}";
		Payload jsonPayload = PayloadFactory.createPayloadFromJson(json, CommandScheme.LocomotiveSpeed);
		Payload bytePayload = PayloadFactory.createPayloadFromBytes(bytes, CommandScheme.LocomotiveSpeed);
		byte[] convertedBytes = bytePayload.toByteArray();
		Assertions.assertEquals(jsonPayload, bytePayload);
		Assertions.assertArrayEquals(bytes, convertedBytes);

	}
}
