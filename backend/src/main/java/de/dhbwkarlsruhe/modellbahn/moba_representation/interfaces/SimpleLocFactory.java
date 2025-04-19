package de.dhbwkarlsruhe.modellbahn.moba_representation.interfaces;

import de.dhbwkarlsruhe.modellbahn.moba_representation.can.CANMessage;
import de.dhbwkarlsruhe.modellbahn.moba_representation.loc.LocDirection;
import de.dhbwkarlsruhe.modellbahn.moba_representation.loc.LocSpeed;
import de.dhbwkarlsruhe.modellbahn.schemes.CommandScheme;
import de.dhbwkarlsruhe.modellbahn.schemes.Direction;
import de.dhbwkarlsruhe.modellbahn.schemes.LocValueScheme;
import de.dhbwkarlsruhe.modellbahn.schemes.Priority;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SimpleLocFactory {
    public static CANMessage createRequest(int locID, LocValueScheme scheme) {
        return switch (scheme) {
            case SPEED -> {
                LocSpeed locSpeed = new LocSpeed(locID, -1);
                yield new CANMessage(Priority.COMMAND,
                        CommandScheme.LOCOMOTIVE_SPEED,
                        locSpeed, false);
            }
            case DIRECTION -> {
                LocDirection locDirection = new LocDirection(locID, Direction.REQUEST);
                yield new CANMessage(Priority.COMMAND,
                        CommandScheme.LOCOMOTIVE_DIRECTION,
                        locDirection, false);
            }
        };
    }
}
