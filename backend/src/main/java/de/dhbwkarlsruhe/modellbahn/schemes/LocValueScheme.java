package de.dhbwkarlsruhe.modellbahn.schemes;

import de.dhbwkarlsruhe.modellbahn.database.entities.Type;
import lombok.Getter;

/**
 * this enum contains the values which are associated with a loc
 * speed and direction are the simplest to retrieve since the CS3 answers with a single frame
 * more complex values are ressources such as sand or diesel. They have to be read out of a Config-Stream.
 */
@Getter
public enum LocValueScheme
{
    SPEED,
    DIRECTION;

    public Type getType()
    {
        Type currentType = new Type();
        currentType.setTypeID(ordinal());
        currentType.setTypeName(name());
        return currentType;
    }
}
