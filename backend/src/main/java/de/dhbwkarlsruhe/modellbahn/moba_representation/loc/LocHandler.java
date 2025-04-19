package de.dhbwkarlsruhe.modellbahn.moba_representation.loc;

import de.dhbwkarlsruhe.modellbahn.moba_representation.can.CANMessage;
import de.dhbwkarlsruhe.modellbahn.moba_representation.can.MobaSocket;
import de.dhbwkarlsruhe.modellbahn.moba_representation.interfaces.SimpleLocFactory;
import de.dhbwkarlsruhe.modellbahn.moba_representation.interfaces.SimpleLocValue;
import de.dhbwkarlsruhe.modellbahn.schemes.LocValueScheme;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@AllArgsConstructor
@Component
public class LocHandler {

    private static final Logger logger = LoggerFactory.getLogger(LocHandler.class);
    private MobaSocket socket;

    /**
     * @param value package to be sent to Moba
     * @return true if the package was sent successfully, false otherwise
     */
    public boolean setValue(SimpleLocValue value) {
        CANMessage message = SimpleLocFactory.createCommand(value);
        try {
            socket.send(message);
        } catch (IOException e) {
            logError(e);
            return false;
        }
        return true;
    }

    public SimpleLocValue requestLocValue(int locID, LocValueScheme scheme) throws IOException {
        CANMessage request = SimpleLocFactory.createRequest(locID, scheme);
        CANMessage response = socket.handleCANInteraction(request);
        return (SimpleLocValue) response.getPayload();
    }


    private void logError(Exception e) {
        logger.error(e.getMessage());
    }
}
