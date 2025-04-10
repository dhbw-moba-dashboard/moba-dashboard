package de.dhbwkarlsruhe.modellbahn.database.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "type")
public class Type
{
    @Id
    @Column(name = "type_id")
    private int TypeID;
    @Column(name = "type_name")
    private String TypeName;

}
