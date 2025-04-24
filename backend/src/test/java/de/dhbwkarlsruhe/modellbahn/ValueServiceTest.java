package de.dhbwkarlsruhe.modellbahn;

import de.dhbwkarlsruhe.modellbahn.database.entities.Value;
import de.dhbwkarlsruhe.modellbahn.database.repositories.ValueRepository;
import de.dhbwkarlsruhe.modellbahn.database.services.LocService;
import de.dhbwkarlsruhe.modellbahn.database.services.ValueService;
import de.dhbwkarlsruhe.modellbahn.moba_representation.can.MobaSocket;
import de.dhbwkarlsruhe.modellbahn.moba_representation.loc.LocHandler;
import de.dhbwkarlsruhe.modellbahn.moba_representation.loc.LocName;
import de.dhbwkarlsruhe.modellbahn.moba_representation.loc.LocSpeed;
import de.dhbwkarlsruhe.modellbahn.schemes.LocValueScheme;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
class ValueServiceTest {

    @Mock
    private ValueRepository valueRepository;
    @Mock
    private LocService locService;
    @Mock
    private LocHandler locHandler;

    private ValueService valueService;

    @BeforeEach
    void setUp() {
        valueService = new ValueService(valueRepository, locService, locHandler);
    }


    @Test
    void checkAndSaveValues_ShouldProcessAllLocs() throws IOException {
        // Given
        List<LocName> locNames = Arrays.asList(
                new LocName(1, "Loc1"),
                new LocName(2, "Loc2")
        );
        when(locService.getLocs()).thenReturn(locNames);
        when(locHandler.requestLocValue(anyInt(), any(LocValueScheme.class)))
                .thenReturn(new LocSpeed(1, 100));

        // When
        valueService.checkAndSaveValues();

        // Then
        verify(valueRepository, times(LocValueScheme.values().length * locNames.size()))
                .save(any(Value.class));
    }

    @Test
    void checkAndSaveValues_ShouldHandleSocketTimeout() throws IOException {
        // Given
        List<LocName> locNames = List.of(new LocName(1, "Loc1"));
        when(locService.getLocs()).thenReturn(locNames);
        when(locHandler.requestLocValue(anyInt(), any(LocValueScheme.class)))
                .thenThrow(new SocketTimeoutException("Connection timeout"));

        // When
        valueService.checkAndSaveValues();

        // Then
        verify(valueRepository, never()).save(any(Value.class));
    }

    @Test
    void checkAndSaveValues_ShouldHandleInvalidPackage() throws IOException {
        // Given
        List<LocName> locNames = List.of(new LocName(1, "Loc1"));
        when(locService.getLocs()).thenReturn(locNames);
        when(locHandler.requestLocValue(anyInt(), any(LocValueScheme.class)))
                .thenThrow(new MobaSocket.InvalidPackageException("Invalid package"));

        // When
        valueService.checkAndSaveValues();

        // Then
        verify(valueRepository, never()).save(any(Value.class));
    }

    @Test
    void checkAndSaveValues_ShouldHandleIOException() throws IOException {
        // Given
        List<LocName> locNames = List.of(new LocName(1, "Loc1"));
        when(locService.getLocs()).thenReturn(locNames);
        when(locHandler.requestLocValue(anyInt(), any(LocValueScheme.class)))
                .thenThrow(new IOException("IO error"));

        // When
        valueService.checkAndSaveValues();

        // Then
        verify(valueRepository, never()).save(any(Value.class));
    }

    @Test
    void checkAndSaveValues_ShouldSkipInvalidAnswers() throws IOException {
        // Given
        List<LocName> locNames = List.of(new LocName(1, "Loc1"));
        when(locService.getLocs()).thenReturn(locNames);
        when(locHandler.requestLocValue(anyInt(), any(LocValueScheme.class)))
                .thenReturn(new LocSpeed(1, -1));

        // When
        valueService.checkAndSaveValues();

        // Then
        verify(valueRepository, never()).save(any(Value.class));
    }


}