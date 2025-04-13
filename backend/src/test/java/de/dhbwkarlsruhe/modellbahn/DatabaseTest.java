package de.dhbwkarlsruhe.modellbahn;

import de.dhbwkarlsruhe.modellbahn.database.entities.Value;
import de.dhbwkarlsruhe.modellbahn.database.repositories.ValueRepository;
import de.dhbwkarlsruhe.modellbahn.database.services.ValueService;
import de.dhbwkarlsruhe.modellbahn.models.LocSpeed;
import de.dhbwkarlsruhe.modellbahn.schemes.LocValueScheme;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
class DatabaseTest
{
    @Autowired
    private ValueRepository repository;
    @Autowired
    private ValueService service;

    @Test
    void retrieveSampleDataEntries()
    {
        Value entryOne = Value.createValue(new LocSpeed(16390, 140));
        entryOne.setTimeStamp(1744551352);

        Value entryTwo = Value.createValue(new LocSpeed(16390, 130));
        entryOne.setTimeStamp(1744551354);

        Value entryThree = Value.createValue(new LocSpeed(16390, 160));
        entryOne.setTimeStamp(1744551357);
        List<Value> expected = List.of(entryOne, entryTwo, entryThree);
        List<Value> actual = service.getLocValuesByScheme(LocValueScheme.SPEED, 16390, 3);
        Assertions.assertEquals(expected, actual);
    }
}
