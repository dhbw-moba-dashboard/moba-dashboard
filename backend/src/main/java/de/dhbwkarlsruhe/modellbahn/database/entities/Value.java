package de.dhbwkarlsruhe.modellbahn.database.entities;

import de.dhbwkarlsruhe.modellbahn.Models.SimpleLocValue;
import lombok.Getter;
import lombok.Setter;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table
@Getter
@Setter
public class Value
{
    @Id
    private int ValueID;

    private int TypeID;
    private String TimeStamp;
    private int Value;
    private int Loc;

    public static Value createValue(SimpleLocValue model)
    {
        Value value = new Value();
        value.setValue(model.getValue());
        value.setLoc(model.getLoc());
        value.setTypeID(0);

        value.setTimeStamp(Instant.now().toString());
        return value;
    }
}
