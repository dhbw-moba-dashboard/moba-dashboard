package de.dhbwkarlsruhe.modellbahn.models;

import de.dhbwkarlsruhe.modellbahn.schemes.CommandScheme;
import de.dhbwkarlsruhe.modellbahn.schemes.Direction;
import de.dhbwkarlsruhe.modellbahn.schemes.LocValueScheme;
import de.dhbwkarlsruhe.modellbahn.schemes.Priority;

public class SimpleLocFactory
{
    //this prevents the class from being instantiated
    private SimpleLocFactory()
    {
    }

    public static CANMessage createRequest(int locID, LocValueScheme scheme)
    {
        return switch (scheme)
        {
            case SPEED ->
            {
                LocSpeed locSpeed = new LocSpeed(locID, -1);
                yield new CANMessage(Priority.COMMAND,
                        CommandScheme.LOCOMOTIVE_SPEED,
                        locSpeed, false);
            }
            case DIRECTION ->
            {
                LocDirection locDirection = new LocDirection(locID, Direction.REQUEST);
                yield new CANMessage(Priority.COMMAND,
                        CommandScheme.LOCOMOTIVE_DIRECTION,
                        locDirection, false);
            }
        };
    }
}
