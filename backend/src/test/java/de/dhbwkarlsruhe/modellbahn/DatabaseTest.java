package de.dhbwkarlsruhe.modellbahn;

import de.dhbwkarlsruhe.modellbahn.database.entities.Value;
import de.dhbwkarlsruhe.modellbahn.database.repositories.ValueRepository;
import de.dhbwkarlsruhe.modellbahn.database.services.ValueService;
import de.dhbwkarlsruhe.modellbahn.moba_representation.loc.LocDirection;
import de.dhbwkarlsruhe.modellbahn.moba_representation.loc.LocSpeed;
import de.dhbwkarlsruhe.modellbahn.schemes.Direction;
import de.dhbwkarlsruhe.modellbahn.schemes.LocValueScheme;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DatabaseTest {
    @Autowired
    private ValueRepository repository;
    @Autowired
    private ValueService service;

    @BeforeEach
    void setupDatabase() {
        // Clear the database before each test
        repository.deleteAll();

        // Add sample data
        Value entryOne = new Value(new LocSpeed(16390, 140), 1744551352);

        repository.save(entryOne);

        Value entryTwo = new Value(new LocDirection(16390, Direction.FORWARD), 1744551354);

        repository.save(entryTwo);

        Value entryThree = new Value(new LocSpeed(16390, 130), 1744551362);

        repository.save(entryThree);

        Value entryFour = new Value(new LocDirection(16391, Direction.BACKWARD), 1744551372);

        repository.save(entryFour);

        Value entryFive = new Value(new LocSpeed(16391, 280), 1744551382);

        repository.save(entryFive);

        Value entrySix = new Value(new LocSpeed(16390, 160), 1744551385);

        repository.save(entrySix);

        Value entrySeven = new Value(new LocDirection(16390, Direction.BACKWARD), 1744551392);

        repository.save(entrySeven);
    }

    @Test
    void retrieveSampleDataEntries() {
        Value entryOne = new Value(new LocSpeed(16390, 140), 1744551352);
        Value entryTwo = new Value(new LocSpeed(16390, 130), 1744551362);
        Value entryThree = new Value(new LocSpeed(16390, 160), 1744551385);

        List<Value> expected = List.of(entryThree, entryTwo, entryOne);
        List<Value> actual = service.getLocValuesByScheme(LocValueScheme.SPEED, 16390, 3);
        Assertions.assertEquals(expected, actual);
    }
}
