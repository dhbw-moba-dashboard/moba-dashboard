package de.dhbwkarlsruhe.modellbahn.database.entities;

import de.dhbwkarlsruhe.modellbahn.Models.LocName;
import lombok.AllArgsConstructor;
import lombok.Getter;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table
@Entity
@Getter
@AllArgsConstructor
public class Loc
{
    @Id
    private int LocID;
    private String Name;

    public Loc()
    {

    }
    public LocName toModel()
    {
        return new LocName(LocID, Name);
    }
}
