package de.dhbwkarlsruhe.modellbahn;

import de.dhbwkarlsruhe.modellbahn.database.entities.Value;
import de.dhbwkarlsruhe.modellbahn.database.repositories.ValueRepository;
import de.dhbwkarlsruhe.modellbahn.database.services.ValueService;
import de.dhbwkarlsruhe.modellbahn.models.LocDirection;
import de.dhbwkarlsruhe.modellbahn.models.LocSpeed;
import de.dhbwkarlsruhe.modellbahn.schemes.Direction;
import de.dhbwkarlsruhe.modellbahn.schemes.LocValueScheme;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DatabaseTest
{
    @Autowired
    private ValueRepository repository;
    @Autowired
    private ValueService service;

    @BeforeEach
    void setupDatabase()
    {
        // Clear the database before each test
        repository.deleteAll();

        // Add sample data
        Value entryOne = Value.createValue(new LocSpeed(16390, 140));
        entryOne.setTimeStamp(1744551352);
        repository.save(entryOne);

        Value entryTwo = Value.createValue(new LocDirection(16390, Direction.FORWARD));
        entryTwo.setTimeStamp(1744551354);
        repository.save(entryTwo);

        Value entryThree = Value.createValue(new LocSpeed(16390, 130));
        entryThree.setTimeStamp(1744551362);
        repository.save(entryThree);

        Value entryFour = Value.createValue(new LocDirection(16391, Direction.BACKWARD));
        entryFour.setTimeStamp(1744551372);
        repository.save(entryFour);

        Value entryFive = Value.createValue(new LocSpeed(16391, 280));
        entryFive.setTimeStamp(1744551382);
        repository.save(entryFive);

        Value entrySix = Value.createValue(new LocSpeed(16390, 160));
        entrySix.setTimeStamp(1744551385);
        repository.save(entrySix);

        Value entrySeven = Value.createValue(new LocDirection(16390, Direction.BACKWARD));
        entrySeven.setTimeStamp(1744551392);
        repository.save(entrySeven);
    }

    @Test
    void retrieveSampleDataEntries()
    {
        Value entryOne = Value.createValue(new LocSpeed(16390, 140));
        entryOne.setTimeStamp(1744551352);
        entryOne.setValueID(1);

        Value entryTwo = Value.createValue(new LocSpeed(16390, 130));
        entryTwo.setTimeStamp(1744551362);
        entryTwo.setValueID(3);

        Value entryThree = Value.createValue(new LocSpeed(16390, 160));
        entryThree.setTimeStamp(1744551385);
        entryThree.setValueID(6);

        List<Value> expected = List.of(entryThree, entryTwo, entryOne);
        List<Value> actual = service.getLocValuesByScheme(LocValueScheme.SPEED, 16390, 3);
        Assertions.assertEquals(expected, actual);
    }
}
