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
public class ValueController {
    private final ValueService valueService;

    @GetMapping("/request/speed")
    public ResponseEntity<List<String>> getLocSpeed(@RequestBody TimeStampBasedRequest timeStampBasedRequest) {
        List<Value> valueList = valueService.getLocValuesByScheme(LocValueScheme.SPEED, timeStampBasedRequest.start(), timeStampBasedRequest.end(), timeStampBasedRequest.locID());
        List<String> jsonRepresentation = valueList.stream()
                .map(Value::toString)
                .toList();
        return ResponseEntity.ok(jsonRepresentation);
    }

    @GetMapping("/request/direction")
    public ResponseEntity<List<String>> getLocDirection(@RequestBody TimeStampBasedRequest timeStampBasedRequest) {
        List<Value> valueList = valueService.getLocValuesByScheme(LocValueScheme.DIRECTION, timeStampBasedRequest.start(), timeStampBasedRequest.end(), timeStampBasedRequest.locID());
        List<String> jsonRepresentation = valueList.stream()
                .map(Value::toString)
                .toList();
        return ResponseEntity.ok(jsonRepresentation);
    }

    @GetMapping("/request/speed/number")
    public ResponseEntity<List<String>> getLocSpeedByNumber(@RequestBody NumberBasedRequest numberBasedRequest) {
        List<Value> valueList = valueService.getLocValuesByScheme(LocValueScheme.SPEED, numberBasedRequest.locID(), numberBasedRequest.entries());
        List<String> jsonRepresentation = valueList.stream()
                .map(Value::toString)
                .toList();
        return ResponseEntity.ok(jsonRepresentation);
    }

    @GetMapping("/request/direction/number")
    public ResponseEntity<List<String>> getLocDirectionByNumber(@RequestBody NumberBasedRequest numberBasedRequest) {
        List<Value> valueList = valueService.getLocValuesByScheme(LocValueScheme.DIRECTION, numberBasedRequest.locID(), numberBasedRequest.entries());
        List<String> jsonRepresentation = valueList.stream()
                .map(Value::toString)
                .toList();
        return ResponseEntity.ok(jsonRepresentation);
    }
}
