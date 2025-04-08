package de.dhbwkarlsruhe.modellbahn.database.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table
public class Type
{
    @Id
    private int TypeID;
    private String TypeName;

}
