package de.dhbwkarlsruhe.modellbahn.models;

import de.dhbwkarlsruhe.modellbahn.database.entities.Loc;

public record LocName(int locID, String Name) {
    public Loc toEntity() {
        return new Loc(locID, Name);
    }
}
