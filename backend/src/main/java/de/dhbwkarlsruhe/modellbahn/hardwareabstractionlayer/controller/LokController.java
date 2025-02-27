package de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.controller;

import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.CANMessage;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.TCPSocket;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.controller.models.request.LocModel;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.payloadtypes.PayloadFactory;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.schemes.CommandScheme;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.schemes.Priority;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class LokController {

	private final TCPSocket tcpSocket;

	public LokController(TCPSocket tcpSocket) {
		this.tcpSocket = tcpSocket;
	}
	/**
	 *
	 * @param lokModel contains the new speed value range : 0-1023
	 */
	@PutMapping("/loc/speed")
	public ResponseEntity<String> setLocSpeed(@RequestBody LocModel.LocSpeed lokModel) {

		CANMessage message = new CANMessage(Priority.BEFEHLE, CommandScheme.LOCOMOTIVE_DIRECTION, lokModel, false);
		try{
		tcpSocket.send(message);
		}catch (IOException e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(PayloadFactory.getJsonSerialString(lokModel), HttpStatus.OK);
	}

	/**
	 *
	 * @param lokModel contains the new direction : value range : 0-3
	 */
	@PutMapping("/loc/direction")
	public ResponseEntity<String> setLocDirection(@RequestBody LocModel.LocDirection lokModel) {

		CANMessage message = new CANMessage(Priority.BEFEHLE, CommandScheme.LOCOMOTIVE_DIRECTION, lokModel, false);
		try{
			tcpSocket.send(message);
		}catch (IOException e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(PayloadFactory.getJsonSerialString(lokModel), HttpStatus.OK);
    }

	/**
	 *
	 * @return list of available locs with their loc-IDs
	 */
	@GetMapping("/loc/list")
	public List<Integer> getLocList() {
		return List.of(1, 2, 3);
	}
}
