package de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.payloadtypes.commands;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import de.dhbwkarlsruhe.modellbahn.BitUtilities;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.payloadtypes.Payload;
import lombok.Getter;

import java.util.List;
import java.util.Objects;

@Getter
public class LocomotiveDirection implements Payload {
	private final Direction direction;
	private final int locID;

	public LocomotiveDirection(byte[] data) {
		locID = BitUtilities.transformBitSequenceToInt(data, 0, 0, 3, 8);
		int dir = BitUtilities.transformBitSequenceToInt(data, 4, 0, 4, 7);
		switch (dir) {
			case 1 -> direction = Direction.FORWARD;
			case 2 -> direction = Direction.BACKWARD;
			case 3 -> direction = Direction.SWITCH;
			default -> direction = Direction.SAME;
		}
	}

	public byte[] toByteArray() {
		List<byte[]> data = List.of(
				BitUtilities.intToByteArray(locID, 4),
				BitUtilities.intToByteArray(direction.ordinal(), 1)
		);
		return BitUtilities.mergeByteArrays(data);
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		LocomotiveDirection that = (LocomotiveDirection) o;
		return locID == that.locID && direction == that.direction;
	}

	@Override
	public int hashCode() {
		return Objects.hash(direction, locID);
	}

	public enum Direction {
		@SerializedName("0")
		SAME,
		@SerializedName("1")
		FORWARD,
		@SerializedName("2")
		BACKWARD,
		@SerializedName("3")
		SWITCH
	}
}
