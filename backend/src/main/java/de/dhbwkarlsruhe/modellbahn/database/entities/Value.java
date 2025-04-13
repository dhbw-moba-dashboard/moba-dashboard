package de.dhbwkarlsruhe.modellbahn.database.entities;

import de.dhbwkarlsruhe.modellbahn.models.SimpleLocValue;
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
    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "type_id")
    public Type type;
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


    public static Value createValue(SimpleLocValue model)
    {
        Value value = new Value();
        value.setData(model.getValue());
        value.setLoc(model.getLoc());
        value.setType(model.getType().getType());

        value.setTimeStamp(Instant.now().getEpochSecond());
        return value;
    }


}
