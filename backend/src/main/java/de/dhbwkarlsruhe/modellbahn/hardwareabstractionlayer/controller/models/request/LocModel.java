package de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.controller.models.request;

import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.payloadtypes.commands.LocomotiveDirection;

public class LocModel {
	public record LocSpeed(int speed) {
		public String buildJson(int locID) {
			return String.format(
					"{\"locID\":%d," +
							"\"speed\":%d}",
					locID, speed
			);
		}
	}

	public record LocDirection(LocomotiveDirection.Direction direction) {
		public String buildJson(int locID) {
			return String.format(
					"{\"locID\":%d," +
							"\"direction\":%s}",
					locID, direction
			);
		}
	}
}
