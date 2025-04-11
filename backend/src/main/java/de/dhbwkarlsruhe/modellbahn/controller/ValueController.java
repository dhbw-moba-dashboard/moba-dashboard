package de.dhbwkarlsruhe.modellbahn.controller;

import de.dhbwkarlsruhe.modellbahn.database.entities.Value;
import de.dhbwkarlsruhe.modellbahn.database.services.ValueService;
import de.dhbwkarlsruhe.modellbahn.schemes.LocValueScheme;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ValueController
{


    private final ValueService valueService;

    @GetMapping("/request/speed")
    public ResponseEntity<List<String>> getLocSpeed(@RequestBody TimeStampBasedRequest record) {
        List<Value> valueList =  valueService.getLocValuesByScheme(LocValueScheme.SPEED, record.start(), record.end(), record.locID());
        List<String> jsonRepresentation = valueList.stream()
                .map(Value::toString)
                .toList();
        return ResponseEntity.ok(jsonRepresentation);
    }
    @GetMapping("/request/direction")
    public ResponseEntity<List<String>> getLocDirection(@RequestBody TimeStampBasedRequest record) {
        List<Value> valueList =  valueService.getLocValuesByScheme(LocValueScheme.DIRECTION, record.start(), record.end(), record.locID());
        List<String> jsonRepresentation = valueList.stream()
                .map(Value::toString)
                .toList();
        return ResponseEntity.ok(jsonRepresentation);
    }
    @GetMapping("/request/speed/number")
    public ResponseEntity<List<String>> getLocSpeedByNumber(@RequestBody NumberBasedRequest record) {
        List<Value> valueList =  valueService.getLocValuesByScheme(LocValueScheme.SPEED, record.locID(),record.entries());
        List<String> jsonRepresentation = valueList.stream()
                .map(Value::toString)
                .toList();
        return ResponseEntity.ok(jsonRepresentation);
    }
    @GetMapping("/request/direction/number")
    public ResponseEntity<List<String>> getLocDirectionByNumber(@RequestBody NumberBasedRequest record) {
        List<Value> valueList =  valueService.getLocValuesByScheme(LocValueScheme.DIRECTION, record.locID(), record.entries());
        List<String> jsonRepresentation = valueList.stream()
                .map(Value::toString)
                .toList();
        return ResponseEntity.ok(jsonRepresentation);
    }
}
