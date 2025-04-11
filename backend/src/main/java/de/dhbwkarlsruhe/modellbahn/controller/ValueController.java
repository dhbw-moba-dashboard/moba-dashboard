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

@RestController
@AllArgsConstructor
public class ValueController
{
    private final ValueService valueService;

    @GetMapping("/timerequest/speed/{locID}")
    public ResponseEntity<List<String>> getLocSpeed(@PathVariable int locID, @RequestParam int startTime, @RequestParam int endTime)
    {
        List<Value> valueList = valueService.getLocValuesByScheme(LocValueScheme.SPEED, locID, startTime, endTime);
        List<String> jsonRepresentation = valueList.stream()
                .map(Value::toString)
                .toList();
        return ResponseEntity.ok(jsonRepresentation);
    }

    @GetMapping("/timerequest/direction/{locID}")
    public ResponseEntity<List<String>> getLocDirection(@PathVariable int locID, @RequestParam int startTime, @RequestParam int endTime)
    {
        List<Value> valueList = valueService.getLocValuesByScheme(LocValueScheme.DIRECTION, locID, startTime, endTime);
        List<String> jsonRepresentation = valueList.stream()
                .map(Value::toString)
                .toList();
        return ResponseEntity.ok(jsonRepresentation);
    }

    @GetMapping("/numberrequest/speed/{locID}")
    public ResponseEntity<List<String>> getLocSpeedByNumber(@RequestParam int entries, @PathVariable int locID)
    {
        List<Value> valueList = valueService.getLocValuesByScheme(LocValueScheme.SPEED, locID, entries);
        List<String> jsonRepresentation = valueList.stream()
                .map(Value::toString)
                .toList();
        return ResponseEntity.ok(jsonRepresentation);
    }

    @GetMapping("/numberrequest/direction/number/{locID}")
    public ResponseEntity<List<String>> getLocDirectionByNumber(@PathVariable int locID, @RequestParam int entries)
    {
        List<Value> valueList = valueService.getLocValuesByScheme(LocValueScheme.DIRECTION, locID, entries);
        List<String> jsonRepresentation = valueList.stream()
                .map(Value::toString)
                .toList();
        return ResponseEntity.ok(jsonRepresentation);
    }
}
