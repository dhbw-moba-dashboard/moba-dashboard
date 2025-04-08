package de.dhbwkarlsruhe.modellbahn.database.entities;

import lombok.Getter;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table
@Entity
@Getter
public class Loc
{
    @Id
    private int LocID;
    private String Name;
}
