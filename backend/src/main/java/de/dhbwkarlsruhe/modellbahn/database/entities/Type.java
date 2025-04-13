package de.dhbwkarlsruhe.modellbahn.database.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "type")
@Getter
@Setter
public class Type
{
    @Id
    @Column(name = "type_id")
    private int typeID;
    @Column(name = "type_name")
    private String typeName;
}
