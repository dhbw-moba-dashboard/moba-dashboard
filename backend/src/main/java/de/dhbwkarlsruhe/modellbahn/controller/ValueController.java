package de.dhbwkarlsruhe.modellbahn.controller;

import de.dhbwkarlsruhe.modellbahn.database.entities.Value;
import de.dhbwkarlsruhe.modellbahn.database.services.ValueService;
import de.dhbwkarlsruhe.modellbahn.schemes.LocValueScheme;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Get-Endpoints to request database values from a specific loc
 */
@RestController
@AllArgsConstructor
public class ValueController
{
    private final ValueService valueService;

    @GetMapping("/request/speed/{locID}")
    public ResponseEntity<List<Value>> getLocSpeed(
            @PathVariable int locID,
            @RequestParam(required = false) Optional<Integer> startTime,
            @RequestParam(required = false) Optional<Integer> endTime,
            @RequestParam(required = false) Optional<Integer> entries
    )
    {
        return getLocValue(startTime, endTime, entries, locID, LocValueScheme.SPEED);

    }

    @GetMapping("/request/direction/{locID}")
    public ResponseEntity<List<Value>> getLocDirection(
            @PathVariable int locID,
            @RequestParam(required = false) Optional<Integer> startTime,
            @RequestParam(required = false) Optional<Integer> endTime,
            @RequestParam(required = false) Optional<Integer> entries
    )
    {
        return getLocValue(startTime, endTime, entries, locID, LocValueScheme.DIRECTION);
    }

    private ResponseEntity<List<Value>> getLocValue(Optional<Integer> startTime, Optional<Integer> endTime, Optional<Integer> entries, int locID, LocValueScheme scheme)
    {
        if (startTime.isPresent() && endTime.isPresent())
        {
            List<Value> valueList = valueService.getLocValuesByScheme(scheme, locID, startTime.get(), endTime.get());

            return ResponseEntity.ok(valueList);
        }

        if (entries.isPresent())
        {
            List<Value> valueList = valueService.getLocValuesByScheme(scheme, locID, entries.get());
            return ResponseEntity.ok(valueList);
        }

        return ResponseEntity.badRequest().build();
    }


}
