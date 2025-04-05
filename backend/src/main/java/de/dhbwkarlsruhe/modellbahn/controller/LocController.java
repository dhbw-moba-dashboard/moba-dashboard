package de.dhbwkarlsruhe.modellbahn.controller;

import de.dhbwkarlsruhe.modellbahn.Models.CANMessage;
import de.dhbwkarlsruhe.modellbahn.MobaSocket;
import de.dhbwkarlsruhe.modellbahn.Models.LocDirection;
import de.dhbwkarlsruhe.modellbahn.Models.LocSpeed;
import de.dhbwkarlsruhe.modellbahn.Models.ModelFactory;
import de.dhbwkarlsruhe.modellbahn.schemes.CommandScheme;
import de.dhbwkarlsruhe.modellbahn.schemes.Priority;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class LocController
{

	private final MobaSocket tcpSocket;

	public LocController(MobaSocket tcpSocket) {
		this.tcpSocket = tcpSocket;
	}
	/**
	 *
	 * @param lokModel contains the new speed value range : 0-1023
	 */
	@PutMapping("/loc/speed")
	public ResponseEntity<String> setLocSpeed(@RequestBody LocSpeed lokModel) {

		CANMessage message = new CANMessage(Priority.BEFEHLE, CommandScheme.LOCOMOTIVE_SPEED, lokModel, false);
		try{
		tcpSocket.send(message);
		}catch (IOException e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(ModelFactory.getJsonSerialString(lokModel), HttpStatus.OK);
	}
	@GetMapping("/loc/speed/{locId}")
	public ResponseEntity<String> getLocSpeed(@PathVariable int locId) {
		LocSpeed lokModel = new LocSpeed(locId, -1);
		CANMessage message = new CANMessage(Priority.BEFEHLE, CommandScheme.LOCOMOTIVE_SPEED, lokModel, false);
        try
        {
            tcpSocket.send(message);
			CANMessage response = tcpSocket.receive(CommandScheme.LOCOMOTIVE_SPEED);
			return new ResponseEntity<>(ModelFactory.getJsonSerialString(response.getPayload()), HttpStatus.OK);

		} catch (IOException e)
        {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

	/**
	 *
	 * @param lokModel contains the new direction : value range : 0-3
	 */
	@PutMapping("/loc/direction")
	public ResponseEntity<String> setLocDirection(@RequestBody LocDirection lokModel) {

		CANMessage message = new CANMessage(Priority.BEFEHLE, CommandScheme.LOCOMOTIVE_DIRECTION, lokModel, false);
		try{
			tcpSocket.send(message);
		}catch (IOException e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(ModelFactory.getJsonSerialString(lokModel), HttpStatus.OK);
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
