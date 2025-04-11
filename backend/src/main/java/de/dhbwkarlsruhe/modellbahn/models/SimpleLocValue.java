package de.dhbwkarlsruhe.modellbahn.models;

import de.dhbwkarlsruhe.modellbahn.schemes.LocValueScheme;

public interface SimpleLocValue extends Model
{
    int getLoc();

    int getValue();

    LocValueScheme getType();
}
