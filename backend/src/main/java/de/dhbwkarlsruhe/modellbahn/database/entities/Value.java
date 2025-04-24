package de.dhbwkarlsruhe.modellbahn.database.entities;

import de.dhbwkarlsruhe.modellbahn.moba_representation.interfaces.SimpleLocValue;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table(name = "loc_values")
@Getter
@NoArgsConstructor
public class Value {
    @Column(name = "type_id")
    private int type;
    @Id
    @Column(name = "value_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int valueID;
    @Column(name = "time_stamp")
    private long timeStamp;
    @Column(name = "data")
    private int data;

    @Column(name = "loc")
    private int loc;


    public Value(SimpleLocValue model, long timeStamp) {

        data = model.getValue();
        loc = model.getLoc();
        type = model.getLocScheme().ordinal();
        this.timeStamp = timeStamp;

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Value value = (Value) o;
        return timeStamp == value.timeStamp && data == value.data && loc == value.loc && Objects.equals(type, value.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, timeStamp, data, loc);
    }
}
