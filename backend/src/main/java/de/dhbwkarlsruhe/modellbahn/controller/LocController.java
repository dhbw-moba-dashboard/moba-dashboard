package de.dhbwkarlsruhe.modellbahn.controller;

import de.dhbwkarlsruhe.modellbahn.MobaSocket;
import de.dhbwkarlsruhe.modellbahn.database.services.LocService;
import de.dhbwkarlsruhe.modellbahn.models.*;
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
    private final LocService locService;


    /**
     * @param locSpeed contains the new speed value range : 0-1023
     */
    @PutMapping("/loc/speed")
    public ResponseEntity<String> setLocSpeed(@RequestBody LocSpeed locSpeed)
    {

        CANMessage message = new CANMessage(Priority.COMMAND, CommandScheme.LOCOMOTIVE_SPEED, locSpeed, false);
        try
        {
            tcpSocket.send(message);
        } catch (IOException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(ModelFactory.getJsonSerialString(locSpeed), HttpStatus.OK);
    }

    @GetMapping("/loc")
    public ResponseEntity<List<LocName>> getLocs()
    {
        return new ResponseEntity<>(locService.getLocs(), HttpStatus.OK);
    }

    @GetMapping("/loc/{locID}")
    public ResponseEntity<LocName> getLoc(@PathVariable int locID)
    {
        return new ResponseEntity<>(locService.getLocByID(locID), HttpStatus.OK);
    }


    /**
     * @param locDirection contains the new direction : value range : 0-3
     */
    @PutMapping("/loc/direction")
    public ResponseEntity<String> setLocDirection(@RequestBody LocDirection locDirection)
    {

        CANMessage message = new CANMessage(Priority.COMMAND, CommandScheme.LOCOMOTIVE_DIRECTION, locDirection, false);
        try
        {
            tcpSocket.send(message);
        } catch (IOException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(ModelFactory.getJsonSerialString(locDirection), HttpStatus.OK);
    }

}
