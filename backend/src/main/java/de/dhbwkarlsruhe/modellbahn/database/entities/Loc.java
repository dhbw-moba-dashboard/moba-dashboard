package de.dhbwkarlsruhe.modellbahn.database.entities;

import de.dhbwkarlsruhe.modellbahn.Models.LocName;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Table(name = "loc")
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Loc
{

    @Id
    @Column(name = "locid")
    private  int LocID;
    @Column(name = "name")
    private  String Name;


    public LocName toModel()
    {
        return new LocName(LocID, Name);
    }
}
