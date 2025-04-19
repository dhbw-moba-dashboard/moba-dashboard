package de.dhbwkarlsruhe.modellbahn.controller;

import de.dhbwkarlsruhe.modellbahn.database.services.LocService;
import de.dhbwkarlsruhe.modellbahn.moba_representation.can.CANMessage;
import de.dhbwkarlsruhe.modellbahn.moba_representation.can.MobaSocket;
import de.dhbwkarlsruhe.modellbahn.moba_representation.loc.LocDirection;
import de.dhbwkarlsruhe.modellbahn.moba_representation.loc.LocName;
import de.dhbwkarlsruhe.modellbahn.moba_representation.loc.LocSpeed;
import de.dhbwkarlsruhe.modellbahn.schemes.CommandScheme;
import de.dhbwkarlsruhe.modellbahn.schemes.Priority;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
public class LocController {

    private final MobaSocket tcpSocket;
    private final LocService locService;
    private static final Logger logger = LoggerFactory.getLogger(LocController.class);


    /**
     * @param locSpeed contains the new speed value range: 0-1023
     */
    @PutMapping("/loc/speed")
    public ResponseEntity<Void> setLocSpeed(@RequestBody LocSpeed locSpeed) {

        CANMessage message = new CANMessage(Priority.COMMAND, CommandScheme.LOCOMOTIVE_SPEED, locSpeed, false);
        try {
            tcpSocket.send(message);
        } catch (IOException e) {
            logError(e);
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/loc")
    public ResponseEntity<List<LocName>> getLocs() {
        return ResponseEntity.ok(locService.getLocs());
    }

    @GetMapping("/loc/{locID}")
    public ResponseEntity<LocName> getLoc(@PathVariable int locID) {
        return ResponseEntity.ok(locService.getLocByID(locID));
    }


    /**
     * @param locDirection contains the new direction : value range : 0-3
     */
    @PutMapping("/loc/direction")
    public ResponseEntity<Void> setLocDirection(@RequestBody LocDirection locDirection) {

        CANMessage message = new CANMessage(Priority.COMMAND, CommandScheme.LOCOMOTIVE_DIRECTION, locDirection, false);
        try {
            tcpSocket.send(message);
        } catch (IOException e) {
            logError(e);
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

    private void logError(Exception e) {
        logger.error(e.getMessage());
    }

}
