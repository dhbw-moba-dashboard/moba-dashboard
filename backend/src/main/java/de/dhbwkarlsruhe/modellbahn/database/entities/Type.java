package de.dhbwkarlsruhe.modellbahn.database.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table(name = "type")
@Getter
@NoArgsConstructor
public class Type {
    @Id
    @Column(name = "type_id")
    private int typeID;
    @Column(name = "type_name")
    private String typeName;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Type type = (Type) o;
        return typeID == type.typeID && Objects.equals(typeName, type.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeID, typeName);
    }
}
