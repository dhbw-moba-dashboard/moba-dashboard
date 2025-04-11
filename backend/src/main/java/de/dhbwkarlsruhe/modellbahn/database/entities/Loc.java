package de.dhbwkarlsruhe.modellbahn.database.entities;

import de.dhbwkarlsruhe.modellbahn.models.LocName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "loc")
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Loc {
    @Id
    @Column(name = "loc_id")
    private int locID;
    @Column(name = "name")
    private String name;

    public static Loc fromModel(LocName model) {
        return new Loc(model.locID(), model.name());
    }

    public LocName toModel() {
        return new LocName(locID, name);
    }
}
