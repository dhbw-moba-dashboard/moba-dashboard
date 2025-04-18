package de.dhbwkarlsruhe.modellbahn.models;

import de.dhbwkarlsruhe.modellbahn.schemes.LocValueScheme;

/**
 * These are the Models, which represent Values associated with a loc
 * This type is easier to persist in a database
 */
public interface SimpleLocValue extends Model {
    /**
     * @return locid
     */
    int getLoc();

    /**
     * @return the actual value.
     */
    int getValue();

    /**
     * @return the type of Value
     */
    LocValueScheme getLocScheme();

    boolean isValidAnswer();
}
