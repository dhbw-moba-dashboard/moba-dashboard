package de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.controller;

import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.CANMessage;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.TCPSocket;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.controller.models.request.LocModel;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.payloadtypes.Payload;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.payloadtypes.PayloadFactory;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.schemes.CommandScheme;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.schemes.Priority;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LokController {
	/**
	 *
	 * @param locID to identify the loc
	 * @param lokModel contains the new speed value range : 0-1023
	 */
	@PutMapping("/loc/{locID}/speed")
	public void setLocSpeed(@PathVariable int locID, @RequestBody LocModel.LocSpeed lokModel) {
		String json = lokModel.buildJson(locID);
		CANMessage message = new CANMessage(Priority.BEFEHLE, CommandScheme.LOCOMOTIVE_DIRECTION, json, false);
		TCPSocket.send(message);
	}

	/**
	 *
	 * @param locID to identify the loc
	 * @param lokModel contains the new direction : value range : 0-3
	 */
	@PutMapping("/loc/{locID}/direction")
	public void setLocDirection(@PathVariable int locID, @RequestBody LocModel.LocDirection lokModel) {
		String json = lokModel.buildJson(locID);
		CANMessage message = new CANMessage(Priority.BEFEHLE, CommandScheme.LOCOMOTIVE_DIRECTION, json, false);
		TCPSocket.send(message);
    }

	/**
	 *
	 * @return list of available locs with their loc-IDs
	 */
	@GetMapping("/loc/list")
	public List<Integer> getLocList() {
		CANMessage message = new CANMessage(Priority.MELDUNG, CommandScheme.LOCOMOTIVE_DIRECTION,"{filename:loks}", true);
		TCPSocket.send(message);
		CANMessage response = TCPSocket.receive();
		return List.of(1, 2, 3, 4, 5);
	}
}
