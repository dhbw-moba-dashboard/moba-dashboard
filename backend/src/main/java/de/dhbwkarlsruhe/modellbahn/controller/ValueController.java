package de.dhbwkarlsruhe.modellbahn.controller;

import de.dhbwkarlsruhe.modellbahn.MobaSocket;
import de.dhbwkarlsruhe.modellbahn.database.entities.Value;
import de.dhbwkarlsruhe.modellbahn.database.services.LocService;
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
    public ResponseEntity<List<String>> getLocSpeed(@RequestBody RequestBodyRecords record) {
        List<Value> valueList =  valueService.getLocValuesByScheme(LocValueScheme.SPEED, record.start(), record.end(), record.locID());
        List<String> jsonRepresentation = valueList.stream()
                .map(Value::toString)
                .toList();
        return ResponseEntity.ok(jsonRepresentation);
    }
    @GetMapping("/request/direction")
    public ResponseEntity<List<String>> getLocDirection(@RequestBody RequestBodyRecords record) {
        List<Value> valueList =  valueService.getLocValuesByScheme(LocValueScheme.DIRECTION, record.start(), record.end(), record.locID());
        List<String> jsonRepresentation = valueList.stream()
                .map(Value::toString)
                .toList();
        return ResponseEntity.ok(jsonRepresentation);
    }
}
