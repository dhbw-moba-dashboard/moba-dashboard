package de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.controller.models.request;

import de.dhbwkarlsruhe.modellbahn.BitUtilities;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.payloadtypes.Payload;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.schemes.Direction;

import java.util.ArrayList;
import java.util.List;

public class LocModel {
	public record LocSpeed(int locID, int speed) implements Payload {
		public static LocSpeed createLocSpeed(byte[] data) {
			int id = BitUtilities.transformBitSequenceToInt(data, 0, 0, 3, 7);
			int speed = BitUtilities.transformBitSequenceToInt(data, 4, 0, 5, 7);
			return new LocSpeed(id, speed);
		}

		@Override
		public byte[] toByteArray() {
			List<byte[]> src = new ArrayList<>();
			src.add(BitUtilities.intToByteArray(locID, 4));
			src.add(BitUtilities.intToByteArray(speed, 2));
			return BitUtilities.mergeByteArrays(src);
		}
	}

	public record LocDirection(int locID, Direction direction) implements Payload {
		public static LocDirection createLocDirection(byte[] data) {
			int id = BitUtilities.transformBitSequenceToInt(data, 0, 0, 3, 8);
			int dir = BitUtilities.transformBitSequenceToInt(data, 4, 0, 4, 7);
			Direction direction;
			switch (dir) {
				case 1 -> direction = Direction.FORWARD;
				case 2 -> direction = Direction.BACKWARD;
				case 3 -> direction = Direction.SWITCH;
				default -> direction = Direction.SAME;
			}
			return new LocDirection(id, direction);
		}

		@Override
		public byte[] toByteArray() {
			List<byte[]> data = List.of(
					BitUtilities.intToByteArray(locID, 4),
					BitUtilities.intToByteArray(direction.ordinal(), 1)
			);
			return BitUtilities.mergeByteArrays(data);
		}

	}

}
