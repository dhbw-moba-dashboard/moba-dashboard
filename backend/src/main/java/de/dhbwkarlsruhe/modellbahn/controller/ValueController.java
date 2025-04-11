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
        if (startTime.isPresent() && endTime.isPresent())
        {
            List<Value> valueList = valueService.getLocValuesByScheme(LocValueScheme.SPEED, locID, startTime.get(), endTime.get());

            return ResponseEntity.ok(valueList);
        }

        if (entries.isPresent())
        {
            List<Value> valueList = valueService.getLocValuesByScheme(LocValueScheme.SPEED, locID, entries.get());
            return ResponseEntity.ok(valueList);
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/request/direction/{locID}")
    public ResponseEntity<List<Value>> getLocDirection(@PathVariable int locID, @RequestParam int startTime, @RequestParam int endTime)
    {
        List<Value> valueList = valueService.getLocValuesByScheme(LocValueScheme.DIRECTION, locID, startTime, endTime);
        return ResponseEntity.ok(valueList);
    }

    @GetMapping("/request/direction/number/{locID}")
    public ResponseEntity<List<Value>> getLocDirectionByNumber(@PathVariable int locID, @RequestParam int entries)
    {
        List<Value> valueList = valueService.getLocValuesByScheme(LocValueScheme.DIRECTION, locID, entries);
        return ResponseEntity.ok(valueList);
    }
}
