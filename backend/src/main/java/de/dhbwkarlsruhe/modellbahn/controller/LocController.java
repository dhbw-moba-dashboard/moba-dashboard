package de.dhbwkarlsruhe.modellbahn.controller;

import de.dhbwkarlsruhe.modellbahn.database.services.LocService;
import de.dhbwkarlsruhe.modellbahn.moba_representation.interfaces.SimpleLocValue;
import de.dhbwkarlsruhe.modellbahn.moba_representation.loc.LocDirection;
import de.dhbwkarlsruhe.modellbahn.moba_representation.loc.LocHandler;
import de.dhbwkarlsruhe.modellbahn.moba_representation.loc.LocName;
import de.dhbwkarlsruhe.modellbahn.moba_representation.loc.LocSpeed;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class LocController {


    private final LocService locService;

    private final LocHandler locHandler;


    /**
     * @param locSpeed contains the new speed value range: 0-1023
     */
    @PutMapping("/loc/speed")
    public ResponseEntity<Void> setLocSpeed(@RequestBody LocSpeed locSpeed) {
        return setValue(locSpeed);
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
        return setValue(locDirection);
    }

    public ResponseEntity<Void> setValue(SimpleLocValue value) {
        boolean success = locHandler.setValue(value);
        if (!success) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

}
