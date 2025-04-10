package de.dhbwkarlsruhe.modellbahn.controller;

import de.dhbwkarlsruhe.modellbahn.Models.*;
import de.dhbwkarlsruhe.modellbahn.MobaSocket;
import de.dhbwkarlsruhe.modellbahn.database.repositories.LocRepository;
import de.dhbwkarlsruhe.modellbahn.database.repositories.ValueRepository;
import de.dhbwkarlsruhe.modellbahn.database.services.LocService;
import de.dhbwkarlsruhe.modellbahn.database.services.ValueService;
import de.dhbwkarlsruhe.modellbahn.schemes.CommandScheme;
import de.dhbwkarlsruhe.modellbahn.schemes.Priority;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
public class LocController
{

	private final MobaSocket tcpSocket;
	private  final LocService locService;
	private final ValueService valueService;


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
	@PutMapping("/loc/register")
	public ResponseEntity<String> registerLoc(@RequestBody LocName loc){
		locService.addLoc(loc);
		return new ResponseEntity<>("Loc saved", HttpStatus.OK);
	}
	@GetMapping("/loc/speed/{locId}")
	public ResponseEntity<String> getLocSpeed(@PathVariable int locId) {
		List<String> speeds = valueService.getLocSpeeds().stream().map(ModelFactory::getJsonSerialString).toList();
		return ResponseEntity.ok(speeds.toString());
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
