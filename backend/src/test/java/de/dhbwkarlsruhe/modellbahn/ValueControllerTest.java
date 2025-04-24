package de.dhbwkarlsruhe.modellbahn;

import de.dhbwkarlsruhe.modellbahn.controller.ValueController;
import de.dhbwkarlsruhe.modellbahn.database.entities.Value;
import de.dhbwkarlsruhe.modellbahn.database.services.ValueService;
import de.dhbwkarlsruhe.modellbahn.moba_representation.loc.LocDirection;
import de.dhbwkarlsruhe.modellbahn.moba_representation.loc.LocSpeed;
import de.dhbwkarlsruhe.modellbahn.schemes.Direction;
import de.dhbwkarlsruhe.modellbahn.schemes.LocValueScheme;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ValueControllerTest {

    @MockBean
    private ValueService valueService;
    private ValueController valueController;

    @BeforeEach
    void setUp() {
        valueController = new ValueController(valueService);
    }

    // this detected a weird bug where the parameterlist was messed up
    @Test
    void getLocSpeed_WithTimeRange_ReturnsValues() {
        int locID = 16390;
        // Arrange
        Value entryOne = new Value(new LocSpeed(locID, 140), 1744551352);


        Value entryThree = new Value(new LocSpeed(locID, 130), 1744551362);


        List<Value> expectedValues = Arrays.asList(entryOne, entryThree);

        when(valueService.getLocValuesByScheme(
                LocValueScheme.SPEED, 1744551352, 1744551362, locID
        )).thenReturn(expectedValues);

        // Act
        ResponseEntity<List<Value>> response = valueController.getLocSpeed(
                locID,
                Optional.of(1744551352),
                Optional.of(1744551362),
                Optional.empty()
        );

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedValues, response.getBody());
    }

    @Test
    void getLocSpeed_WithEntries_ReturnsValues() {
        // Arrange
        int locID = 16390;
        // Arrange
        Value entryOne = new Value(new LocSpeed(locID, 140), 1744551352);


        Value entryThree = new Value(new LocSpeed(locID, 130), 1744551362);


        List<Value> expectedValues = Arrays.asList(entryOne, entryThree);

        when(valueService.getLocValuesByScheme(
                LocValueScheme.SPEED, locID, 2
        )).thenReturn(expectedValues);


        // Act
        ResponseEntity<List<Value>> response = valueController.getLocSpeed(
                locID,
                Optional.empty(),
                Optional.empty(),
                Optional.of(2)
        );

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedValues, response.getBody());
    }

    @Test
    void getLocSpeed_WithNoParameters_ReturnsBadRequest() {
        // Act
        ResponseEntity<List<Value>> response = valueController.getLocSpeed(
                1,
                Optional.empty(),
                Optional.empty(),
                Optional.empty()
        );

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getLocDirection_WithTimeRange_ReturnsValues() {
        // Arrange
        int locID = 16390;
        Value entryTwo = new Value(new LocDirection(locID, Direction.FORWARD), 1744551354);


        Value entryFour = new Value(new LocDirection(locID, Direction.BACKWARD), 1744551372);


        Value entrySeven = new Value(new LocDirection(locID, Direction.BACKWARD), 1744551392);

        List<Value> expectedValues = Arrays.asList(entryTwo, entryFour, entrySeven);
        when(valueService.getLocValuesByScheme(
                LocValueScheme.DIRECTION, 1744551354, 1744551392, locID
        )).thenReturn(expectedValues);

        // Act
        ResponseEntity<List<Value>> response = valueController.getLocDirection(
                locID,
                Optional.of(1744551354),
                Optional.of(1744551392),
                Optional.empty()
        );

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedValues, response.getBody());
        verify(valueService).getLocValuesByScheme(LocValueScheme.DIRECTION, 1744551354, 1744551392, locID);
    }

    @Test
    void getLocDirection_WithEntries_ReturnsValues() {
        // Arrange


        int locID = 1390;
        Value entryTwo = new Value(new LocDirection(locID, Direction.FORWARD), 1744551354);


        Value entryFour = new Value(new LocDirection(locID, Direction.BACKWARD), 1744551372);


        Value entrySeven = new Value(new LocDirection(locID, Direction.BACKWARD), 1744551392);

        List<Value> expectedValues = Arrays.asList(entryTwo, entryFour, entrySeven);

        when(valueService.getLocValuesByScheme(
                LocValueScheme.DIRECTION, locID, 3
        )).thenReturn(expectedValues);

        // Act
        ResponseEntity<List<Value>> response = valueController.getLocDirection(
                locID,
                Optional.empty(),
                Optional.empty(),
                Optional.of(3)
        );

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedValues, response.getBody());
        verify(valueService).getLocValuesByScheme(LocValueScheme.DIRECTION, locID, 3);
    }

    @Test
    void getLocDirection_WithNoParameters_ReturnsBadRequest() {
        // Act
        ResponseEntity<List<Value>> response = valueController.getLocDirection(
                1,
                Optional.empty(),
                Optional.empty(),
                Optional.empty()
        );

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getLocSpeed_WithOnlyStartTime_ReturnsBadRequest() {
        // Act
        ResponseEntity<List<Value>> response = valueController.getLocSpeed(
                1,
                Optional.of(1000),
                Optional.empty(),
                Optional.empty()
        );

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}