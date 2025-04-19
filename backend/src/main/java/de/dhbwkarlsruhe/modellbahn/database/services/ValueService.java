package de.dhbwkarlsruhe.modellbahn.database.services;

import de.dhbwkarlsruhe.modellbahn.database.entities.Value;
import de.dhbwkarlsruhe.modellbahn.database.repositories.ValueRepository;
import de.dhbwkarlsruhe.modellbahn.moba_representation.can.MobaSocket;
import de.dhbwkarlsruhe.modellbahn.moba_representation.interfaces.SimpleLocValue;
import de.dhbwkarlsruhe.modellbahn.moba_representation.loc.LocHandler;
import de.dhbwkarlsruhe.modellbahn.moba_representation.loc.LocName;
import de.dhbwkarlsruhe.modellbahn.schemes.LocValueScheme;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class ValueService {
    private static final Logger logger = LoggerFactory.getLogger(ValueService.class);
    private final ValueRepository valueRepository;
    private final LocService locService;
    private final LocHandler locHandler;

    /**
     * saves a value in the database
     * and generates timestamp
     *
     * @param model the value to save
     */
    public void addValue(SimpleLocValue model) {

        Value value = new Value(model, Instant.now().getEpochSecond());
        valueRepository.save(value);
    }

    /**
     * a scheduled task that requests data from the MoBa every minute and updates the database.
     * the data should include every loc associated with every loc.
     */
    @Scheduled(cron = "*/15 * * * * *")
    public void checkAndSaveValues() {
        List<Integer> locIDs = locService.getLocs().stream().map(LocName::locID).toList();
        try {
            for (int locID : locIDs) {
                iterateOverScheme(locID);
            }
        } catch (SocketTimeoutException connectException) {
            logger.error("Could not connect to MOBA");
        }

    }

    /**
     * iterates over the schema enum of the loc-Values
     *
     * @param locID data from this specific loc
     * @throws SocketTimeoutException indicates that the computer can't connect to the MoBa
     */
    private void iterateOverScheme(int locID) throws SocketTimeoutException {
        for (LocValueScheme scheme : LocValueScheme.values()) {
            try {
                SimpleLocValue value = locHandler.requestLocValue(locID, scheme);
                if (value.isValidAnswer()) {
                    addValue(value);
                }
            } catch (MobaSocket.InvalidPackageException e) {
                logger.error("Invalid package", e);
            } catch (SocketTimeoutException e) {
                throw e;
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }

    /**
     * @param scheme kind of values e.g. speed, direction
     * @param start  start time (unix timestamp)
     * @param end    end time (unix timestamp)
     * @param locID  specific locomotive
     * @return all values in specific timespan
     */
    public List<Value> getLocValuesByScheme(LocValueScheme scheme, long start, long end, int locID) {
        return valueRepository.findValueInRange(start, end, scheme.ordinal(), locID);
    }

    /**
     * @param scheme kind of values e.g. speed, direction
     * @param locID  specific locomotive
     * @param number number of entries
     * @return last number of values
     */
    public List<Value> getLocValuesByScheme(LocValueScheme scheme, int locID, int number) {
        return valueRepository.findNumberOfValues(scheme.ordinal(), locID, number);
    }
}
