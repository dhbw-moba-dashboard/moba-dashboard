package de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.Controller.models.request;

import de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.Commands.LocomotiveDirection;

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
