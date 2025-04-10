package de.dhbwkarlsruhe.modellbahn.database.entities;

import de.dhbwkarlsruhe.modellbahn.Models.LocDirection;
import de.dhbwkarlsruhe.modellbahn.Models.LocSpeed;
import de.dhbwkarlsruhe.modellbahn.Models.SimpleLocValue;
import de.dhbwkarlsruhe.modellbahn.schemes.Direction;
import de.dhbwkarlsruhe.modellbahn.schemes.LocValueScheme;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.Instant;

@Entity
@Table(name = "loc_values")
@Getter
@Setter
public class Value
{
    @Id
    @Column(name = "value_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ValueID;
    @Column(name = "type_id")
    public int typeID;
    @Column(name = "time_stamp")
    private long TimeStamp;
    @Column(name = "value")
    private int Value;
    @Column(name = "loc")
    private int Loc;

    public static Value createValue(SimpleLocValue model)
    {
        Value value = new Value();
        value.setValue(model.getValue());
        value.setLoc(model.getLoc());
        value.setTypeID(model.getType().ordinal());

        value.setTimeStamp(Instant.now().getEpochSecond());
        return value;
    }
    public SimpleLocValue toModel()
    {
        LocValueScheme scheme = LocValueScheme.values()[typeID];
        return switch (scheme){
            case SPEED -> new LocSpeed(Loc, Value);
            case DIRECTION ->new LocDirection(Loc, Direction.values()[Value]);

        };
    }
}
