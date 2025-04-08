package de.dhbwkarlsruhe.modellbahn.database.services;

import de.dhbwkarlsruhe.modellbahn.Models.Model;
import de.dhbwkarlsruhe.modellbahn.Models.SimpleLocValue;
import de.dhbwkarlsruhe.modellbahn.database.entities.Value;
import de.dhbwkarlsruhe.modellbahn.database.repositories.ValueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ValueService
{
    private final ValueRepository valueRepository;
    public void addValue(SimpleLocValue model){
        Value value = Value.createValue(model);
        valueRepository.save(value);
    }
}
